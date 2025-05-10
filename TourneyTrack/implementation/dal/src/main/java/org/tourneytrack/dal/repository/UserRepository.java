package org.tourneytrack.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tourneytrack.dal.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
