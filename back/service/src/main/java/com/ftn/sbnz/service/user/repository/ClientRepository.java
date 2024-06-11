package com.ftn.sbnz.service.user.repository;

import com.ftn.sbnz.model.user.AppUser;
import com.ftn.sbnz.model.user.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);

    Boolean existsByEmail(String email);

    List<Client> findClientsByServiceAreaId(Long serviceAreaId);
}
