package fr.miage.samba.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class UserDto{

    @Id
    private  String id;
    private  String username;
    private String password;

    public UserDto(){

    }

    public UserDto(String username, String password){
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id.toHexString();
    }
}