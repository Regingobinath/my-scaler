package com.inventory.apiAdapter;

public interface EmailAdapter {
    public void sendEmailAsync(String email, String subject, String body);
}
