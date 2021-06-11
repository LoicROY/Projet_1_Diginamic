package fr.diginamic.entities;

public class Ingredient {

    public void addProduit(Produit produit) {
        if (produit != null) {
            produit.addIngredient(this);

        }
    }
        public void removeProduit (Produit produit){
            if (produit != null) {
                produit.removeIngredient(null);
            }
        }

    }
}