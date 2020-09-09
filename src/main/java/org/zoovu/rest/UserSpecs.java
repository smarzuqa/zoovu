package org.zoovu.rest;

import org.springframework.data.jpa.domain.Specification;
import org.zoovu.model.User;
import org.zoovu.model.User_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class UserSpecs implements Specification<User> {

    public static Specification<User> dailyLeaderboard() {
        return (root, query, builder) -> {
            LocalDate today = LocalDate.now();
            query.orderBy(builder.desc(root.get(User_.score)));
            return builder.equal(root.get(User_.loginDate), today);
        };
    }

    public static Specification<User> weeklyLeaderboard() {
        return (root, query, builder) -> {
            LocalDate today = LocalDate.now();
            DayOfWeek day = today.getDayOfWeek();
            int value = day.getValue();
            LocalDate weekStart = today.minusDays(value - 1);
            query.orderBy(builder.desc(root.get(User_.score)));
            return builder.greaterThanOrEqualTo(root.get(User_.loginDate), weekStart);
        };
    }

    public static Specification<User> yearlyLeaderboard() {
        return (root, query, builder) -> {
            LocalDate today = LocalDate.now();
            int year = today.getYear();
            query.orderBy(builder.desc(root.get(User_.score)));
            return builder.greaterThanOrEqualTo(root.get(User_.loginDate), LocalDate.of(year, 1, 1));
        };
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
