package com.mariuszilinskas.vsp.comms.email.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.mariuszilinskas.vsp.comms.email.dto.EmailRequest;
import com.mariuszilinskas.vsp.comms.email.dto.ResetPasswordRequest;
import com.mariuszilinskas.vsp.comms.email.dto.VerifyEmailRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


/**
 * Service implementation for managing Platform Transactional emails.
 *
 * @author Marius Zilinskas
 */
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final AmazonSimpleEmailService sesClient;
    private final TemplateEngine templateEngine;

    @Value("${email.fromEmail}")
    private String fromEmail;

    @Value("${frontend.baseUrl}")
    private String baseUrl;

    @Override
    public void sendVerifyAccountEmail(VerifyEmailRequest request) {
        ModelMap model = initialiseModelMap(request);
        model.addAttribute("passcode", request.getPasscode());

        String body = buildEmail("verifyAccount.html", model);
        sendEmail("Verify Account", request.getEmail(), "Verify Your Account", body);
    }

    @Override
    public void sendWelcomeEmail(EmailRequest request) {
        ModelMap model = initialiseModelMap(request);
        String body = buildEmail("welcome.html", model);
        sendEmail("Welcome", request.getEmail(), "Welcome to VSP!", body);
    }

    @Override
    public void sendResetPasswordEmail(ResetPasswordRequest request) {
        ModelMap model = initialiseModelMap(request);
        String resetLink = baseUrl + "/reset-password?token=" + request.getResetToken();
        model.addAttribute("resetLink", resetLink);

        String body = buildEmail("resetPassword.html", model);
        sendEmail("Reset Password", request.getEmail(), "Reset Your Password", body);
    }

    private ModelMap initialiseModelMap(EmailRequest request) {
        ModelMap model = new ModelMap();
        model.addAttribute("firstName", request.getFirstName());
        model.addAttribute("email", request.getEmail());
        return model;
    }

    private String buildEmail(String templateName, ModelMap model) {
        Context context = new Context();
        context.setVariables(model);
        return templateEngine.process(templateName, context);
    }

    private void sendEmail(String type, String to, String subject, String body) {
        String fromFormatted = "VSP <" + fromEmail + ">";

        Destination destination = new Destination().withToAddresses(to);
        Content subjectContent = new Content().withCharset("UTF-8").withData(subject);
        Content htmlBodyContent = new Content().withCharset("UTF-8").withData(body);

        Body emailBody = new Body().withHtml(htmlBodyContent);
        Message message = new Message()
                .withBody(emailBody)
                .withSubject(subjectContent);

        SendEmailRequest sendEmailRequest = new SendEmailRequest()
                .withDestination(destination)
                .withMessage(message)
                .withSource(fromFormatted);

        try {
            sesClient.sendEmail(sendEmailRequest);
            logger.info("{} Email sent successfully to {}", type, to);
        } catch (Exception ex) {
            logger.error("{} email could not be sent. Error message: {}", type, ex.getMessage());
        }
    }

}
