package fr.diginamic.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CATEGORIE")
//@NamedQuery(name = "Categorie.findByNom", query = "SELECT c FROM Categorie c WHERE c.nom = :nom")
public class Categorie extends BasedNamedEntity {

    @OneToMany(mappedBy = "categorie")
    private Set<Produit> produits; // --> = null de base

    {
        this.produits = new HashSet<>(); // --> me permet d'enlever le "null" et instancie une collection vide
        // --> en gros j'ai le droit de faire this.produits.add() ou this.produits.remove() sans que sa pete
    }

    // constructeur vide
    public Categorie() {
    }

    // constructeur avec les attributs de base sans l'id (et donc sans les relations)
    public Categorie(String nom) {
        super(nom);
    }

    // constructeur avec les attributs de base + l'id (et donc sans les relations)
    public Categorie(Long id, String nom) {
        super(id, nom);
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    public void addProduit(Produit produit) {
        if (produit != null) {
            produit.setCategorie(this);
        }
    }

    public void removeProduit(Produit produit) {
        if (produit != null) {
            produit.setCategorie(null);
        }
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", produits=" + produits.size() +
                '}';
    }
}
