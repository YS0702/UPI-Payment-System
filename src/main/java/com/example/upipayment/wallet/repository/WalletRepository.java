package com.example.upipayment.wallet.repository;



import com.example.upipayment.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByWalletId(String walletId);
    Optional<Wallet> findByUser_UserId(String userId);
}
