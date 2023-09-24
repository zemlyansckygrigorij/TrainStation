package com.example.trainstation.db.services;

import com.example.trainstation.entities.Cargo;

import java.util.List;
/**
 * Компонент для работы с таблицей cargo
 */
public interface CargoComponent {
    /**
     * Сохраняет данные груза.
     *
     * @param cargo груз.
     * @return сохраненный груз.
     */
    Cargo save(Cargo cargo);
    /**
     * Возвращает все грузы.
     *
     * @return список грузов.
     */
    List<Cargo> getAll();
    /**
     * Ищет груз по идентификатору.
     *
     * @param id  идентификатор груза.
     * @return груз.
     */
    Cargo findById(Long id) throws Exception;
    /**
     * Обновляет груз по идентификатору.
     *
     * @param cargo груз.
     * @param id идентификатор груза.
     */
    void update(Cargo cargo, Long id);
    /**
     * Удаляет груз по идентификатору.
     * @param id идентификатор груза.
     * @return груз.
     */
    Cargo deleteById(Long id) throws Exception;

}
