package com.example.trainstation.db.repo;

import com.example.trainstation.entities.Passport;
import com.example.trainstation.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Слой обработки
 */
@Repository
public interface PassportRepo  extends JpaRepository<Passport, Long> {
     @Modifying
     @Transactional
     @Query("update Passport p set p.number= ?1 , p.type = ?2, p.boxingWeight = ?3, p.loadCapacity = ?4  where p.id = ?5")
     void update(
             @Param("number") int number,
             @Param("type") Type type,
             @Param("boxingWeight") double boxingWeight,
             @Param("loadCapacity") double loadCapacity,
             @Param("id") Long id);
}


