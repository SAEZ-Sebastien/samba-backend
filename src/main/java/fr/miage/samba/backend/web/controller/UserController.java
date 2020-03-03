package fr.miage.samba.backend.web.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import fr.miage.samba.backend.enums.Regex;
import fr.miage.samba.backend.model.UserDto;
import fr.miage.samba.backend.Helper.TokenHelper;
import fr.miage.samba.backend.services.UserService;
import fr.miage.samba.backend.web.exceptions.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    //Get all
    @GetMapping()
    public MappingJacksonValue getUsers() {
        MappingJacksonValue users = new MappingJacksonValue(this.userService.getUsers());
        users.setFilters(getFilterForUsers(true));
        return users;
    }

    @GetMapping("/{id}")
    public MappingJacksonValue getUserById(HttpServletRequest request, @PathVariable String id){
        boolean isOwner = false;
        Optional<UserDto> requestResult = this.userService.getUserById(id);
        if(!requestResult.isPresent()){
            throw new UserNotFound();
        }

        MappingJacksonValue user = new MappingJacksonValue(requestResult.get());

        String userId = TokenHelper.extractSubjectOf(request);

        if(!userId.trim().isEmpty()) {
            isOwner = id.equals(userId);
        }

        user.setFilters(getFilterForUsers(isOwner));

        return user;
    }


    private FilterProvider getFilterForUsers(boolean isOwner) {

        String[] allowedProperties = isOwner
                ? new String[]{"id","username","mail","password"}
                : new String[]{"id", "username"};

        return new SimpleFilterProvider()
                .addFilter("OwnerFilter", SimpleBeanPropertyFilter.filterOutAllExcept(allowedProperties));
    }

    @PostMapping(value = "/sign-up")
    public Object createUser(
            @Valid
            @RequestBody
                    UserDto user) {
        if(user.getPassword().isEmpty() || user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getUsername().isEmpty() || user.getMail().isEmpty()){
            throw new EmptyField();
        }

        if(!Regex.REGEX_MAIL.getPattern().matcher(user.getMail()).matches()){
            throw new IncorrectMailFormat();
        }

        if(user.getPassword().length() < 8){
            throw  new IncorrectPasswordFormat();
        }

        if(this.userService.getUserByMail(user.getMail()) != null){
            throw new MailAlreayExist();
        }

        if(this.userService.getUserByUsername(user.getUsername()) != null){
            throw new UsernameAlreadyExist();
        }


        user.setId(ObjectId.get());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        UserDto userAdded =  this.userService.addUser(user);

        MappingJacksonValue users = new MappingJacksonValue(userAdded);

        FilterProvider filter = new SimpleFilterProvider()
                .addFilter("OwnerFilter", SimpleBeanPropertyFilter.serializeAll());

        users.setFilters(filter);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userAdded.getId())
                .toUri();

        return ResponseEntity.created(location).body(users);

    }
}
