package tech.elfin.sendemail.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tech.elfin.sendemail.model.SendEmailResponse;
import tech.elfin.sendemail.model.UserData;
import tech.elfin.sendemail.service.UserDataService;

/**
 * Контроллер для обработки REST-запросов http://host:port/api/sendmail.
 * Данные передаются в теле POST-запроса
 */
@RestController
@RequestMapping(value = "/api")
public class UserDataController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDataService.class);

    @Autowired
    private UserDataService userDataService;

    @RequestMapping(value = "/sendemail", method = RequestMethod.POST)
    public SendEmailResponse sendEmail(@RequestBody UserData userData) {
        LOGGER.info(userData.toString());
        return new SendEmailResponse(userDataService.sendEmail(userData));
    }
}
