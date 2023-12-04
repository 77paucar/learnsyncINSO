package com.learnsyc.appweb.repositories;

import com.learnsyc.appweb.models.Comentario;
import com.learnsyc.appweb.models.Hilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
