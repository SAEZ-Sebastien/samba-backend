package fr.miage.samba.backend.model;

public class AdresseDto {
    private int numeroVoie;
    private String codePostal;
    private String departement;
    private String rue;

    public AdresseDto(int numero, String rue, String codePostal, String departement){
        this.numeroVoie = numero;
        this.rue = rue;
        this.codePostal = codePostal;
        this.departement = departement;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public int getNumeroVoie() {
        return numeroVoie;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String toString(){
        return this.numeroVoie + " " + this.rue + " " + this.departement + " " + this.codePostal;
    }
}
