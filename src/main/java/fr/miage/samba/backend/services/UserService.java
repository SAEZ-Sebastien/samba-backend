package fr.miage.samba.backend.services;

import fr.miage.samba.backend.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto getUserByUsername(String username);

    Optional<UserDto> getUserById(String id);

    UserDto getUserByMail(String mail);

    UserDto addUser(UserDto user);

    List<UserDto> getUsers();
}
