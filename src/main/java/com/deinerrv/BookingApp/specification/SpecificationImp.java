package com.deinerrv.BookingApp.specification;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class SpecificationImp<T> implements Specification<T> {
    
    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path<String> key = null;
        if(criteria.getKey().contains("."))
            key = getComplexPath(root);
        else
            key = root.<String> get(criteria.getKey());

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
              key, criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
              key, criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (key.getJavaType() == String.class) {
                return builder.like(
                    key, "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(key, criteria.getValue());
            }
        }
        return null;
    }

    private Path<String> getComplexPath(Root<T> root){
        String[] path = criteria.getKey().split("\\.");
        System.out.println(path.length);
        Path<String> complexPathRoot = root.get(path[0]);
        for(int i=1; i<path.length; i++){
            complexPathRoot = complexPathRoot.get(path[i]);
        }
        return complexPathRoot;
    }
}
