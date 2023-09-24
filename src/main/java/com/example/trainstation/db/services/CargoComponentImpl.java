package com.example.trainstation.db.services;

import com.example.trainstation.db.repo.CargoRepo;
import com.example.trainstation.entities.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CargoComponentImpl implements CargoComponent{
    @Autowired
    CargoRepo cargoRepo;
    @Override
    public Cargo save(Cargo cargo) {
        return cargoRepo.save(cargo);
    }

    @Override
    public List<Cargo> getAll() {
        return cargoRepo.findAll();
    }

    @Override
    public Cargo findById(Long id) throws Exception {
        return cargoRepo.findById(id).orElseThrow(() -> new Exception());
    }

    @Override
    public void update(Cargo cargo, Long id) {
        cargoRepo.update( cargo.getCode(), cargo.getNameCargo(), id);
    }

    @Override
    public Cargo deleteById(Long id) throws Exception {
        Cargo cargo = findById(id);
        cargoRepo.deleteById(id);
        return cargo;
    }
}
