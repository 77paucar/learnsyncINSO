package com.learnsyc.appweb.repositories;

import com.learnsyc.appweb.models.Mazo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MazoRepository extends JpaRepository<Mazo, Long> {
    Mazo findByIdMazo(Long id);
}
