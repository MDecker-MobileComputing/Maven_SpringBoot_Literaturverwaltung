package de.eldecker.dhbw.spring.literaturverwaltung.logik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import de.eldecker.dhbw.spring.literaturverwaltung.db.AbstractPublikationEntity;
import de.eldecker.dhbw.spring.literaturverwaltung.db.BuchEntity;
import de.eldecker.dhbw.spring.literaturverwaltung.db.JournalArtikelEntity;
import de.eldecker.dhbw.spring.literaturverwaltung.db.SchlagwortEmbeddable;
import de.eldecker.dhbw.spring.literaturverwaltung.db.ThemaEntity;
import de.eldecker.dhbw.spring.literaturverwaltung.db.repos.PublikationenRepo;
import de.eldecker.dhbw.spring.literaturverwaltung.db.repos.ThemenRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Bean um bei leeren DB-Tabellen den Demo-Content zu laden.
 */
@Component
public class DemoDatenImportApplicationRunner implements ApplicationRunner {

    private final static Logger LOG = LoggerFactory.getLogger( DemoDatenImportApplicationRunner.class );

    /** Repo-Bean für Zugriff auf Tabelle mit Themen. */
    @Autowired
    private ThemenRepo _themenRepo;

    /**
     * Repo-Bean um Objekte der verschiedenen Unterklasse von {@link AbstractPublikationEntity}
     * zu speichern.
     */
    @Autowired
    private PublikationenRepo _publikationenRepo;


    /**
     * Diese Methode wird unmittelbar nach Hochfahren der Anwendung ausgeführt und
     * lädt bei Bedarf den Demo-Content.
     */
    public void run( ApplicationArguments args ) throws Exception {

        final long themenAnzahlAlt = _themenRepo.count();
        if ( themenAnzahlAlt > 0 ) {

            LOG.info( "Es sind schon {} Themen in der DB gespeichert, also wird kein Demo-Content geladen.",
                      themenAnzahlAlt );
        } else {

            LOG.info( "Keine Themen in Datenbank gefunden, lade jetzt Demo-Content." );

            ThemaEntity themaBPM          = erzeugeThemaGeschaeftsProzessManagement();
            ThemaEntity themaSpringBoot   = erzeugeThemaSpringBoot();
            ThemaEntity themaMicroservice = erzeugeThemaMicroservice();

            erzeugeBuch1( themaSpringBoot );
            erzeugeBuch2( themaSpringBoot, themaMicroservice );

            erzeugeJournalartikel1( themaBPM );
            erzeugeJournalartikel2( themaBPM );
            erzeugeJournalartikel3( themaMicroservice );

            final long themenAnzahlNeu = _themenRepo.count();
            LOG.info( "Demo-Daten geladen, es sind jetzt {} Themen in der Datenbank.", themenAnzahlNeu );
        }
    }


    /**
     * Thema "Geschäftsprozesse" mit einigen Schlagwörtern in DB anlegen.
     */
    private ThemaEntity erzeugeThemaGeschaeftsProzessManagement() {

        SchlagwortEmbeddable sw1 = new SchlagwortEmbeddable( "Prozessmanagement"  , "Process Management" );
        SchlagwortEmbeddable sw2 = new SchlagwortEmbeddable( "Prozessmodellierung", "Process Modelling"  );
        SchlagwortEmbeddable sw3 = new SchlagwortEmbeddable( "Geschäftsprozess"   , "Business Process"   );

        ThemaEntity thema = new ThemaEntity( "Geschäftsprozesse", sw1, sw2, sw3 );
        _themenRepo.save( thema );

        return thema;
    }


    /**
     * Thema "Microservices" in DB anlegen.
     */
    private ThemaEntity erzeugeThemaMicroservice() {

        ThemaEntity thema = new ThemaEntity( "Microservices" );
        _themenRepo.save( thema );

        return thema;
    }


    /**
     * Thema "Spring Boot" mit einigen Schlagwörtern in DB anlegen.
     */
    private ThemaEntity erzeugeThemaSpringBoot() {

        SchlagwortEmbeddable sw1 = new SchlagwortEmbeddable( "Spring"   , "Spring "    );
        SchlagwortEmbeddable sw2 = new SchlagwortEmbeddable( "Java"     , "Java"       );
        SchlagwortEmbeddable sw3 = new SchlagwortEmbeddable( "Framework", "Rahmenwerk" );

        ThemaEntity thema = new ThemaEntity( "Spring Boot", sw1, sw2, sw3 );
        _themenRepo.save( thema );

        return thema;
    }


    /**
     * Eintrag für
     * <a href="https://www.amazon.de/dp/3836290499">dieses Buch</a>
     * über <i>Spring Boot</i> erzeugen.
     */
    private void erzeugeBuch1( ThemaEntity themaSpringBoot ) {

        BuchEntity buch =
                new BuchEntity( "Spring Boot 3 und Spring Framework 6", 2023,
                                "3836290499", "978-3836290494",
                                "Rheinwerk Computing", "Bonn",
                                "Christian Ullenboom" );

        buch.addThema( themaSpringBoot );

        _publikationenRepo.save( buch );
    }


    /**
     * Eintrag für
     * <a href="https://www.amazon.de/dp/1484287916/">dieses Buch</a>
     * über <i>Spring Boot</i> und Microservices erzeugen.
     */
    private void erzeugeBuch2( ThemaEntity themaSpringBoot, ThemaEntity themaMicroservices ) {

        BuchEntity buch =
                new BuchEntity( "Beginning Spring Boot 3: Build Dynamic Cloud-Native Java Applications and Microservices", 2022,
                                "1484287916", "978-1484287910",
                                "Apress", "Berkeley, CA, USA",
                                "K. Siva Prasad Reddy", "Sai Upadhyayula" );

        buch.addThema( themaSpringBoot    );
        buch.addThema( themaMicroservices );

        _publikationenRepo.save( buch );
    }


    /**
     * Eintrag für
     * <a href="https://link.springer.com/article/10.1023/A:1022883727209">diesen Zeitschriftenartikel</a>.
     */
    private void erzeugeJournalartikel1( ThemaEntity themaBPM ) {

        JournalArtikelEntity artikel =
                new JournalArtikelEntity( "Workflow Patterns", 2003,
                                          "Distributed and Parallel Databases", 14, 3, 5,
                                          "W.M.P. van der Aalst", "A.H.M. ter Hofstede", "B. Kiepuszewski", "A.P. Barros" );
        artikel.addThema( themaBPM );

        _publikationenRepo.save( artikel );
    }


    /**
     * Eintrag für
     * <a href="https://www.emerald.com/insight/content/doi/10.1108/14637150910960594/full/html">diesen Zeitschriftenartikel</a>.
     */
    private void erzeugeJournalartikel2( ThemaEntity themaBPM ) {

        JournalArtikelEntity artikel =
                new JournalArtikelEntity( "Procedure and guidelines for evaluation of BPM methodologies", 2009,
                                          "Business Process Management Journal", 15, 3, 336,
                                          "Agata Filipowska", "Monika Kaczmarek", "Marek Kowalkiewicz", "Xuan Zhou", "Matthias Born" );
        artikel.addThema( themaBPM );

        _publikationenRepo.save( artikel );
    }

    /**
     * Eintrag für
     * <a href="https://ieeexplore.ieee.org/abstract/document/7030212">diesen Zeitschriftenartikel</a>.
     */
    private void erzeugeJournalartikel3( ThemaEntity themaMicroservice ) {

        JournalArtikelEntity artikel =
                new JournalArtikelEntity( "Microservices", 2015,
                                          "IEEE Software", 32, 1, 116,
                                          "Johannes Thönes" );
        artikel.addThema( themaMicroservice );

        _publikationenRepo.save( artikel );
    }

}
