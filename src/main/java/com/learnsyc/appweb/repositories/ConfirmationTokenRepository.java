package com.learnsyc.appweb.repositories;

import com.learnsyc.appweb.models.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    boolean existsConfirmationTokenByToken(String token);

    ConfirmationToken findByToken(String token);

    ConfirmationToken saveAndFlush(ConfirmationToken confirmationToken);
}
