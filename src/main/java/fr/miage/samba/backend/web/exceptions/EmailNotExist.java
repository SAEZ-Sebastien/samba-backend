package fr.miage.samba.backend.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Email doesn't exist")
public class EmailNotExist extends RuntimeException {
    public EmailNotExist() {
        super();
    }
}
