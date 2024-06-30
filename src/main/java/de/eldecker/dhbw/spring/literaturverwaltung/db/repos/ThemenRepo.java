package de.eldecker.dhbw.spring.literaturverwaltung.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import de.eldecker.dhbw.spring.literaturverwaltung.db.ThemaEntity;

import java.util.List;


/**
 * Repo-Interface für Zugriff auf Tabelle mit wiss. Themen.
 */
public interface ThemenRepo extends JpaRepository<ThemaEntity, Long> {

    /**
     * Liste aller Themen.
     *  
     * @return Alle Themen, sortiert nach Thema.
     */
    List<ThemaEntity> findAllByOrderByThema();

}
