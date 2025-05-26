package de.eldecker.dhbw.spring.literaturverwaltung.db;

import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;


/**
 * Entity-Klasse für wissenschaftliche Themen, denen Publikationen zugeordnet werden.
 */
@Entity
@Table(name = "THEMA")
public class ThemaEntity extends AbstractPersistable<Long> {

    /** Wissenschaftliches Thema, z.B. "Workflow Management" */
    private String thema;

    /**
     * Schlagwörter für dieses Thema; diese werden in der Tabelle
     * {@code SCHLAGWOERTER} abgelegt, welche die Fremdschlüsselspalte
     * {@code THEMA_ID} hat.
     */
    @ElementCollection
    @CollectionTable( name        = "schlagwoerter",
                      joinColumns = @JoinColumn( name="thema_id") )
    private Set<SchlagwortEmbeddable> schlagwoerterSet = new HashSet<>();

    /** Publikationen, die dieses Thema haben. */
    @ManyToMany(mappedBy = "themen")
    private Set<AbstractPublikationEntity> publikationen;

    
    /** Konstruktor für JPA */
    public ThemaEntity() {}

    
    /**
     * Konstruktor, um skalare Attribute zu setzen.
     */
    public ThemaEntity( String thema ) {

        this.thema = thema;
    }

    
    /**
     * Konstruktor um alle Non-ID-Attribute zu setzen.
     */
    public ThemaEntity( String thema, SchlagwortEmbeddable... schlagwoerter ) {

        this.thema = thema;
        List<SchlagwortEmbeddable> schlagwortListe = asList( schlagwoerter );
        this.schlagwoerterSet.addAll( schlagwortListe );
    }

    
    public String getThema() {

        return thema;
    }

    
    public void setThema( String thema ) {

        this.thema = thema;
    }

    
    public Set<SchlagwortEmbeddable> getSchlagwoerterSet() {

        return schlagwoerterSet;
    }

    
    public void setSchlagwoerterSet( Set<SchlagwortEmbeddable> schlagwoerterSet ) {

        this.schlagwoerterSet = schlagwoerterSet;
    }


    /**
     * Hilfsmethode, um der Menge der Schlagworte ein Thema hinzuzufügen.
     */
    public void addSchlagwort( SchlagwortEmbeddable schlagwort ) {

        schlagwoerterSet.add(schlagwort);
    }


    /**
     * Methode gibt nur Thema zurück.
     *
     * @return Wert von {@link #getThema()}, z.B. "Workflow Management"
     */
    @Override
    public String toString() {

        return getThema();
    }

}