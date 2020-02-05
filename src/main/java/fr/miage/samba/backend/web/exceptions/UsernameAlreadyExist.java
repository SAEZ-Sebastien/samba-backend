package fr.miage.samba.backend.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Username already exist")
public class UsernameAlreadyExist extends RuntimeException {
    public UsernameAlreadyExist() {
        super();
    }
}
