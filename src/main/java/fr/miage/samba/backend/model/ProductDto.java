package fr.miage.samba.backend.model;

import java.util.ArrayList;
import java.util.List;

public class ProductDto {
    private String title;
    private String description;
    private double prix;
    private String mainPicture;
    private List<String> picturesDetails = new ArrayList<String>();
    private String vendeurUsername;
    private int etatNotation;

    public ProductDto(String title, String description, double prix) {
        this.title = title;
        this.description = description;
        this.prix = prix;
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
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

}
