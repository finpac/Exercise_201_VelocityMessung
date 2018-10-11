package Velocity;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Patrick Fink
 */
public class VelocityGUI extends javax.swing.JFrame
{
    private final VelocityTableModel inv;

    public VelocityGUI()
    {
        initComponents();
        createContextMenu();
        inv = new VelocityTableModel();
        table.setModel(inv);
        table.setDefaultRenderer(Object.class, new VelocityTableRenderer());
    }

    private void createContextMenu()
    {
        JPopupMenu pop = new JPopupMenu();

        JButton btnAdd = new JButton("Hinzufügen");
        JButton btnDelete = new JButton("Löschen");
        JButton btnDurchschnitt = new JButton("Durchschnitt");

        btnAdd.addActionListener(this::onAddMeasure);
        btnDelete.addActionListener(this::onRemoveMeasure);
        btnDurchschnitt.addActionListener(this::onDisplayAverage);

        pop.add(btnAdd);
        pop.add(btnDelete);
        pop.add(new JSeparator());
        pop.add(btnDurchschnitt);
        table.setComponentPopupMenu(pop);
        spScrollPane.setComponentPopupMenu(pop);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        plOverlay = new javax.swing.JPanel();
        spScrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        mbMainMenu = new javax.swing.JMenuBar();
        mnDatei = new javax.swing.JMenu();
        miSpeichern = new javax.swing.JMenuItem();
        miLaden = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Geschwindigkeitsmessung");

        plOverlay.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        plOverlay.setLayout(new java.awt.BorderLayout());

        spScrollPane.setViewportView(table);

        plOverlay.add(spScrollPane, java.awt.BorderLayout.CENTER);

        getContentPane().add(plOverlay, java.awt.BorderLayout.CENTER);

        mnDatei.setText("Datei");

        miSpeichern.setText("speichern");
        miSpeichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSaveData(evt);
            }
        });
        mnDatei.add(miSpeichern);

        miLaden.setText("laden");
        miLaden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onLoadData(evt);
            }
        });
        mnDatei.add(miLaden);

        mbMainMenu.add(mnDatei);

        setJMenuBar(mbMainMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onSaveData(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onSaveData
    {//GEN-HEADEREND:event_onSaveData
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Speichern");
        fc.setDialogType(JFileChooser.SAVE_DIALOG);
        fc.setFileFilter(new FileNameExtensionFilter("Binary File", "ser"));
        int x = fc.showSaveDialog(this);
        if (x == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                inv.save(fc.getSelectedFile());
                JOptionPane.showMessageDialog(this, "Die Daten wurden erfolgreich gespeichert.", "Erfolg!", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex)
            {
                JOptionPane.showMessageDialog(this, "Datei konnte nicht gespeichert werden", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_onSaveData

    private void onLoadData(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onLoadData
    {//GEN-HEADEREND:event_onLoadData
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Laden");
        fc.setDialogType(JFileChooser.OPEN_DIALOG);
        fc.setFileFilter(new FileNameExtensionFilter("Binary File", "ser"));
        int x = fc.showOpenDialog(this);
        if (x == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                inv.load(fc.getSelectedFile());
            } catch (IOException | ClassNotFoundException ex)
            {
                JOptionPane.showMessageDialog(this, "Datei konnte nicht geladen werden", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_onLoadData

    private void onDisplayAverage(ActionEvent event)
    {
        if (table.getSelectedRows().length > 0)
        {
            JOptionPane.showMessageDialog(this, "Der Durchschnitt beträgt: " + inv.getAverage(table.getSelectedRows()), "Durchschnitt", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void onAddMeasure(ActionEvent event)
    {
        VelocityDlg dlg = new VelocityDlg(this, true);
        dlg.setVisible(true);
        if (dlg.isOK())
        {
            inv.addElement(dlg.getElement());
        }
    }

    private void onRemoveMeasure(ActionEvent event)
    {
        if (table.getSelectedRows().length > 0)
        {
            inv.removeElements(table.getSelectedRows());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(VelocityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(VelocityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(VelocityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(VelocityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new VelocityGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar mbMainMenu;
    private javax.swing.JMenuItem miLaden;
    private javax.swing.JMenuItem miSpeichern;
    private javax.swing.JMenu mnDatei;
    private javax.swing.JPanel plOverlay;
    private javax.swing.JScrollPane spScrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}