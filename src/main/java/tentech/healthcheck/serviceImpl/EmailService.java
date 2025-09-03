package tentech.healthcheck.serviceImpl;


import tentech.healthcheck.model.dto.EmailRequest;


public interface EmailService {


    String sendSimpleMail(EmailRequest emailRequest);

    String sendMailWithAttachment(EmailRequest emailRequest);

}