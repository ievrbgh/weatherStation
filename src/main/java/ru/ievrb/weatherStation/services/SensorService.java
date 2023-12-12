package ru.ievrb.weatherStation.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ievrb.weatherStation.models.Sensor;
import ru.ievrb.weatherStation.repositories.SensorRepository;

@Service
@Transactional
public class SensorService {

    private final SensorRepository sr;

    @Autowired
    public SensorService(SensorRepository sr) {
        this.sr = sr;
    }

    public Sensor getById(int id){
        return sr.findById(id).orElse(null);
    }

    public void save(Sensor sensor){
        sr.save(sensor);
    }
}
