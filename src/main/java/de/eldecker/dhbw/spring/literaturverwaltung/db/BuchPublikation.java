package de.eldecker.dhbw.spring.literaturverwaltung.db;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;


/** 
 * Unterklasse von {@link AbstractPublikationEntity} für (Fach-/Lehr-)Bücher. 
 * <br><br>
 * 
 * Ein Buch kann sowohl eine ISBN10 als auch eine ISBN13 haben, z.B. 
 * <a href="https://www.amazon.de/dp/0262033844">Cormen et al.: Introduction to Algorithms</a>.
 */
@Entity
@DiscriminatorValue( "Buch" )
public class BuchPublikation extends AbstractPublikationEntity {

    /**
     * International Standard Book Number, ISBN-10, z.B. {@code 9780262033848}.
     */
    private String isbn10;

    /**
     * International Standard Book Number, ISBN-13, z.B. {@code 978-0262033848}.
     */
    private String isbn13;
        
    @Size( min = 3, 
           message = "Der Verlagsname muss mindestens drei Zeichen haben" )
    private String verlag;

    @Size( min = 3, 
           message = "Der Verlagsort muss mindestens drei Zeichen haben" )   
    private String verlagOrt;
    
    /**
     * Default-Konstruktor für JPA.
     */
    public BuchPublikation() {}        

    
    /**
     * Konstruktor um alle Attribute zu setzen. 
     */
    public BuchPublikation( String isbn10, String isbn13, String verlag, String verlagOrt ) {
        
        this.isbn10    = isbn10;
        this.isbn13    = isbn13;
        this.verlag    = verlag;
        this.verlagOrt = verlagOrt;
    }


    public String getIsbn10() {
        
        return isbn10;
    }


    public void setIsbn10( String isbn10 ) {
        
        this.isbn10 = isbn10;
    }


    public String getIsbn13() {
        
        return isbn13;
    }


    public void setIsbn13( String isbn13 ) {
        
        this.isbn13 = isbn13;
    }


    public String getVerlag() {
        
        return verlag;
    }


    public void setVerlag( String verlag ) {
        
        this.verlag = verlag;
    }


    public String getVerlagOrt() {
        
        return verlagOrt;
    }


    public void setVerlagOrt( String verlagOrt ) {
        
        this.verlagOrt = verlagOrt;
    }        
    
    @Override
    public String toString() {
        
        return "Buch: " + super.toString() + ", Verlag: " + verlag + " (" + verlagOrt + ")";               
    }
    
}
