package com.ftn.sbnz.service.user.repository;
import com.ftn.sbnz.model.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    public Optional<AppUser> findByEmail(String email);

    public Boolean existsByEmail(String email);

}
