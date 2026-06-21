package com.example.upipayment.wallet.entity;

import com.example.upipayment.common.enums.WalletStatus;
import com.example.upipayment.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallets", uniqueConstraints = @UniqueConstraint(columnNames = "walletId"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Wallet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String walletId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private WalletStatus status;

    @Version
    private Long version;

    @Column(nullable=false, updatable = false)

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
