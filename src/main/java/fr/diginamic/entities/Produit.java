package fr.diginamic.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="PRODUIT")
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


}
