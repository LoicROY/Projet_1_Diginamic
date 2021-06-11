package fr.diginamic.entities;

public class Marque {

    public void addProduit(Produit produit) {
        if (produit != null) {
            produit.addMarque(this);
        }

        public void addProduit (Produit produit){
            if (produit != null) {
                produit.removeMarque(null);
            }
        }

    }
}
}
