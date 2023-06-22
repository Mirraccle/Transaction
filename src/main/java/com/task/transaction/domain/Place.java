package com.task.transaction.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "place")
@Data
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "region_id")
    private Long regionId;
}
