package com.example.trainstation.db.services;

import com.example.trainstation.entities.Passport;

import java.util.List;

/**
 * Компонент для работы с таблицей passport
 */
public interface PassportComponent {
    /**
     * Сохраняет данные паспорта вагона.
     *
     * @param passport паспорт вагона.
     * @return сохраненный паспорт вагона.
     */
    Passport save(Passport passport);
    /**
     * Возвращает все паспорта вагонов.
     *
     * @return список паспортов вагонов.
     */
    List<Passport> getAll();
    /**
     * Ищет паспорт по идентификатору.
     *
     * @param id  идентификатор вагона.
     * @return паспорт вагона.
     */
    Passport findByIdOrDie(Long id) throws Exception;
    /**
     * Обновляет паспорт по идентификатору.
     *
     * @param passport паспорт вагона.
     * @param id идентификатор вагона.
     */
    void update(Passport passport,Long id);
    /**
     * Удаляет паспорт вагона по идентификатору.
     * @param id идентификатор вагона.
     * @return паспорт вагона.
     */
    Passport deleteById(Long id) throws Exception;
}
