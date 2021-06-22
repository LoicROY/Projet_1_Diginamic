package fr.diginamic.entities;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BasedEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected BasedEntity() {
    }

    protected BasedEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
