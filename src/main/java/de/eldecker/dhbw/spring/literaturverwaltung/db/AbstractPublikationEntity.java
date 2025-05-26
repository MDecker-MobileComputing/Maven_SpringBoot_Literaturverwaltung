package de.eldecker.dhbw.spring.literaturverwaltung.db;

import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderBy;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;


/**
 * Abstrakte Oberklasse für Publikationen: Hat Autor(en), Titel und Jahreszahl der     
 * Veröffentlichung. Diese Klasse ist als Oberklasse für Vererbung, es kann aber 
 * keine Instanzen von ihr geben.
 * <br><br>
 * 
 * Für die Abbildung der Vererbungsbeziehung wurde die Strategie {@code SINGLE_TABLE}
 * gewählt. Die Objekte aller Unterklassen werden also in derselben Tabelle gespeichert,
 * aber je nach Unterklasse sind verschiedene Spalten gefüllt. 
 * In jeder Unterklasse sollte mit der Annotation {@code DiscriminatorValue} der Name
 * für den Wert in der Spalte {@code PublikationsTyp} gesetzt werden (sonst wird als
 * Wert einfach der Name der jeweiligen Entity-Klasse verwendet).
 */
@Entity
@Inheritance( strategy = SINGLE_TABLE )
@DiscriminatorColumn( name = "PublikationsTyp" )
public abstract class AbstractPublikationEntity {

    /** 
     * ID, muss Zugriffsmodifizierer {@code protected} haben, damit 
     * die ID von JPA auch für Unterklassen gesetzt werden kann. 
     */
    @Id
    @GeneratedValue( strategy = AUTO )
    protected Long id;
        
    /** Titel des Buchs oder Artikels. */
    @Size( min     = 5, 
           message = "Der Titel muss mindestens 5 Zeichen lang sein" )
    private String titel;

    /** Jahreszahl muss 4-stellig sein. */
    @Min( 1000 )
    @Max( 9999 )
    private int jahr;
    
    /** Liste der Autoren der Publikation. */
    private List<String> autoren = new ArrayList<>( 5 );
    
    /** Themen für dieses Buch, N:M-Beziehung. */
    @ManyToMany 
    @JoinTable( name               = "publikation_zu_thema",      
                joinColumns        = @JoinColumn( name = "publikation_id" ),
                inverseJoinColumns = @JoinColumn( name = "thema_id"       ) )
    @OrderBy( "thema ASC" )
    private Set<ThemaEntity> themen = new HashSet<>();    
    
        
    /** 
     * Default-Konstruktor. 
     */
    public AbstractPublikationEntity() {}
     
    
    /**
     * Konstruktor um alle Attribute außer ID zu setzen. 
     */
    public AbstractPublikationEntity( String titel, int jahr, String... autoren ) {
                
        this.titel = titel;
        this.jahr  = jahr;
        
        this.autoren = List.of( autoren );
    }
    
    
    public Long getId() {
        
        return id;
    }

    
    public String getTitel() {
        
        return titel;
    }

    public void setTitel( String titel ) {
        
        this.titel = titel;
    }

    public int getJahr() {
        
        return jahr;
    }

    public void setJahr( int jahr ) {
        
        this.jahr = jahr;
    }

    public List<String> getAutoren() {
        
        return autoren;
    }

    public void setAutoren( List<String> autoren ) {
        
        this.autoren = autoren;
    }
    
    
    /**
     * Hilfsmethode, um einzelnen Autor hinzuzufügen. 
     */
    public void addAutor( String autor ) {
        
        this.autoren.add( autor );
    }
    
        
    public Set<ThemaEntity> getThemen() {
        
        return themen;
    }


    public void setThemen( Set<ThemaEntity> themen ) {
        
        this.themen = themen;
    }

    
    /**
     * Hilfsmethode, um einzelne Thema hinzuzufügen
     */
    public void addThema( ThemaEntity thema ) {
        
        this.themen.add( thema );
    }


    @Override
    public String toString() {
        
        return autoren + ": " + titel + " (" + jahr + ")";
    }
    
}
