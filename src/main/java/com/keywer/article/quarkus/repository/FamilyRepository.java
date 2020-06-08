package com.keywer.article.quarkus.repository;

import com.keywer.article.quarkus.domain.Family;
import com.keywer.article.quarkus.domain.enums.WaterType;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class FamilyRepository  implements PanacheRepository<Family> {
    public Family findByName(String name){
        return find("name", name).firstResult();
    }

    public List<Family> findSea(){
        return list("waterType", WaterType.SEA);
    }

    public void deleteByName(String name){
        delete("name", name);
    }
}
