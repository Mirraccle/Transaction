package com.task.transaction.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "request")
@Data
public class Request {

    @Id
    private String id;

    @Column
    private String productId;

    @Column
    private Long placeId;
}
