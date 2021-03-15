package com.example.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.exception.SpringRedditException;
import com.example.model.NotificationEmail;

@Service
@AllArgsConstructor
@Slf4j
class MailService {

	private final JavaMailSender mailSender;
	private final MailContentBuilder mailContentBuilder;

	@Async
	void sendMail(NotificationEmail notificationEmail) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(notificationEmail.getRecipient());
		msg.setSubject(notificationEmail.getSubject());
		//msg.setText(mailContentBuilder.build(notificationEmail.getBody()));
		msg.setText(notificationEmail.getBody());
		try {
			mailSender.send(msg);
			log.info("Activation email sent!!");
		} catch (MailException e) {
			throw new SpringRedditException(
					"Exception occurred when sending mail to " + notificationEmail.getRecipient());
		}
	}

}