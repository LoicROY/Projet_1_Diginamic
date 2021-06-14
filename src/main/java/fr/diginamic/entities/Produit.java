package fr.diginamic.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "PRODUIT")
public class Produit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID")
    private long id;

    private String nom;

    @Enumerated(EnumType.ORDINAL)
    private NutritionGradeFr nutritionGradeFr;

    private float energie100g;
    private float graisse100g;
    private float sucre100g;
    private float fibre100g;
    private float proteine100g;
    private float sel100g;
    private float vitA100g;
    private float vitD100g;
    private float vitE100g;
    private float vitK100g;
    private float vitC100g;
    private float vitB1100g;
    private float vitB2100g;
    private float vitPP100g;
    private float vitB6100g;
    private float vitB9100g;
    private float vitB12100g;
    private float calcium100g;
    private float magnesium100g;
    private float iron100g;
    private float fer100g;
    private float betaCarotene100g;
    private boolean presenceHuileDePalme;

    //<--------------------------------------------->
    // Relations

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_CATEGORIE", referencedColumnName = "ID", nullable = false)
    private Categorie categorie;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "PRODUIT_ALLERGENE",
            joinColumns = @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_ALLERGENE", referencedColumnName = "ID"))
    private Set<Allergene> allergenes;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_MARQUE", referencedColumnName = "ID", nullable = false)
    private Marque marque;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "PRODUIT_INGREDIENT",
            joinColumns = @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "ID_INGREDIENT", referencedColumnName = "ID",nullable = false))
    private Set<Ingredient> ingredients;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "PRODUIT_ADDITIF",
            joinColumns = @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_ADDITIF", referencedColumnName = "ID"))
    private Set<Additif> additifs;

    //<--------------------------------------------->
    //HashSets

    {
        this.allergenes = new HashSet<>();
        this.ingredients = new HashSet<>();
        this.additifs = new HashSet<>();
    }

    //<--------------------------------------------->
    //Constructeurs

    public Produit() {
    }

    //sans id et sans relation
    public Produit(String nom, NutritionGradeFr nutritionGradeFr, float energie100g, float graisse100g, float sucre100g, float fibre100g, float proteine100g, float sel100g, float vitA100g, float vitD100g, float vitE100g, float vitK100g, float vitC100g, float vitB1100g, float vitB2100g, float vitPP100g, float vitB6100g, float vitB9100g, float vitB12100g, float calcium100g, float magnesium100g, float iron100g, float fer100g, float betaCarotene100g, boolean presenceHuileDePalme) {
        this.nom = nom;
        this.nutritionGradeFr = nutritionGradeFr;
        this.energie100g = energie100g;
        this.graisse100g = graisse100g;
        this.sucre100g = sucre100g;
        this.fibre100g = fibre100g;
        this.proteine100g = proteine100g;
        this.sel100g = sel100g;
        this.vitA100g = vitA100g;
        this.vitD100g = vitD100g;
        this.vitE100g = vitE100g;
        this.vitK100g = vitK100g;
        this.vitC100g = vitC100g;
        this.vitB1100g = vitB1100g;
        this.vitB2100g = vitB2100g;
        this.vitPP100g = vitPP100g;
        this.vitB6100g = vitB6100g;
        this.vitB9100g = vitB9100g;
        this.vitB12100g = vitB12100g;
        this.calcium100g = calcium100g;
        this.magnesium100g = magnesium100g;
        this.iron100g = iron100g;
        this.fer100g = fer100g;
        this.betaCarotene100g = betaCarotene100g;
        this.presenceHuileDePalme = presenceHuileDePalme;
    }

    //avec id et sans relations
    public Produit(long id, String nom, NutritionGradeFr nutritionGradeFr, float energie100g, float graisse100g, float sucre100g, float fibre100g, float proteine100g, float sel100g, float vitA100g, float vitD100g, float vitE100g, float vitK100g, float vitC100g, float vitB1100g, float vitB2100g, float vitPP100g, float vitB6100g, float vitB9100g, float vitB12100g, float calcium100g, float magnesium100g, float iron100g, float fer100g, float betaCarotene100g, boolean presenceHuileDePalme) {
        this.id = id;
        this.nom = nom;
        this.nutritionGradeFr = nutritionGradeFr;
        this.energie100g = energie100g;
        this.graisse100g = graisse100g;
        this.sucre100g = sucre100g;
        this.fibre100g = fibre100g;
        this.proteine100g = proteine100g;
        this.sel100g = sel100g;
        this.vitA100g = vitA100g;
        this.vitD100g = vitD100g;
        this.vitE100g = vitE100g;
        this.vitK100g = vitK100g;
        this.vitC100g = vitC100g;
        this.vitB1100g = vitB1100g;
        this.vitB2100g = vitB2100g;
        this.vitPP100g = vitPP100g;
        this.vitB6100g = vitB6100g;
        this.vitB9100g = vitB9100g;
        this.vitB12100g = vitB12100g;
        this.calcium100g = calcium100g;
        this.magnesium100g = magnesium100g;
        this.iron100g = iron100g;
        this.fer100g = fer100g;
        this.betaCarotene100g = betaCarotene100g;
        this.presenceHuileDePalme = presenceHuileDePalme;
    }


    //<--------------------------------------------->
    //Getters et Setters

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

    public NutritionGradeFr getNutritionGradeFr() {
        return nutritionGradeFr;
    }

    public void setNutritionGradeFr(NutritionGradeFr nutritionGradeFr) {
        this.nutritionGradeFr = nutritionGradeFr;
    }

    public float getEnergie100g() {
        return energie100g;
    }

    public void setEnergie100g(float energie100g) {
        this.energie100g = energie100g;
    }

    public float getGraisse100g() {
        return graisse100g;
    }

    public void setGraisse100g(float graisse100g) {
        this.graisse100g = graisse100g;
    }

    public float getSucre100g() {
        return sucre100g;
    }

    public void setSucre100g(float sucre100g) {
        this.sucre100g = sucre100g;
    }

    public float getFibre100g() {
        return fibre100g;
    }

    public void setFibre100g(float fibre100g) {
        this.fibre100g = fibre100g;
    }

    public float getProteine100g() {
        return proteine100g;
    }

    public void setProteine100g(float proteine100g) {
        this.proteine100g = proteine100g;
    }

    public float getSel100g() {
        return sel100g;
    }

    public void setSel100g(float sel100g) {
        this.sel100g = sel100g;
    }

    public float getVitA100g() {
        return vitA100g;
    }

    public void setVitA100g(float vitA100g) {
        this.vitA100g = vitA100g;
    }

    public float getVitD100g() {
        return vitD100g;
    }

    public void setVitD100g(float vitD100g) {
        this.vitD100g = vitD100g;
    }

    public float getVitE100g() {
        return vitE100g;
    }

    public void setVitE100g(float vitE100g) {
        this.vitE100g = vitE100g;
    }

    public float getVitK100g() {
        return vitK100g;
    }

    public void setVitK100g(float vitK100g) {
        this.vitK100g = vitK100g;
    }

    public float getVitC100g() {
        return vitC100g;
    }

    public void setVitC100g(float vitC100g) {
        this.vitC100g = vitC100g;
    }

    public float getVitB1100g() {
        return vitB1100g;
    }

    public void setVitB1100g(float vitB1100g) {
        this.vitB1100g = vitB1100g;
    }

    public float getVitB2100g() {
        return vitB2100g;
    }

    public void setVitB2100g(float vitB2100g) {
        this.vitB2100g = vitB2100g;
    }

    public float getVitPP100g() {
        return vitPP100g;
    }

    public void setVitPP100g(float vitPP100g) {
        this.vitPP100g = vitPP100g;
    }

    public float getVitB6100g() {
        return vitB6100g;
    }

    public void setVitB6100g(float vitB6100g) {
        this.vitB6100g = vitB6100g;
    }

    public float getVitB9100g() {
        return vitB9100g;
    }

    public void setVitB9100g(float vitB9100g) {
        this.vitB9100g = vitB9100g;
    }

    public float getVitB12100g() {
        return vitB12100g;
    }

    public void setVitB12100g(float vitB12100g) {
        this.vitB12100g = vitB12100g;
    }

    public float getCalcium100g() {
        return calcium100g;
    }

    public void setCalcium100g(float calcium100g) {
        this.calcium100g = calcium100g;
    }

    public float getMagnesium100g() {
        return magnesium100g;
    }

    public void setMagnesium100g(float magnesium100g) {
        this.magnesium100g = magnesium100g;
    }

    public float getIron100g() {
        return iron100g;
    }

    public void setIron100g(float iron100g) {
        this.iron100g = iron100g;
    }

    public float getFer100g() {
        return fer100g;
    }

    public void setFer100g(float fer100g) {
        this.fer100g = fer100g;
    }

    public float getBetaCarotene100g() {
        return betaCarotene100g;
    }

    public void setBetaCarotene100g(float betaCarotene100g) {
        this.betaCarotene100g = betaCarotene100g;
    }

    public boolean isPresenceHuileDePalme() {
        return presenceHuileDePalme;
    }

    public void setPresenceHuileDePalme(boolean presenceHuileDePalme) {
        this.presenceHuileDePalme = presenceHuileDePalme;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        if (this.categorie != null) {
            this.categorie.getProduits().remove(this);
        }
        this.categorie = categorie;
        if (categorie != null) {
            categorie.getProduits().add(this);
        }
    }

    public Set<Allergene> getAllergenes() {
        return allergenes;
    }

    public void setAllergenes(Set<Allergene> allergenes) {
        this.allergenes = allergenes;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        if (this.marque != null) {
            this.marque.getProduits().remove(this);
        }
        this.marque = marque;
        if (marque != null) {
            marque.getProduits().add(this);
        }
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<Additif> getAdditifs() {
        return additifs;
    }

    public void setAdditifs(Set<Additif> additifs) {
        this.additifs = additifs;
    }


    //<--------------------------------------------->
    //Add-remove


    //ingredients

    public void addIngredient(Ingredient ingredients) {
        if (ingredients != null) {
            ingredients.getProduits().add(this);
            this.ingredients.add(ingredients);
        }
    }

    public void removeIngredient(Ingredient ingredients) {
        if (ingredients != null) {
            ingredients.getProduits().remove(this);
            this.ingredients.remove(ingredients);
        }
    }

    //allergenes
    public void addAllergene(Allergene allergenes) {
        if (allergenes != null) {
            allergenes.getProduits().add(this);
            this.allergenes.add(allergenes);
        }
    }


    public void removeAllergene(Ingredient allergene) {
        if (allergene != null) {
            allergene.getProduits().remove(this);
            this.allergenes.remove(allergene);
        }
    }


    //additif
    public void addAdditif(Additif additif) {
        if (additif != null) {
            additif.getProduits().add(this);
            this.additifs.add(additif);
        }
    }

    public void removeAdditif(Additif additif) {
        if (additif != null) {
            additif.getProduits().remove(this);
            this.additifs.remove(additif);
        }
    }

    //<--------------------------------------------->
    //To String
    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", nutritionGradeFr=" + nutritionGradeFr +
                ", energie100g=" + energie100g +
                ", graisse100g=" + graisse100g +
                ", sucre100g=" + sucre100g +
                ", fibre100g=" + fibre100g +
                ", proteine100g=" + proteine100g +
                ", sel100g=" + sel100g +
                ", vitA100g=" + vitA100g +
                ", vitD100g=" + vitD100g +
                ", vitE100g=" + vitE100g +
                ", vitK100g=" + vitK100g +
                ", vitC100g=" + vitC100g +
                ", vitB1100g=" + vitB1100g +
                ", vitB2100g=" + vitB2100g +
                ", vitPP100g=" + vitPP100g +
                ", vitB6100g=" + vitB6100g +
                ", vitB9100g=" + vitB9100g +
                ", vitB12100g=" + vitB12100g +
                ", calcium100g=" + calcium100g +
                ", magnesium100g=" + magnesium100g +
                ", iron100g=" + iron100g +
                ", fer100g=" + fer100g +
                ", betaCarotene100g=" + betaCarotene100g +
                ", presenceHuileDePalme=" + presenceHuileDePalme +
                '}';
    }
}

