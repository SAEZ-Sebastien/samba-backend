package fr.miage.samba.backend.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Email already exist")
public class MailAlreayExist extends RuntimeException {
    public MailAlreayExist() {
        super();
    }
}
