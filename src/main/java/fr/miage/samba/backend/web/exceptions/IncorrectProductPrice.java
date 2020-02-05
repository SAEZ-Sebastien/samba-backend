package fr.miage.samba.backend.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Product price is invalid")
public class IncorrectProductPrice extends RuntimeException {
    public IncorrectProductPrice() {
        super();
    }
}