package de.eldecker.dhbw.spring.literaturverwaltung.web;

import static java.lang.String.format;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import de.eldecker.dhbw.spring.literaturverwaltung.db.AbstractPublikationEntity;
import de.eldecker.dhbw.spring.literaturverwaltung.db.ThemaEntity;
import de.eldecker.dhbw.spring.literaturverwaltung.db.repos.PublikationenRepo;
import de.eldecker.dhbw.spring.literaturverwaltung.db.repos.ThemenRepo;

import java.util.List;
import java.util.Optional;

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

    /** Repo-Bean für Zugriff auf alle Themen */
    @Autowired
    private ThemenRepo _themenRepo;


    /**
     * Seite mit allen Publikationen sortiert nach Jahreszahl zurückgeben.
     *
     * @param model Objekt für Platzhalterwerte in Template
     *
     * @return Name der Template-Datei {@code liste-alle-publikationen.html}
     */
    @GetMapping( "/liste-alle-publikationen" )
    public String allePublikationen( Model model ) {

        List<AbstractPublikationEntity> liste = _publikationenRepo.findAllByOrderByJahrAsc();

        model.addAttribute( "liste", liste );

        return "liste-alle-publikationen";
    }


    /**
     * Seite mit Detailansicht anzeigen.
     *
     * @param id Pfadparameter mit ID der Publikation
     *
     * @param model Objekt für Platzhalterwerte in Template
     *
     * @return Name der Template-Datei {@code detail-publikation.html}
     */
    @GetMapping( "/publikation/{id}" )
    public String detailsPublikation( @PathVariable("id") Long id,
                                      Model model ) {

        final Optional<AbstractPublikationEntity> publikationOptional =
                                                    _publikationenRepo.findById( id );
        if ( publikationOptional.isEmpty() ) {

            String fehlertext = format( "Keine Publikation mit ID=%d gefunden.", id );

            LOG.warn( fehlertext );

            model.addAttribute( "fehlermeldung", fehlertext );
            return "fehlerseite";
        }

        final AbstractPublikationEntity publikation = publikationOptional.get();
        model.addAttribute( "publikation", publikation );

        return "detail-publikation";
    }


    /**
     * Seite mit Detailansicht für ein Thema anzeigen.
     *
     * @param id Pfadparameter mit ID des Themas
     *
     * @param model Objekt für Platzhalterwerte in Template
     *
     * @return Name der Template-Datei {@code detail-thema.html}
     */
    @GetMapping( "/thema/{id}" )
    public String detailsThema( @PathVariable("id") Long id,
                                Model model ) {

        final Optional<ThemaEntity> themaOptional = _themenRepo.findById( id );
        if ( themaOptional.isEmpty() ) {

            String fehlertext = format( "Kein Thema mit ID=%d gefunden.", id );

            LOG.warn( fehlertext );

            model.addAttribute( "fehlermeldung", fehlertext );
            return "fehlerseite";
        }

        final ThemaEntity thema = themaOptional.get();

        model.addAttribute( "thema", thema );

        return "detail-thema";
    }

}
