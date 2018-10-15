package tech.elfin.sendemail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import tech.elfin.sendemail.model.UserData;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.util.Properties;

/**
 * Сервис отправки E-mail сообщения о данных, введенных клиентом
 */
@Service
public class UserDataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDataService.class);

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.username}")
    private String userName;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.protocol}")
    private String protocol;

    @Value("${mail.smtp.auth}")
    private String auth;

    @Value("${mail.smtp.ssl.enable}")
    private String ssl;

    @Value("${mail.debug}")
    private String debug;

    @Value("${mail.from}")
    private String from;

    @Value("${mail.recipients}")
    private String recipients;

    /**
     * Отправка E-mail
     * @param userData - пользовательские данные
     * @return - признак успешности/неуспешности отправки E-mail
     */
    public boolean sendEmail(UserData userData) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.ssl.enable", ssl);
        props.put("mail.debug", debug);
        mailSender.setJavaMailProperties(props);
        mailSender.setUsername(userName);
        mailSender.setPassword(password);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setSubject("Введены данные клиента");
        try {
            String[] addressArray = recipients.replaceAll(" ", "").replaceAll(",", ";").split(";");
            for (String address: addressArray) {
                new InternetAddress(address);
            }
            mailMessage.setTo(addressArray);

            mailMessage.setText("Введены данные клиента\n" +
                    "ФИО: " + userData.getFio() + "\n" +
                    "Паспорт: " + userData.getPassSeries() + " " + userData.getPassNumber() + "\n" +
                    "E-mail: " + userData.getEmail() + "\n" +
                    "Комментарий: " + userData.getComment());

            try {
                mailSender.send(mailMessage);
                LOGGER.info("Mail sended to " + recipients);
                return true;
            } catch (MailException e) {
                LOGGER.error("Mail send to " + recipients + " failed.", e);
            }
        } catch (MessagingException e) {
            LOGGER.error("Error parsing Recipients.", e);
        }
        return false;
    }
}
