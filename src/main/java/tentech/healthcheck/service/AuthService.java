package tentech.healthcheck.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
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

import java.io.File;
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

    public String sendSimpleMail(EmailRequest details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    public String
    sendMailWithAttachment(EmailRequest details) {
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());
            FileSystemResource file =
                    new FileSystemResource(
                            new File(details.getAttachment()));
            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        } catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
    }


//    public String setNewPassword(String email, Long resetCode, String newPassword, String confirmPassword) {
//        UserAccount user = userAccountRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User with: "+email+" not found !"));
//        if (resetCode.equals(user.get())) {
//            if (newPassword.equals(confirmPassword)) {
//                user.setPassword(passwordEncoder.encode(newPassword));
//            } else {
//                throw new IncorrectCodeException("The password does not match !");
//            }
//        } else {
//            throw new IncorrectCodeException("The code does not match");
//        }
//        userRepository.save(user);
//        return "New password set successfully login with this password";
//    }

//    public void sendResetCodeForPassword(String email) throws MessagingException {
//        UserAccount user = userAccountRepository.findByEmail(email);
////                .orElseThrow(() -> new ChangeSetPersister.NotFoundException("User with: " + email + " not found !"));
//
//        Long strCode = user.getResetCode();
//        userAccountRepository.save(user);
//
//        MimeMessage mimeMessage = MailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//        mimeMessageHelper.setTo(email);
//        mimeMessageHelper.setSubject("Set Password");
//        mimeMessageHelper.setText("Your reset code: " + strCode);
//
//        javaMailSender.send(mimeMessage);
//    }
}

