package com.grsu.teacherassistant.service.filter;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;

public abstract class BaseFilter<T, U> {

    public abstract Specification<T> getFilter(U search);

    Specification<T> fieldContains(String attribute, String value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get(attribute)),
                    containsLowerCase(value)
            );
        };
    }

    Specification<T> fieldContains(Path<String> path, String value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }

            return cb.like(
                    cb.lower(path),
                    containsLowerCase(value)
            );
        };
    }

    private String containsLowerCase(String searchField) {
        String wildcard = "%";
        return wildcard + searchField.toLowerCase() + wildcard;
    }
}
