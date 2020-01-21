package fr.miage.samba.backend.security;

import fr.miage.samba.backend.dao.UserDao;
import fr.miage.samba.backend.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        UserDto user = userDao.getUserDtoByUsername(mail);
        if (user == null) {
            throw new UsernameNotFoundException(mail);
        }
        return new User(user.getUsername(), user.getPassword(), emptyList());
    }
}
