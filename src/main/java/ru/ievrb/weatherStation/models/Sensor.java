package ru.ievrb.weatherStation.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="sensor_token")
    private String sensorToken;

    @OneToMany(mappedBy = "sensor")
    private List<Metering> meterings;


    public Sensor(String name) {
        this.name = name;
    }

    public Sensor() {

    }

    public String getSensorToken() {
        return sensorToken;
    }

    public void setSensorToken(String sensorToken) {
        this.sensorToken = sensorToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Metering> getMeterings() {
        return meterings;
    }

    public void setMeterings(List<Metering> meterings) {
        this.meterings = meterings;
    }
}
