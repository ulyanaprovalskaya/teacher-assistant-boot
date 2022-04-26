package com.grsu.teacherassistant.service.filter;

import com.grsu.teacherassistant.entity.Student;
import com.grsu.teacherassistant.entity.StudentGroup;
import com.grsu.teacherassistant.entity.StudentGroup_;
import com.grsu.teacherassistant.entity.Student_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class StudentFilter extends BaseFilter<Student, String> {

    @Override
    public Specification<Student> getFilter(String search) {
        if (!StringUtils.hasText(search)) {
            return null;
        }

        return (root, query, cb) -> {
            query.distinct(true);
            Join<Student, StudentGroup> join = root.join(Student_.GROUPS, JoinType.INNER);
            Specification<Student> where =
                    where(
                            fieldContains(Student_.FIRST_NAME, search)
                                    .or(fieldContains(Student_.LAST_NAME, search))
                                    .or(fieldContains(join.get(StudentGroup_.NAME), search))
                                    .or(fieldContains(Student_.CARD_UID, search))
                    );

            return where.toPredicate(root, query, cb);
        };
    }
}
