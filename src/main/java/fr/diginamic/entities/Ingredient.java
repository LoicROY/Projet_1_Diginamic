package fr.diginamic.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name = "Ingredient.findByNom", query = "SELECT i FROM Ingredient i WHERE i.nom = :nom")
public class Ingredient extends BasedEntity {

    @ManyToMany(mappedBy = "ingredients")
    private Set<Produit> produits; // --> = null de base

    {
        this.produits = new HashSet<>(); // --> me permet d'enlever le "null" et instancie une collection vide
        // --> en gros j'ai le droit de faire this.produits.add() ou this.produits.remove() sans que sa pete
    }

    // constructeur vide
    public Ingredient() {
    }

    // constructeur avec les attributs de base sans l'id (et donc sans les relations)
    public Ingredient(String nom) {
        super(nom);
    }

    // constructeur avec les attributs de base + l'id (et donc sans les relations)
    public Ingredient(Long id, String nom) {
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
                produit.addIngredient(this);

            }
        }
        public void removeProduit (Produit produit) throws Exception {
            if (produit != null) {
                produit.removeIngredient(this);
            }
        }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", produits=" + produits.size() +
                '}';
    }
}
