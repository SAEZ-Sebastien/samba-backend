package fr.miage.samba.backend.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data
@Document(collection = "user")
@JsonFilter("OwnerFilter")
public class UserDto{

    @Id
    private String id;
    private String lastName;
    private String firstName;
    private String username;
    private String password;
    private String mail;
    private List<AvisDto> avis = new ArrayList<AvisDto>();
    private AdresseDto adresseLivraison;
    private AdresseDto adresseFacturation;
    private boolean isCertified;
    private double notation;
    private String avatarUrl;

    public UserDto(){

    }

    public UserDto(String username, String password, String mail){
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public void setUsername(String username) {
        this.username = username.toUpperCase();
    }

    public String getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id.toHexString();
    }


    public void setMail(String mail) {
        this.mail = mail.toUpperCase();
    }
}