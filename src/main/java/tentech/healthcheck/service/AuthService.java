package tentech.healthcheck.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tentech.healthcheck.mapper.AuthMapper;
import tentech.healthcheck.model.dto.EmailRequest;
import tentech.healthcheck.model.dto.UserAccountRequest;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.model.entity.UserAccount;
import tentech.healthcheck.repository.UserAccountRepository;
import tentech.healthcheck.serviceImpl.EmailService;

import java.io.File;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService implements EmailService {

    private final AuthMapper authMapper;
    private final UserAccountRepository userAccountRepository;
    private final JavaMailSender javaMailSender;
    private String sender = "argenabdykadyrov5@gmail.com";
    private final PasswordEncoder passwordEncoder;

    public UserAccountResponse signUp(UserAccountRequest userAccountRequest) {
        UserAccount userAccount = authMapper.mapToEntity(userAccountRequest);
        userAccount.setDate(LocalDate.now());
        userAccountRepository.save(userAccount);
        return authMapper.mapToUserResponse(userAccount);
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

    public void registerUser(String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        // сохранить encodedPassword в базу
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}