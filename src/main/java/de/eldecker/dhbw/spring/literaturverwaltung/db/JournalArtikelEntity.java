package de.eldecker.dhbw.spring.literaturverwaltung.db;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;


/**
 * Entity-Klasse für einen Zeitschriftenartikel. 
 */
@Entity
@DiscriminatorValue( "JournalArtikel" )
public class JournalArtikelEntity extends AbstractPublikationEntity {

    @Size( min = 5, 
           message = "Zeitschriftennamen muss mindestens 5 Zeichen haben" ) 
    private String zeitschrift;
    
    /** Jahrgang (Volume) der Zeitschrift (wenn unbekannt, dann Jahreszahl) */
    @Min( 1 )
    @Max( 9999 )
    private int jahrgang;
    
    /** 
     * Ausgabe der Zeitschrift, z.B. {@code 3} für die März-Ausgabe einer
     * monatlich erscheinenden Zeitschrift.
     */
    @Min( 1 )
    private int ausgabe;
    
    /** Erste Seite des Artikels */
    @Min( 1 )
    private int seitennr;
    
    /**
     * Default-Konstruktor für JPA.
     */
    public JournalArtikelEntity() {}
    
    /** 
     * Konstruktor, um alle Attribute (auch die geerbten) zu setzen.
     */
    public JournalArtikelEntity( String titel, int jahr,
                                 String zeitschrift, int jahrgang, int ausgabe, int seitennr,
                                 String... autoren ) {
        
        super( titel, jahr, autoren );
        
        this.zeitschrift = zeitschrift;
        this.jahrgang    = jahrgang;
        this.ausgabe     = ausgabe;
        this.seitennr    = seitennr;
    }
            
    public String getZeitschrift() {
        
        return zeitschrift;
    }

    public void setZeitschrift( String zeitschrift ) {
        
        this.zeitschrift = zeitschrift;
    }

    public int getJahrgang() {
        
        return jahrgang;
    }

    public void setJahrgang( int jahrgang ) {
        
        this.jahrgang = jahrgang;
    }

    public int getAusgabe() {
        
        return ausgabe;
    }

    public void setAusgabe( int ausgabe ) {
        
        this.ausgabe = ausgabe;
    }

    public int getSeitennr() {
        
        return seitennr;
    }

    public void setSeitennr( int seitennr ) {
        
        this.seitennr = seitennr;
    }

    @Override
    public String toString() {
        
        return super.toString() + 
               ", Zeitschrift: " + zeitschrift + " (Vol. " + jahrgang + ", No " + ausgabe + ").";               
    }    
    
}
