package fr.miage.samba.backend.services;

import fr.miage.samba.backend.model.UserDto;

public interface UserService {

    UserDto getUserByUsername(String username);

    UserDto addUser(UserDto user);
}
