package fr.diginamic.entities;

public class Additif {

    public void addProduit(Produit produit) {
        if (produit != null) {
            produit.addAdditif(this);

        }
    }
        public void removeProduit (Produit produit){
            if (produit != null) {
                produit.removeAdditif(null);
            }
        }

    }