package fr.diginamic.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length=255)
    private String nom;

    @OneToMany(mappedBy = "categorie")
    private Set<Produit> produits; // --> = null de base

    {
        this.produits = new HashSet<>(); // --> me permet d'enlever le "null" et instancie une collection vide
        // --> en gros j'ai le droit de faire this.produits.add() ou this.produits.remove() sans que sa pete
    }

    // constructeur vide
    public Category() {
    }

    // constructeur avec les attributs de base sans l'id (et donc sans les relations)
    public Category(String nom) {
        this.nom = nom;
    }

    // constructeur avec les attributs de base + l'id (et donc sans les relations)
    public Category(long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void addProduit(Produit produit){

    }

    public void removeProduit(Produit produit){

    }

    @Override
    public String toString() {
        return "Marque{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
