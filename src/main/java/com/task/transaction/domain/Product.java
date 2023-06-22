package com.task.transaction.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "product")
@Data
public class Product {
    @Id
    private String id;

    @Column(name = "name")
    private String name;
}
