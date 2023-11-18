package ma.ensa.volley.beans;

import java.util.Date;

public class Employe {
    private Long id;
    private String nom;
    private String prenom;
    private Date dateDeNaisssance;
    private String photo;
    private Service service;
    private Employe chef;

    public Employe(Long id, String nom, String prenom, Date dateDeNaisssance, String photo, Service service) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaisssance = dateDeNaisssance;
        this.photo = photo;
        this.service = service;
    }

    public Employe(Long id, String nom, String prenom, Date dateDeNaisssance, String photo, Service service, Employe chef) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaisssance = dateDeNaisssance;
        this.photo = photo;
        this.service = service;
        this.chef = chef;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateDeNaisssance() {
        return dateDeNaisssance;
    }

    public void setDateDeNaisssance(Date dateDeNaisssance) {
        this.dateDeNaisssance = dateDeNaisssance;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getService() {
        return service.getNom();
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Employe getChef() {
        return chef;
    }

    public void setChef(Employe chef) {
        this.chef = chef;
    }
}
