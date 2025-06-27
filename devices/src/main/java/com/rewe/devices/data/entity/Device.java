package com.rewe.devices.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class Device extends BaseEntity {

    private String name;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "location")
    private String location;

    @Column(name = "created")
    private LocalDate created;

    @OneToMany(mappedBy = "device", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Spec> specs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Store store;
}
