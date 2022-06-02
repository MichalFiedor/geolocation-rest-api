package com.fiedormichal.geolocationrestapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
public class Latitude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Latitude value can not be null")
    @Min(value = -90, message = "Latitude must be greater than -90")
    @Max(value = 90, message = "Latitude can not be greater than 90")
    @Digits(integer = 10, fraction = 8, message = "Latitude may have 8 decimal places")
    private Double latitudeValue;
    @NotNull(message = "Latitude world side can not be null")
    @Pattern(regexp = "^[N|S]{1}$", message ="Latitude must be N or S")
    @Column(length = 1)
    private String worldSide;
}
