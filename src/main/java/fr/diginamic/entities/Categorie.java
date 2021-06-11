package fr.diginamic.entities;

public class Categorie {

    public void addProduit(Produit produit) {
        if (produit != null) {
            produit.addCategorie(this);
        }

        public void addProduit (Produit produit){
            if (produit != null) {
                produit.removeCategorie(null);
            }
        }

    }
}
