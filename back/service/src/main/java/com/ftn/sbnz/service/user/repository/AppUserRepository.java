package com.ftn.sbnz.service.user.repository;
import com.ftn.sbnz.model.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
