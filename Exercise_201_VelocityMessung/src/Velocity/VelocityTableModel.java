package Velocity;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Patrick Fink
 */
public class VelocityTableModel extends AbstractTableModel
{

    private final DAL dal;
    private ArrayList<Measurement> speicher;
    private final String[] columnNames =
    {
        "Datum", "Uhrzeit", "Kennzeichen", "Gemessen", "Erlaubt", "Übertretung"
    };

    public VelocityTableModel()
    {
        speicher = new ArrayList<>();
        dal = new DAL();
    }

    @Override
    public int getRowCount()
    {
        return speicher.size();
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int colum)
    {
        Measurement element = speicher.get(row);

        switch (colum)
        {
            case 0:
                DateTimeFormatter x = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                return element.getDatum().format(x);
            case 1:
                x = DateTimeFormatter.ofPattern("hh:mm");
                return element.getUhrzeit().format(x);
            case 2:
                return element.getKennzeichen();
            case 3:
                return element.getGemessen();
            case 4:
                return element.getErlaubt();
            case 5:
                return element.getUebertretung();
            default:
                return "-";
        }
    }

    @Override
    public String getColumnName(int i)
    {
        return columnNames[i];
    }

    public void addElement(Measurement m)
    {
        speicher.add(m);
        Collections.sort(speicher, new Comparator<Measurement>()
        {
            @Override
            public int compare(Measurement t, Measurement t1)
            {
                if (t.getUebertretung() >= t1.getUebertretung())
                {
                    return -1;
                } else if (t.getUebertretung() == t1.getUebertretung())
                {
                    return 0;
                } else
                {
                    return 1;
                }
            }
        });
        super.fireTableDataChanged();
    }

    public void removeElements(int... indexes)
    {
        if (indexes.length == 1)
        {
            speicher.remove(indexes[0]);
        } else
        {
            int verschoben = 0;
            for (int index : indexes)
            {
                speicher.remove(index - verschoben++);
            }
        }
        super.fireTableDataChanged();
    }

    public double getAverage(int... rows)
    {
        double durchschnitt;
        int count = 0;

        for (int index : rows)
        {
            count += speicher.get(index).getUebertretung();
        }

        durchschnitt = count / rows.length;
        return durchschnitt;
    }

    public void save(File selectedFile) throws IOException
    {
        dal.save(speicher, selectedFile);
        super.fireTableDataChanged();
    }

    public void load(File f) throws IOException, ClassNotFoundException
    {
        speicher = dal.load(f);
        super.fireTableDataChanged();
    }
}
