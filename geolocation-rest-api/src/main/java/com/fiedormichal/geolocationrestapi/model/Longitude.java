package com.fiedormichal.geolocationrestapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
public class Longitude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Longitude value can not be null")
    @Min(value = -180, message = "Longitude must be greater than -180")
    @Max(value = 180, message = "Longitude can not be greater than 180")
    @Digits(integer = 11, fraction = 8, message = "Longitude may have 8 decimal places")
    private Double longitudeValue;
    @NotNull(message = "Longitude world side can not be null")
    @Pattern(regexp = "^[E|W]{1}$", message ="Longitude must be E or W")
    @Column(length = 1)
    private String worldSide;
}
