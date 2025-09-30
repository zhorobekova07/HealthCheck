package tentech.healthcheck.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tentech.healthcheck.mapper.AuthMapper;
import tentech.healthcheck.mapper.LoginMapper;
import tentech.healthcheck.model.dto.*;
import tentech.healthcheck.model.entity.UserAccount;
import tentech.healthcheck.model.enums.Role;
import tentech.healthcheck.repository.UserAccountRepository;
import tentech.healthcheck.security.jwt.JwtTokenUtil;
import tentech.healthcheck.serviceImpl.EmailService;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService implements EmailService {

    private final LoginMapper loginMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserAccountRepository userAccountRepository;
    private final AuthMapper authMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;
    private String sender = "argenabdykadyrov5@gmail.com";


    public UserAccountResponse signUp(UserAccountRequest userAccountRequest) {
        UserAccount userAccount = authMapper.mapToEntity(userAccountRequest);
        if (findAll().isEmpty()) {
            userAccount.setRole(Role.ADMIN);
        } else {
            userAccount.setRole(Role.USER);
        }
        Optional<UserAccount> user1 = Optional.ofNullable(userAccountRepository.findByEmail(userAccountRequest.getEmail()));
        if (user1.isPresent()) {
            throw new RuntimeException("User with this mail already exists in the database");
        }
        if (userAccountRequest.getPassword().matches(("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*._-]).{8,}$"))) {
            userAccount.setPassword(passwordEncoder.encode(userAccountRequest.getPassword()));
        } else {
            throw new RuntimeException("invalid password");
        }
        userAccountRepository.save(userAccount);
        return authMapper.mapToResponse(userAccount);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        UserAccount userAccount = userAccountRepository.findByEmail(loginRequest.getEmail());
        String jwt = jwtTokenUtil.generateToken(userAccount);
        return loginMapper.mapToResponse(jwt, userAccount);
    }

    public List<UserAccountResponse> findAll() {
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        return authMapper.mapToList(userAccounts);
    }

    private Long generateRandomCode() {
        SecureRandom random = new SecureRandom();
        Long code = 10000000 + random.nextLong(90000000); // От 10000000 до 99999999
        return code;
    }

    public String sendSimpleMail(EmailRequest details) {
        try {
            Long code = generateRandomCode();

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText("Ваш код: " + code);

            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully with code: " + code;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while Sending Mail";
        }
    }

    // Метод для сброса пароля с проверкой resetCode
    public String setNewPassword(String email, Long resetCode, String newPassword, String confirmPassword) {
        UserAccount user = userAccountRepository.findByEmail(email);
//                .orElseThrow(() -> new RuntimeException("User with: " + email + " not found!"));


        if (resetCode.equals(user.getResetCode())) {
            if (newPassword.equals(confirmPassword)) {
                user.setPassword(passwordEncoder.encode(newPassword));
            } else {
                throw new RuntimeException("not matches");
            }
        } else {
            throw new RuntimeException("code not correct");
        }

        if (!newPassword.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*._-]).{8,}$")) {
            throw new RuntimeException("Invalid password format");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userAccountRepository.save(user);
        return "New password set successfully, please login with the new password";
    }

    // Метод для отправки кода сброса пароля
    public void sendResetCodeForPassword(String email) throws MessagingException {
        UserAccount user = userAccountRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User with email " + email + " not found");
        }

        Long resetCode = user.getResetCode();
        if (resetCode == null) {
            // Генерируем новый resetCode, если он отсутствует
            resetCode = generateRandomCode();
            user.setResetCode(resetCode);
            userAccountRepository.save(user);
        }

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Set Password");
        mimeMessageHelper.setText("Your reset code: " + resetCode);

        javaMailSender.send(mimeMessage);
    }
}

