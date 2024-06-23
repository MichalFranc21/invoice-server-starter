package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.InvoiceEntity_;
import cz.itnetwork.entity.filter.InvoiceFilter;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

public class InvoiceSpecification implements Specification<InvoiceEntity> {
    private final InvoiceFilter filter;

    public InvoiceSpecification(InvoiceFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<InvoiceEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getMinPrice() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(InvoiceEntity_.PRICE), filter.getMinPrice()));
        }

        if (filter.getMaxPrice() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(InvoiceEntity_.PRICE), filter.getMaxPrice()));
        }

        if (filter.getProduct() != null && !filter.getProduct().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get(InvoiceEntity_.PRODUCT), filter.getProduct()));
        }

        if (filter.getSellerID() != null) {
            predicates.add(criteriaBuilder.equal(root.get(InvoiceEntity_.SELLER).get("id"), filter.getSellerID()));
        }

        if (filter.getBuyerID() != null) {
            predicates.add(criteriaBuilder.equal(root.get(InvoiceEntity_.BUYER).get("id"), filter.getBuyerID()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
