package de.eldecker.dhbw.spring.literaturverwaltung.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.eldecker.dhbw.spring.literaturverwaltung.db.AbstractPublikationEntity;
import de.eldecker.dhbw.spring.literaturverwaltung.db.repos.PublikationenRepo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Klasse mit Controller-Methoden für Thymeleaf-Templates. 
 */
@Controller
@RequestMapping( "/app" )
public class ThymeleafController {

    private static final Logger LOG = LoggerFactory.getLogger( ThymeleafController.class );
    
    /** Repo-Bean für Zugriff auf alle Publikationen */
    @Autowired
    private PublikationenRepo _publikationenRepo;
    
    
    /**
     * Seite mit allen Publikationen sortiert nach Jahreszahl zurückgeben.
     * 
     * @param model Objekt für Platzhalterwerte in Template
     * 
     * @return Name der Template-Datei.
     */
    @GetMapping( "/liste-alle-publikationen" )
    public String allePublikationen( Model model ) {
        
        List<AbstractPublikationEntity> liste = _publikationenRepo.findAllByOrderByJahrAsc();
        
        model.addAttribute( "liste", liste );
        
        return "liste-alle-publikationen";
    }
}
