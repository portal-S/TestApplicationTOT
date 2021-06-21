package com.totsystems.repository;

import com.totsystems.model.Security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityRepository extends JpaRepository<Security, Integer> {

    public Optional<Security> findSecurityBySecId(String secId);
}
