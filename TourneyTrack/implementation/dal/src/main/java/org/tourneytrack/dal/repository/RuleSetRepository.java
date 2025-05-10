package org.tourneytrack.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tourneytrack.dal.entity.RuleSetEntity;

public interface RuleSetRepository extends JpaRepository<RuleSetEntity, Long> {
}
