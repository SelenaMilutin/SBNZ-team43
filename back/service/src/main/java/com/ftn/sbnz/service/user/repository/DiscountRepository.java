package com.ftn.sbnz.service.user.repository;

import com.ftn.sbnz.model.user.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
