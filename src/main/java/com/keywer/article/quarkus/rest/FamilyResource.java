package com.keywer.article.quarkus.rest;

import com.keywer.article.quarkus.domain.Family;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;


@ResourceProperties(hal = true, path = "family")
public interface FamilyResource extends PanacheEntityResource<Family, Long> {
}
