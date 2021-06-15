package fr.diginamic.entities;

import fr.diginamic.service.QueryService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name = QueryService.NAMED_QUERY_INGREDIENT, query = "SELECT i FROM Ingredient i WHERE i.nom = :nom")
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nom;

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
        this.nom = nom;
    }

    // constructeur avec les attributs de base + l'id (et donc sans les relations)
    public Ingredient(Long id, String nom) {
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
