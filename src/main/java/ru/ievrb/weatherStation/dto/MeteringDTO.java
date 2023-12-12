package ru.ievrb.weatherStation.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class MeteringDTO {


    @NotNull(message = "date should be not null")
    private LocalDateTime date;

    @NotNull(message = "temp should be not null")
    @Min(value=-100, message = "incorrect temp")
    @Max(value=100, message = "incorrect temp")
    private Float temp;

    @NotNull(message = "rain should be not null")
    private Boolean rain;

    @Transient
    @NotNull(message = "sensor is not authenticate")
    private String sensorName;

    @Transient
    @NotNull(message = "sensor is not authenticate")
    private String token;

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public boolean isRain() {
        return rain;
    }

    public void setRain(boolean rain) {
        this.rain = rain;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getRain() {
        return rain;
    }

    public void setRain(Boolean rain) {
        this.rain = rain;
    }
}
