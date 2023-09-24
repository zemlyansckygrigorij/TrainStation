package com.example.trainstation.db.repo;

import com.example.trainstation.entities.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CargoRepo  extends JpaRepository<Cargo, Long> {
    @Modifying
    @Transactional
    @Query("update Cargo  c set c.code= ?1 , c.nameCargo = ?2  where c.id = ?3")
    void update(
            @Param("code") String code,
            @Param("name_cargo") String name_cargo,
            @Param("id") Long id);
}
