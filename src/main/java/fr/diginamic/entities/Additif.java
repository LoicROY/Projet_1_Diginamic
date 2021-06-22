package fr.diginamic.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
//@NamedQuery(name = "Additif.findByNom", query = "SELECT a FROM Additif a WHERE a.nom = :nom")
public class Additif extends BasedNamedEntity {

    @ManyToMany(mappedBy = "additifs")
    private Set<Produit> produits; // --> = null de base

    {
        this.produits = new HashSet<>(); // --> me permet d'enlever le "null" et instancie une collection vide
        // --> en gros j'ai le droit de faire this.produits.add() ou this.produits.remove() sans que sa pete
    }

    // constructeur vide
    public Additif() {
    }

    // constructeur avec les attributs de base sans l'id (et donc sans les relations)
    public Additif(String nom) {
        super(nom);
    }

    // constructeur avec les attributs de base + l'id (et donc sans les relations)
    public Additif(Long id, String nom) {
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
            produit.addAdditif(this);

        }
    }

    public void removeProduit (Produit produit){
        if (produit != null) {
            produit.removeAdditif(this);
        }
    }

    @Override
    public String toString() {
        return "Additif{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", produits=" + produits.size() +
                '}';
    }
}

