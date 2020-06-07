package com.keywer.article.quarkus.domain;



import com.keywer.article.quarkus.domain.enums.WaterType;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Family extends PanacheEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    public long id;
    @NotBlank
    public String name;
    @NotNull
    @Column(name="WATER_TYPE")
    public WaterType waterType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="family")
    public Collection<Fish> fishs;

}


