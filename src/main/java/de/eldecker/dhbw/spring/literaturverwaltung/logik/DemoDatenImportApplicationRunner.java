package de.eldecker.dhbw.spring.literaturverwaltung.logik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import de.eldecker.dhbw.spring.literaturverwaltung.db.AbstractPublikationEntity;
import de.eldecker.dhbw.spring.literaturverwaltung.db.BuchPublikation;
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
                         
            ThemaEntity themaBPM        = erzeugeThemaGeschaeftsProzessManagement();
            ThemaEntity themaSpringBoot = erzeugeThemaSpringBoot();
                                    
            erzeugePublikationBuch1( themaSpringBoot );
            
            final long themenAnzahlNeu = _themenRepo.count();
            LOG.info( "Demo-Daten geladen, es sind jetzt {} Themen in der Datenbank.", themenAnzahlNeu );
        }
    }
            
    
    /**
     * Thema "Geschäftsprozesse" mit einigen Schlagwörtern in DB anlegen.
     */
    private ThemaEntity erzeugeThemaGeschaeftsProzessManagement() {
    
        SchlagwortEmbeddable sw1 = new SchlagwortEmbeddable( "Prozessmanagement"  , "Geschäftsprozess"  );
        SchlagwortEmbeddable sw2 = new SchlagwortEmbeddable( "Prozessmodellierung", "Process Modelling" );
        SchlagwortEmbeddable sw3 = new SchlagwortEmbeddable( "Workflow"           , "Workflow"          );
        
        ThemaEntity thema = new ThemaEntity( "Geschäftsprozesse", sw1, sw2, sw3 );
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
     * Eintrag über
     * <a href="https://www.amazon.de/dp/3836290499">dieses Buch</a>
     * über <i>Spring Boot</i> erzeugen.
     */
    private void erzeugePublikationBuch1( ThemaEntity springBootThema ) {
        
        BuchPublikation buch = 
                new BuchPublikation( "Spring Boot 3 und Spring Framework 6", 2023,
                                     "3836290499", "978-3836290494",
                                     "Rheinwerk Computing", "Bonn",
                                     "Christian Ullenboom" );

        buch.addThema( springBootThema );
        
        _publikationenRepo.save( buch );
    }
    
}
