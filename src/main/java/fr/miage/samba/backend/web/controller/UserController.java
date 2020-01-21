package fr.miage.samba.backend.web.controller;

import fr.miage.samba.backend.model.UserDto;
import fr.miage.samba.backend.services.UserService;
import fr.miage.samba.backend.web.exceptions.MailAlreayExist;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping(value = "/sign-up")
    public UserDto createUser(
            @Valid
            @RequestBody
                    UserDto user) {
       /* if(!Regex.REGEX_MAIL.getPattern().matcher(user.getUsername()).matches()){
            throw new IncorrectMailFormat();
        }*/

        if(this.userService.getUserByUsername(user.getUsername()) != null){
            throw new MailAlreayExist();
        }

        user.setId(ObjectId.get());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return this.userService.addUser(user);
    }
}
