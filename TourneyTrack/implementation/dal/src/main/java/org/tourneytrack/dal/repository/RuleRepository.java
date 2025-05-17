package org.tourneytrack.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tourneytrack.dal.entity.RuleEntity;

import java.util.List;

public interface RuleRepository extends JpaRepository<RuleEntity, Long> {
}
