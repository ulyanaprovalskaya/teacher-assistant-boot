package com.grsu.teacherassistant.utils;

import com.grsu.teacherassistant.dto.FilterDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class FilterSpecification<T> implements Specification<T> {

    private final List<FilterDto> filterDtos;

    public FilterSpecification(List<FilterDto> filterDtos) {
        this.filterDtos = filterDtos;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = buildPredicates(root, criteriaBuilder);
        return predicates.size() > 1
                ? criteriaBuilder.and(predicates.toArray(new Predicate[0]))
                : predicates.get(0);
    }

    private Predicate buildPredicate(FilterDto filterDTO, Root<T> root, CriteriaBuilder criteriaBuilder) {
        switch (filterDTO.getComparison()) {
            case equals:
                return buildEqualsPredicateToCriteria(filterDTO, root, criteriaBuilder);
            case greaterThan:
                return buildGreaterThanPredicateToCriteria(filterDTO, root, criteriaBuilder);
            case lessThan:
                return buildLessThanPredicateToCriteria(filterDTO, root, criteriaBuilder);
            case like:
                return buildLikePredicateToCriteria(filterDTO, root, criteriaBuilder);
        }
        throw new RuntimeException();
    }

    private List<Predicate> buildPredicates(Root<T> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (!CollectionUtils.isEmpty(filterDtos)) {
            filterDtos.forEach(dto -> predicates.add(buildPredicate(dto, root, criteriaBuilder)));
        }
        return predicates;
    }

    private Predicate buildEqualsPredicateToCriteria(FilterDto filterDTO, Root<T> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get(filterDTO.getFilterParameter()), filterDTO.getValueOfFilterParameter());
    }

    private Predicate buildGreaterThanPredicateToCriteria(FilterDto filterDTO, Root<T> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.greaterThanOrEqualTo(root.get(filterDTO.getFilterParameter()), filterDTO.getValueOfFilterParameter());
    }

    private Predicate buildLessThanPredicateToCriteria(FilterDto filterDTO, Root<T> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.lessThanOrEqualTo(root.get(filterDTO.getFilterParameter()), filterDTO.getValueOfFilterParameter());
    }

    private Predicate buildLikePredicateToCriteria(FilterDto filterDTO, Root<T> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get(filterDTO.getFilterParameter()), filterDTO.getValueOfFilterParameter());
    }
}
