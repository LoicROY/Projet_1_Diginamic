package fr.diginamic.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "PRODUIT")
public class Produit extends BasedEntity {

    @Enumerated(EnumType.ORDINAL)
    private NutritionGradeFr nutritionGradeFr;

    private Float energie100g;
    private Float graisse100g;
    private Float sucre100g;
    private Float fibre100g;
    private Float proteine100g;
    private Float sel100g;
    private Float vitA100g;
    private Float vitD100g;
    private Float vitE100g;
    private Float vitK100g;
    private Float vitC100g;
    private Float vitB1100g;
    private Float vitB2100g;
    private Float vitPP100g;
    private Float vitB6100g;
    private Float vitB9100g;
    private Float vitB12100g;
    private Float calcium100g;
    private Float magnesium100g;
    private Float iron100g;
    private Float fer100g;
    private Float betaCarotene100g;
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
    public Produit(String nom, NutritionGradeFr nutritionGradeFr, Float energie100g, Float graisse100g, Float sucre100g, Float fibre100g, Float proteine100g, Float sel100g, Float vitA100g, Float vitD100g, Float vitE100g, Float vitK100g, Float vitC100g, Float vitB1100g, Float vitB2100g, Float vitPP100g, Float vitB6100g, Float vitB9100g, Float vitB12100g, Float calcium100g, Float magnesium100g, Float iron100g, Float fer100g, Float betaCarotene100g, boolean presenceHuileDePalme) {
        super(nom);
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
    public Produit(Long id, String nom, NutritionGradeFr nutritionGradeFr, Float energie100g, Float graisse100g, Float sucre100g, Float fibre100g, Float proteine100g, Float sel100g, Float vitA100g, Float vitD100g, Float vitE100g, Float vitK100g, Float vitC100g, Float vitB1100g, Float vitB2100g, Float vitPP100g, Float vitB6100g, Float vitB9100g, Float vitB12100g, Float calcium100g, Float magnesium100g, Float iron100g, Float fer100g, Float betaCarotene100g, boolean presenceHuileDePalme) {
        super(id, nom);
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

    public NutritionGradeFr getNutritionGradeFr() {
        return nutritionGradeFr;
    }

    public void setNutritionGradeFr(NutritionGradeFr nutritionGradeFr) {
        this.nutritionGradeFr = nutritionGradeFr;
    }

    public Float getEnergie100g() {
        return energie100g;
    }

    public void setEnergie100g(Float energie100g) {
        this.energie100g = energie100g;
    }

    public Float getGraisse100g() {
        return graisse100g;
    }

    public void setGraisse100g(Float graisse100g) {
        this.graisse100g = graisse100g;
    }

    public Float getSucre100g() {
        return sucre100g;
    }

    public void setSucre100g(Float sucre100g) {
        this.sucre100g = sucre100g;
    }

    public Float getFibre100g() {
        return fibre100g;
    }

    public void setFibre100g(Float fibre100g) {
        this.fibre100g = fibre100g;
    }

    public Float getProteine100g() {
        return proteine100g;
    }

    public void setProteine100g(Float proteine100g) {
        this.proteine100g = proteine100g;
    }

    public Float getSel100g() {
        return sel100g;
    }

    public void setSel100g(Float sel100g) {
        this.sel100g = sel100g;
    }

    public Float getVitA100g() {
        return vitA100g;
    }

    public void setVitA100g(Float vitA100g) {
        this.vitA100g = vitA100g;
    }

    public Float getVitD100g() {
        return vitD100g;
    }

    public void setVitD100g(Float vitD100g) {
        this.vitD100g = vitD100g;
    }

    public Float getVitE100g() {
        return vitE100g;
    }

    public void setVitE100g(Float vitE100g) {
        this.vitE100g = vitE100g;
    }

    public Float getVitK100g() {
        return vitK100g;
    }

    public void setVitK100g(Float vitK100g) {
        this.vitK100g = vitK100g;
    }

    public Float getVitC100g() {
        return vitC100g;
    }

    public void setVitC100g(Float vitC100g) {
        this.vitC100g = vitC100g;
    }

    public Float getVitB1100g() {
        return vitB1100g;
    }

    public void setVitB1100g(Float vitB1100g) {
        this.vitB1100g = vitB1100g;
    }

    public Float getVitB2100g() {
        return vitB2100g;
    }

    public void setVitB2100g(Float vitB2100g) {
        this.vitB2100g = vitB2100g;
    }

    public Float getVitPP100g() {
        return vitPP100g;
    }

    public void setVitPP100g(Float vitPP100g) {
        this.vitPP100g = vitPP100g;
    }

    public Float getVitB6100g() {
        return vitB6100g;
    }

    public void setVitB6100g(Float vitB6100g) {
        this.vitB6100g = vitB6100g;
    }

    public Float getVitB9100g() {
        return vitB9100g;
    }

    public void setVitB9100g(Float vitB9100g) {
        this.vitB9100g = vitB9100g;
    }

    public Float getVitB12100g() {
        return vitB12100g;
    }

    public void setVitB12100g(Float vitB12100g) {
        this.vitB12100g = vitB12100g;
    }

    public Float getCalcium100g() {
        return calcium100g;
    }

    public void setCalcium100g(Float calcium100g) {
        this.calcium100g = calcium100g;
    }

    public Float getMagnesium100g() {
        return magnesium100g;
    }

    public void setMagnesium100g(Float magnesium100g) {
        this.magnesium100g = magnesium100g;
    }

    public Float getIron100g() {
        return iron100g;
    }

    public void setIron100g(Float iron100g) {
        this.iron100g = iron100g;
    }

    public Float getFer100g() {
        return fer100g;
    }

    public void setFer100g(Float fer100g) {
        this.fer100g = fer100g;
    }

    public Float getBetaCarotene100g() {
        return betaCarotene100g;
    }

    public void setBetaCarotene100g(Float betaCarotene100g) {
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

    public void removeIngredient(Ingredient ingredients) throws Exception {
        if (!checkIfIngredientIsNotEmptyAfterRemove()){
            throw new Exception("Un produit doit être rattaché à au moins 1 ingrédient");
        }
        if (ingredients != null) {
            ingredients.getProduits().remove(this);
            this.ingredients.remove(ingredients);
        }
    }

    public boolean checkIfIngredientIsNotEmptyAfterRemove(){
        return this.ingredients.size() > 1;
    }

    //allergenes
    public void addAllergene(Allergene allergenes) {
        if (allergenes != null) {
            allergenes.getProduits().add(this);
            this.allergenes.add(allergenes);
        }
    }


    public void removeAllergene(Allergene allergene) {
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
    //Empeche la nullité de l'association produit/ingredient
    @PrePersist
    public void checkIngredientsIsNotEmpty() throws Exception {
        if (this.ingredients.isEmpty()){
            throw new Exception("Un produit doit être rattaché à au moins 1 ingrédient");
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
                ", categorie=" + categorie +
                ", allergenes=" + allergenes +
                ", marque=" + marque +
                ", ingredients=" + ingredients +
                ", additifs=" + additifs +
                '}';
    }
}

