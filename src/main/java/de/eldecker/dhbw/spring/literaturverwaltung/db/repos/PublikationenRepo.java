package de.eldecker.dhbw.spring.literaturverwaltung.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import de.eldecker.dhbw.spring.literaturverwaltung.db.AbstractPublikationEntity;


public interface PublikationenRepo extends JpaRepository<AbstractPublikationEntity, Long> {

}
