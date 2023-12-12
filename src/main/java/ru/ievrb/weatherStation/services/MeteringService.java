package ru.ievrb.weatherStation.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ievrb.weatherStation.models.Metering;
import ru.ievrb.weatherStation.repositories.MeteringRepository;
import ru.ievrb.weatherStation.repositories.SensorRepository;
import ru.ievrb.weatherStation.utill.exceptions.MeteringNotFoundException;

import java.util.List;

@Service
@Transactional
public class MeteringService {

    private final MeteringRepository mr;
    private final SensorRepository sr;

    @Autowired
    public MeteringService(MeteringRepository mr, SensorRepository sr) {
        this.mr = mr;
        this.sr = sr;
    }

    public Metering getById(int id){
        return mr.findById(id).orElseThrow(MeteringNotFoundException::new);
    }

    public List<Metering> getAll(){
        return mr.findAll();
    }

    public void save(Metering metering){
        expandDTO(metering);
        mr.save(metering);
    }

    private void expandDTO(Metering metering){
        //добавить сенсор
        metering.setSensor(sr.findById(1).orElse(null));
    }

}
