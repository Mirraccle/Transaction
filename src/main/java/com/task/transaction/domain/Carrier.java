package com.task.transaction.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "carrier")
@Data
public class Carrier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "carrier_region",
            joinColumns = { @JoinColumn(name = "carrier_id") },
            inverseJoinColumns = { @JoinColumn(name = "region_id") })
    @JsonIgnore
    private Set<Region> regions = new HashSet<>();
}
