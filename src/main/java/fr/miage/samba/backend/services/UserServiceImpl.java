package fr.miage.samba.backend.services;

import fr.miage.samba.backend.dao.UserDao;
import fr.miage.samba.backend.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    UserDao userDao;

   public UserDto getUserByUsername(String username){
       return this.userDao.getUserDtoByUsername(username);
    }

    @Override
    public UserDto addUser(UserDto user) {
        System.out.println(user);
        return this.userDao.save(user);
    }
}
