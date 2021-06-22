package fr.diginamic.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BasedNamedEntity extends BasedEntity implements NamedEntity {

    @Column(nullable = false, unique = true)
    protected String nom;

    public BasedNamedEntity() {
    }

    public BasedNamedEntity(String nom) {
        this.nom = nom;
    }

    public BasedNamedEntity(Long id, String nom) {
        super(id);
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String getNameAttribute() {
        return getNom();
    }
}
