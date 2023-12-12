package ru.ievrb.weatherStation.dto;

import jakarta.validation.constraints.NotEmpty;

public class SensorDTO {

    @NotEmpty(message = "Sensor name should be not empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
