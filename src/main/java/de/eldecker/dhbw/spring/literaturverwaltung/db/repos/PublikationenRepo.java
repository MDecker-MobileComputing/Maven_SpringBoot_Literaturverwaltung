package de.eldecker.dhbw.spring.literaturverwaltung.db.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.eldecker.dhbw.spring.literaturverwaltung.db.AbstractPublikationEntity;


/**
 * Repo-Interface für DB-Zugriff auf Publikationen.
 */
public interface PublikationenRepo extends JpaRepository<AbstractPublikationEntity, Long> {

    /**
     * Liste aller Publikationen (verschiedene Typen) zurückgeben.
     *  
     * @return Alle Publikationen, sortiert nach aufsteigender Jahreszahl
     */
    List<AbstractPublikationEntity> findAllByOrderByJahrAsc();
    
    /**
     * Alle Publikationen zu einem bestimmten Thema zurückgeben.
     * 
     * @param themaId ID des Themas
     * 
     * @return Liste der Publikationen zu diesem Thema
     */
    List<AbstractPublikationEntity> findByThemenId( Long themaId );
}

