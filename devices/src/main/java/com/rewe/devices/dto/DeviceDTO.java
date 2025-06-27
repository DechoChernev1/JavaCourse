package com.rewe.devices.dto;

import com.rewe.devices.data.entity.Spec;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DeviceDTO {
    private Long id;

    private String name;

    private String specialty;

    private String location;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;

    private List<Spec> specs;
}
