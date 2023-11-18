package ma.cherkani.entities;



import java.sql.Date;

import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    @Temporal(TemporalType.DATE)
    private Date dateDeNaisssance;
    private String photo;

    @ManyToOne
    private Service service;

    @ManyToOne
    @JoinColumn(name = "Le_chef_id")
    private Employe chef;





}
