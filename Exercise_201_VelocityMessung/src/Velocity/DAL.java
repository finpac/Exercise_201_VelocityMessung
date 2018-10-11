package Velocity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Partrick Fink
 */
public class DAL
{

    /**
     * Methode zum Speichern einer Save-Datei
     *
     * @param liste Liste die gespeichert werden soll
     * @param file Der Pfad zur Datei, in die gespeichert werden soll
     * @throws IOException Wenn nicht gespeichert werden kann
     */
    public void save(ArrayList<Measurement> liste, File file) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(liste);
        oos.close();
        fos.close();
    }

    /**
     * Methode zum laden eines gespeicherten Files
     *
     * @param file Der Pfad zur Datei, die geladen werden soll
     * @return Eine beschriebene Liste mit Werten
     * @throws IOException Wenn nicht geladen werden kann
     * @throws ClassNotFoundException Wenn nicht geladen werden kann
     */
    public ArrayList<Measurement> load(File file) throws IOException, ClassNotFoundException
    {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Measurement> liste = (ArrayList<Measurement>) ois.readObject();
        ois.close();
        fis.close();
        return liste;
    }
}
