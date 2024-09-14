package org.example.model;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Builder
@Getter @Setter
@EqualsAndHashCode (exclude = { "id" })
@ToString
public class Car {
    private Integer id;
    private String model;
    private Integer year;
    private String carClass;
    private String modelCode;
}
