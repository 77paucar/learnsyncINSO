package com.learnsyc.appweb.repositories;

import com.learnsyc.appweb.models.FlashCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashCardRepository extends JpaRepository<FlashCard, Long> {
}
