package com.fiedormichal.geolocationrestapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Device name can not be null")
    private String name;
    @ManyToOne
    @NotNull(message = "Device type can not be null")
    @Valid
    private DeviceType deviceType;
}
