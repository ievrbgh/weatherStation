package ru.ievrb.weatherStation.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="seensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Metering> meterings;

    public Sensor(String name) {
        this.name = name;
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
