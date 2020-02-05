package fr.miage.samba.backend.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Password doesn't respect pattern")
public class IncorrectPasswordFormat extends RuntimeException {
    public IncorrectPasswordFormat() {
        super();
    }
}