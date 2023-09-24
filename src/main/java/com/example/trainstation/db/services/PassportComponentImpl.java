package com.example.trainstation.db.services;

import com.example.trainstation.db.repo.PassportRepo;
import com.example.trainstation.entities.Passport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PassportComponentImpl implements PassportComponent{

    @Autowired
    PassportRepo repo;
    @Override
    public Passport save(Passport passport) {
        return repo.save(passport);
    }

    @Override
    public List<Passport> getAll() {
        return repo.findAll();
    }

    @Override
    public Passport findByIdOrDie(Long id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new Exception());
    }

    @Override
    public void update(Passport passport, Long id) {
         repo.update(
                 passport.getNumber(),
                 passport.getType(),
                 passport.getBoxingWeight(),
                 passport.getLoadCapacity(),
                 id);
    }

    @Override
    public Passport deleteById(Long id) throws Exception {
        Passport passport= findByIdOrDie(id);
        repo.delete(passport);
        return  passport;
    }
}
