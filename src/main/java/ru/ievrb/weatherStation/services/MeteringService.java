package ru.ievrb.weatherStation.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ievrb.weatherStation.models.Metering;
import ru.ievrb.weatherStation.repositories.MeteringRepository;

import java.util.List;

@Service
@Transactional
public class MeteringService {

    private final MeteringRepository mr;

    @Autowired
    public MeteringService(MeteringRepository mr) {
        this.mr = mr;
    }

    public Metering getById(int id){
        return mr.findById(id).orElse(null);
    }

    public List<Metering> getAll(){
        return mr.findAll();
    }

    public void save(Metering metering){
        mr.save(metering);
    }

}
