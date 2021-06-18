package fr.diginamic.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name = "Marque.findByNom", query = "SELECT m FROM Marque m WHERE m.nom = :nom")
public class Marque extends BasedEntity {

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
        super(nom);
    }

    // constructeur avec les attributs de base + l'id (et donc sans les relations)
    public Marque(Long id, String nom) {
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
