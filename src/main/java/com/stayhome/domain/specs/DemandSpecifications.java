package com.stayhome.domain.specs;

import com.stayhome.domain.Demand;
import com.stayhome.domain.Organization;
import com.stayhome.service.dto.DemandCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class DemandSpecifications {

    private DemandSpecifications() {
    }

    public static Specification<Demand> createSpecification(Organization organization, DemandCriteria criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.join("organization"), organization));

            if (criteria.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), criteria.getStatus()));
            }

            if (StringUtils.isNotBlank(criteria.getAssignee())) {
                predicates.add(criteriaBuilder.equal(root.join("assignee").get("login"), criteria.getAssignee()));
            }

            if (criteria.getServiceId() != null) {
                predicates.add(criteriaBuilder.equal(root.join("serviceType").get("id"), criteria.getServiceId()));
            }

            if (criteria.getLocalityId() != null) {
                predicates.add(criteriaBuilder.equal(root.join("locality").get("id"), criteria.getLocalityId()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
