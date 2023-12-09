package com.learnsyc.appweb.repositories;

import com.learnsyc.appweb.models.Premio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremioRepository extends JpaRepository<Premio, Long> {
    Premio findByIdPremio(Long id);

    Premio findByNombre(String nombre);
}