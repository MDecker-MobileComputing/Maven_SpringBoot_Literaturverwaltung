package de.eldecker.dhbw.spring.literaturverwaltung.db;

import jakarta.persistence.Embeddable;


/**
 * Objekt für ein Schlagwort zu einem wiss. Thema als Attribut einer Entity-Klasse. 
 * Für jedes Schlagwort muss der deutsche und der englische Begriff angegeben werden,
 * z.B. "Geschäftsprozess" und "Business Process".
 * <br><br>
 * 
 * Erklärung von <i>GH Copilot</i> zur Annotation {@code Embeddable}:
 * <i>"Embeddable entities are a feature of JPA (Java Persistence API) that allows 
 * you to define a class whose instances are stored as an intrinsic part of an owning  
 * entity. This is useful for representing composite values that don't have their  
 * own lifecycle or identity separate from the owning entity."</i>
 */
@Embeddable
public class SchlagwortEmbeddable {

    /** 
     * Schlagwort auf Deutsch, z.B. "Geschäftsprozess"; kann auch Englisch sein
     * wenn es kein passendes deutsches Wort gibt, z.B. für "Computer" oder
     * "Workflow".
     */
    private String deutsch;
    
    /** Schlagwort auf Englisch, z.B. "Business Process". */
    private String englisch;
    
    
    /**
     * Default-Konstruktor
     */
    public SchlagwortEmbeddable() {}

    
    /**
     * Convenience Konstruktor, um {@code englisch} als Begriff für Englisch und  
     * auch Deutsch zu setzen (wenn es kein passendes Deutsches Wort gibt, z.B. 
     * für "Debugger").
     */
    public SchlagwortEmbeddable( String englisch ) {
        
        this.deutsch = this.englisch = englisch;
    }
    
    
    /**
     * Konstruktor um alle Attribute zu setzen. 
     */
    public SchlagwortEmbeddable( String deutsch, String englisch ) {
        
        this.deutsch  = deutsch;
        this.englisch = englisch;
    }

    public String getDeutsch() {
        
        return deutsch;
    }

    public void setDeutsch( String deutsch ) {
        
        this.deutsch = deutsch;
    }

    public String getEnglisch() {
        
        return englisch;
    }

    public void setEnglisch( String englisch ) {
        
        this.englisch = englisch;
    }
    
    /**
     * Methode gibt String-Repräsentation des Objekts zurück.
     * 
     * @return String mit deutschem und englischem Begriff
     */
    @Override
    public String toString() {
        
        return "Schlagwort: " + deutsch + " (engl.: " + englisch + ")";
    }
    
}
