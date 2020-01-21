package fr.miage.samba.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "user")
public class UserDto{

    @Id
    private  String id;
    private  String username;
    private String password;
    private String mail;
    private List<AvisDto> avis = new ArrayList<AvisDto>();
    private AdresseDto adresseLivraison;
    private AdresseDto adresseFacturation;
    private boolean isCertified;
    private double notation;
    private List<CommandeDto> commandes = new ArrayList<CommandeDto>();
    private String avatarUrl;

    public UserDto(){

    }

    public UserDto(String username, String password, String mail){
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}