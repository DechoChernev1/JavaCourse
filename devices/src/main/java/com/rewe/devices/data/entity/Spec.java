package com.rewe.devices.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class Spec extends BaseEntity {
    private int width;
    private int height;
    private int depth;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Device device;
}
