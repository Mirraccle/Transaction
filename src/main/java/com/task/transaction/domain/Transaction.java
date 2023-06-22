package com.task.transaction.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "transaction")
@Data
public class Transaction {

    @Id
    private String id;

    @Column(name = "carrier_id")
    private Long carrierId;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "offer_id")
    private String offerId;

    @Column(name = "score")
    private Integer score = 0;
}
