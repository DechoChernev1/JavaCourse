package com.rewe.devices.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StoreDTO {
    private Long id;

    @NotBlank(message = "Store name is mandatory")
    @Size(max = 100, message = "Store name must be less than 100 characters")
    private String name;

    @NotBlank(message = "Location is mandatory")
    @Size(max = 100, message = "Location must be less than 100 characters")
    private String location;

    // Relations
    private List<DeviceDTO> devices;
}
