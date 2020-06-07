package com.keywer.article.quarkus.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Fish.getByName", query = "select f from Fish f where f.name = :name"),
}
)
public class Fish extends PanacheEntity {

    @NotBlank
    public String name;
    @Positive
    public int temperature;
    @Positive
    @DecimalMin("0.3")
    public float price;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="family_fk")
    @JsonIgnore
    public Family family;


    public static Fish findByName(String name){
        return find("#Fish.getByName", name).firstResult();
    }

}