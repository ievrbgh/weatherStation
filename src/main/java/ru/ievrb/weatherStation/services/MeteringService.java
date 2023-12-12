package ru.ievrb.weatherStation.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ievrb.weatherStation.dto.MeteringDTO;
import ru.ievrb.weatherStation.models.Metering;
import ru.ievrb.weatherStation.models.Sensor;
import ru.ievrb.weatherStation.repositories.MeteringRepository;
import ru.ievrb.weatherStation.repositories.SensorRepository;
import ru.ievrb.weatherStation.utill.exceptions.InvalidTokenException;
import ru.ievrb.weatherStation.utill.exceptions.MeteringNotFoundException;

import java.util.List;

@Service
@Transactional
public class MeteringService {

    private final MeteringRepository mr;
    private final SensorRepository sr;

    private final ModelMapper mm;

    @Autowired
    public MeteringService(MeteringRepository mr, SensorRepository sr, ModelMapper mm) {
        this.mr = mr;
        this.sr = sr;
        this.mm = mm;
    }

    public Metering getById(int id){
        return mr.findById(id).orElseThrow(MeteringNotFoundException::new);
    }

    public List<Metering> getAll(){
        return mr.findAll();
    }

    public void save(MeteringDTO meteringDTO){
        Sensor sensor = sr.findByName(meteringDTO.getSensorName());
        Metering metering = mm.map(meteringDTO, Metering.class);
        metering.setSensor(sensor);
        mr.save(metering);
    }

    private Metering convertToMetering(MeteringDTO meteringDTO){
        return mm.map(meteringDTO, Metering.class);
    }

    public void checkSensorToken(MeteringDTO meteringDTO) {
        Sensor sensor = sr.findByName(meteringDTO.getSensorName());
        try {
            if(!sensor.getSensorToken().equals(meteringDTO.getToken())) {
                throw new InvalidTokenException();
            }
        } catch (NullPointerException e){
            throw new InvalidTokenException();
        }
    }


    public Integer getRainyDaysCount() {
        List<Metering> rainyDays = mr.findMeteringByRain(true);
        return (int) rainyDays.stream().count();
    }
}
