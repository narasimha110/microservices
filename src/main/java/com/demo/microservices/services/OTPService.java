package com.demo.microservices.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class OTPService {
	@Autowired
    private JavaMailSender javaMailSender;

    private Map<String, Integer> otpStorage = new HashMap<>();
    private static final int EXPIRATION_TIME_MINUTES = 5;

    public int generateOTP(String email) {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpStorage.put(email, otp);

        // Send OTP via email
        sendOTPEmail(email, otp);

        return otp;
    }

    public boolean verifyOTP(String email, int otp) {
        Integer storedOTP = otpStorage.get(email);
        if (storedOTP != null && storedOTP == otp) {
            // Clear OTP after successful verification
            otpStorage.remove(email);
            return true;
        }
        return false;
    }

    private void sendOTPEmail(String email, int otp) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(email);
            helper.setSubject("OTP for Login");
            helper.setText("Your OTP for login is: " + otp);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
        System.out.println("OTP sent to " + email + ": " + otp);
    }
}
