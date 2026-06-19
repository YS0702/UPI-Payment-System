package com.example.upipayment.user.repository;

import com.example.upipayment.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByMobileNumber(String mobileNumber);
    Optional<User> findByUpiId(String upiId);
    boolean existsByMobileNumber(String mobileNumber);
    boolean existsByUpiId(String upiId);
}