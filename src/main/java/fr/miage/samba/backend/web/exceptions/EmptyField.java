package fr.miage.samba.backend.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "One of fiels is empty")
public class EmptyField extends RuntimeException {
    public EmptyField() {
        super();
    }
    public EmptyField(String s) {
        super(s);
    }
}

