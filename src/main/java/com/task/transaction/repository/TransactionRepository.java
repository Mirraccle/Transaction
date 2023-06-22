package com.task.transaction.repository;

import com.task.transaction.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    Boolean existsByIdOrOfferIdOrRequestId(String id, String offerId, String requestId);

    Boolean existsByRequestId(String requestId);

    List<Transaction> findAllByCarrierId(Long carrierId);

}
