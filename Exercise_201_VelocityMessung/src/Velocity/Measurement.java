package Velocity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Patrick Fink
 */
public class Measurement implements Serializable
{

    private final LocalDate datum;
    private final LocalTime uhrzeit;
    private final String kennzeichen;
    private final int gemessen;
    private final int erlaubt;
    private final int uebertretung;

    
    public Measurement(LocalDate datum, LocalTime uhrzeit, String kennzeichen, int gemessen, int erlaubt)
    {
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.kennzeichen = kennzeichen;
        this.gemessen = gemessen;
        this.erlaubt = erlaubt;
        this.uebertretung = gemessen - erlaubt;
    }

    public LocalDate getDatum()
    {
        return datum;
    }

    public LocalTime getUhrzeit()
    {
        return uhrzeit;
    }

    public String getKennzeichen()
    {
        return kennzeichen;
    }

    public int getGemessen()
    {
        return gemessen;
    }

    public int getErlaubt()
    {
        return erlaubt;
    }

    public int getUebertretung()
    {
        return uebertretung;
    }
}
