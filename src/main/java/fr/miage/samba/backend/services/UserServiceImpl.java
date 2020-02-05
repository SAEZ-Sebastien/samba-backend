package fr.miage.samba.backend.services;

import fr.miage.samba.backend.dao.UserDao;
import fr.miage.samba.backend.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    UserDao userDao;

   public UserDto getUserByUsername(String username){
       return this.userDao.getUserDtoByUsername(username);
    }

    @Override
    public Optional<UserDto> getUserById(String id) {
        return this.userDao.findById(id);
    }

    @Override
    public UserDto getUserByMail(String mail) {
        return this.userDao.getUserDtoByMail(mail);
    }

    @Override
    public UserDto addUser(UserDto user) {
        System.out.println(user);
        return this.userDao.save(user);
    }

    @Override
    public List<UserDto> getUsers() {
        return this.userDao.findAll();
    }
}
