package com.learnsyc.appweb.repositories;

import com.learnsyc.appweb.models.Canje;
import com.learnsyc.appweb.models.CanjePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanjeoRepository extends JpaRepository<Canje, CanjePK> {
}
