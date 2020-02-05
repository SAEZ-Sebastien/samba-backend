package fr.miage.samba.backend.configuration;

import fr.miage.samba.backend.dao.ProductDao;
import fr.miage.samba.backend.dao.UserDao;
import fr.miage.samba.backend.model.ProductDto;
import fr.miage.samba.backend.model.UserDto;
import lombok.extern.slf4j.Slf4j;

import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserDao userDao, ProductDao productDao ) {
        return args -> {
           /* userDao.deleteAll();
            UserDto user = new UserDto("admin","admin","admin@gmail.com");
            user.setId(ObjectId.get());
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            log.info("Preloading " + userDao.save(user));

            productDao.deleteAll();
            log.info("Preloading " + productDao.save(new ProductDto("Iphone","Iphone 64go",1200.50)));*/
        };
    }

}
