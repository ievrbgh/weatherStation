package ru.ievrb.weatherStation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ievrb.weatherStation.models.Metering;

@Repository
public interface MeteringRepository extends JpaRepository<Metering, Integer> {
}
