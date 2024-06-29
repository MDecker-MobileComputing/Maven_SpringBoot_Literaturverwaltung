package de.eldecker.dhbw.spring.literaturverwaltung.db.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.eldecker.dhbw.spring.literaturverwaltung.db.AbstractPublikationEntity;


/**
 * Repo-Interface für DB-Zugriff auf Publikationen.
 */
public interface PublikationenRepo extends JpaRepository<AbstractPublikationEntity, Long> {

    /**
     * Liste aller Publikationen (verschiedene Typen).
     *  
     * @return Alle Publikationen, sortiert nach aufsteigender Jahreszahl
     */
    List<AbstractPublikationEntity> findAllByOrderByJahrAsc();
}
