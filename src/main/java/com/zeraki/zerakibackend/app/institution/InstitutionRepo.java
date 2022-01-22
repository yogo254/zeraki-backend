package com.zeraki.zerakibackend.app.institution;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepo extends JpaRepository<Institution, String> {
    List<Institution> findByKeywordsContaining(String keywords);
}
