package ru.ievrb.weatherStation.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="metering")
public class Metering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="date")
    private LocalDateTime date;
    @Column(name="temp")
    private Float temp;
    @Column(name="rain")
    private boolean rain;

    @ManyToOne
    @JoinColumn(name="sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    public Metering(Float temp, boolean rain, Sensor sensor) {
        this.temp = temp;
        this.rain = rain;
        this.sensor = sensor;
    }

    public Metering() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
