package com.rewe.devices.dto;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SpecDTO {
    private Long id;
    private int width;
    private int height;
    private int depth;
}
