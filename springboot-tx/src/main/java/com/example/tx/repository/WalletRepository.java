package com.example.tx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.tx.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
	// 取得客戶餘額
	@Query(value = "select balance from wallet where username = :username", nativeQuery = true)
	Integer getWalletBalance(String username);
	
	@Modifying
	@Transactional
	// 更新餘額(目前餘額 - bookPrice)
	@Query(value = "update wallet set balance = balance - :bookPrice where username = :username", nativeQuery = true)
	void updateWalletBalance(Integer bookPrice, String username);
}
