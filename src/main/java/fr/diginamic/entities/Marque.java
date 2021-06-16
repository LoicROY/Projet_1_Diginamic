package fr.diginamic.entities;

import fr.diginamic.service.QueryService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name = QueryService.NAMED_QUERY_MARQUE, query = "SELECT m FROM Marque m WHERE m.nom = :nom")
public class Marque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nom;

    @OneToMany(mappedBy = "marque")
       private Set<Produit> produits; // --> = null de base

    {
        this.produits = new HashSet<>(); // --> me permet d'enlever le "null" et instancie une collection vide
        // --> en gros j'ai le droit de faire this.produits.add() ou this.produits.remove() sans que sa pete
    }

    // constructeur vide
    public Marque() {
    }

    // constructeur avec les attributs de base sans l'id (et donc sans les relations)
    public Marque(String nom) {
        this.nom = nom;
    }

    // constructeur avec les attributs de base + l'id (et donc sans les relations)
    public Marque(Long id, String nom) {
        this.id = id;
        this.nom = nom;
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

    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    public void addProduit(Produit produit) {
        if (produit != null) {
            produit.setMarque(this);
        }
    }

    public void removeProduit(Produit produit) {
        if (produit != null) {
            produit.setMarque(null);
        }
    }

    @Override
    public String toString() {
        return "Marque{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", produits=" + produits.size() +
                '}';
    }
}
