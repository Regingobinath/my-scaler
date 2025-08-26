package com.inventory.apiAdapter;

import com.inventory.libraries.Sendgrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendGridAdapterApi  implements EmailAdapter {
    @Autowired
    private Sendgrid sendgrid;
    public void sendEmailAsync(String email, String subject, String body) {
        sendgrid.sendEmailAsync(email, subject, body);
    }
}
