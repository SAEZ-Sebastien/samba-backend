package fr.miage.samba.backend.dao;

import fr.miage.samba.backend.model.UserDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserDao extends MongoRepository<UserDto, String> {
    public UserDto getUserDtoByUsername(@Param("username") String username);
}
