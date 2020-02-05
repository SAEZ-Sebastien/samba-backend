package fr.miage.samba.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "product")
public class ProductDto {

    @Id
    private  String id;
    private String title;
    private String description;
    private double price;
    //private String mainPicture;
   // private List<String> picturesDetails = new ArrayList<String>();
    private String sellerId;
    //private int etatNotation;

    public ProductDto(){

    }

    public ProductDto(String title, String description, double prix) {
        this.title = title;
        this.description = description;
        this.price = prix;
    }

    public String getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id.toHexString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return price;
    }

    public void setPrix(double prix) {
        this.price = prix;
    }

}
