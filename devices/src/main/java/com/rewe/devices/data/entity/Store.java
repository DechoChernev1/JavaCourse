package com.rewe.devices.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class Store extends BaseEntity {
    private String name;
    private String location;
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.DETACH)
    @JsonIgnore
    private Set<Device> devices;
}
