package fr.diginamic.entities;

public class Allergene {

    public void addProduit(Produit produit) {
        if (produit != null) {
            produit.addAllergene(this);

        }
    }
    public void removeProduit (Produit produit){
        if (produit != null) {
            produit.removeAllergene(null);
        }
    }

}
}
