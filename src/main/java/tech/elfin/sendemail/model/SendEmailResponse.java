package tech.elfin.sendemail.model;

import java.io.Serializable;

/**
 * POJO-класс для сериализации в JSON-ответ
 */
public class SendEmailResponse implements Serializable {
    private boolean isEmailSended;

    public SendEmailResponse(boolean isEmailSended) {
        this.isEmailSended = isEmailSended;
    }

    public boolean getIsEmailSended() {
        return isEmailSended;
    }

    public void setIsEmailSended(boolean isEmailSended) {
        this.isEmailSended = isEmailSended;
    }
}
