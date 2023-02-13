package com.statista.code.challenge.email.domain;

import com.statista.code.challenge.email.inbound.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail() {
        log.info("sending email....");
    }
}
