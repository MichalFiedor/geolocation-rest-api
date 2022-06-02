package com.fiedormichal.geolocationrestapi.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
public class Geolocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @NotNull(message = "Device must contains id")
    private Device device;
    @NotNull(message = "Latitude can not be null")
    @OneToOne(cascade = CascadeType.PERSIST)
    @Valid
    private Latitude latitude;
    @NotNull(message = "Longitude can not be null")
    @OneToOne(cascade = CascadeType.PERSIST)
    @Valid
    private Longitude longitude;
    @Setter(value = AccessLevel.NONE)
    private String insertDate;
    private String updateDate;
    private Boolean isActive;

    @PrePersist
    private void setInsertDate(){
        this.insertDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.isActive = true;
    }

    public void setUpdateDate(LocalDateTime updateDate){
        this.updateDate = updateDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
