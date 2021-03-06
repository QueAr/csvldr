/*
 * DatabasePanel.java
 *
 * --- Last Update: 8/22/2013 11:48 PM ---
 *
 * Update Notes 8/22/2013 11:48 PM by Adrian Wijasa:
 * If Databases.xml exists and tnsnames.ora exists, change it to tnsnames.old and save its entries into Databases.xml
 *
 * Update Notes 8/7/2013 by Bryan Pauquette:
 * Changed TNS references to Database references
 * Present an error message when a new Database entry is incomplete
 *
 * Update Notes 4/18/2010 6:55 PM by Adrian Wijasa:
 * This class can now work with Java 5.
 *
 * Update Notes 3/22/2010 4:29 PM by Adrian Wijasa:
 * Re-adjusted the form design so that it will look good under Java Metal UI.
 *
 * Created on March 1, 2007, 10:49 AM
 *
 * CSV Loader
 * Copyright 2007, 2009, 2010 Adrian Wijasa
 *
 * This file is part of CSV Loader.
 *
 * CSV Loader is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CSV Loader is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CSV Loader.  If not, see <http://www.gnu.org/licenses/>.
 */

package panels;

import config.ConfigException;
import config.DatabaseConfiguration;
import forms.Main;
import forms.TNSExceptionFrame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import tns.TNSException;
import tns.TNSNamesReader;
import tns.TNSNamesWriter;

/**
 * TNS Maintenance Panel
 * 
 * @author awijasa
 */
public class DatabasePanel extends javax.swing.JPanel {

    /** Creates new form DatabasePanel */
    public DatabasePanel( Main main ) {
        this.main = main;
        databaseConfigReader = new DatabaseConfiguration();
        initComponents();
        initColumnNames();
        initDatabaseTable();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        databaseScrollPane = new javax.swing.JScrollPane();
        databaseTable = new javax.swing.JTable();
        closeLabel = new javax.swing.JLabel();
        saveLabel = new javax.swing.JLabel();
        resetLabel = new javax.swing.JLabel();
        addLabel = new javax.swing.JLabel();
        removeLabel = new javax.swing.JLabel();

        databaseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Host", "Port", "SID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        databaseScrollPane.setViewportView(databaseTable);

        closeLabel.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        closeLabel.setForeground(new java.awt.Color(0, 102, 255));
        closeLabel.setText("Close");
        closeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeLabelMouseExited(evt);
            }
        });

        saveLabel.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        saveLabel.setForeground(new java.awt.Color(0, 102, 255));
        saveLabel.setText("Save");
        saveLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveLabelMouseExited(evt);
            }
        });

        resetLabel.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        resetLabel.setForeground(new java.awt.Color(0, 102, 255));
        resetLabel.setText("Reset");
        resetLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                resetLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                resetLabelMouseExited(evt);
            }
        });

        addLabel.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        addLabel.setForeground(new java.awt.Color(0, 102, 255));
        addLabel.setText("Add");
        addLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addLabelMouseExited(evt);
            }
        });

        removeLabel.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        removeLabel.setForeground(new java.awt.Color(0, 102, 255));
        removeLabel.setText("Remove");
        removeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                removeLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                removeLabelMouseExited(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(284, 284, 284)
                        .add(saveLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(154, 154, 154)
                        .add(resetLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(269, 269, 269))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(databaseScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, closeLabel)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(addLabel)
                                .add(36, 36, 36)
                                .add(removeLabel)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(closeLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(databaseScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(addLabel)
                    .add(removeLabel))
                .add(12, 12, 12)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(saveLabel)
                    .add(resetLabel))
                .add(59, 59, 59))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void closeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseClicked
        closeLabel.setForeground( main.red );

        main.tnsMenu.setEnabled( false );
        main.tnsMenuItem.setEnabled( true );
        main.welcomePanel.databaseLabel.setEnabled( true );
        main.tabbedPane.remove( this );
}//GEN-LAST:event_closeLabelMouseClicked

    private void closeLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseExited
        closeLabel.setForeground( main.blue );
}//GEN-LAST:event_closeLabelMouseExited

    private void closeLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseEntered
        closeLabel.setForeground( main.black );
}//GEN-LAST:event_closeLabelMouseEntered

    private void saveLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveLabelMouseClicked
        saveLabel.setForeground( main.red );

        try {
            databaseConfigReader.setDatabaseVector(getVector(databaseTable.getModel()));
            databaseConfigReader.write();
            JOptionPane.showMessageDialog( null, "Successfully saved the Configuration", "Save Result", JOptionPane.INFORMATION_MESSAGE );
        } catch(ConfigException ex) {
            JOptionPane.showMessageDialog( null, ex.getMessage(), "Error saving configuration", JOptionPane.ERROR_MESSAGE );
        }
}//GEN-LAST:event_saveLabelMouseClicked

    private void saveLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveLabelMouseExited
        saveLabel.setForeground( main.blue );
}//GEN-LAST:event_saveLabelMouseExited

    private void saveLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveLabelMouseEntered
        saveLabel.setForeground( main.black );
}//GEN-LAST:event_saveLabelMouseEntered

    private void resetLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetLabelMouseClicked
        resetLabel.setForeground( main.red );
        initDatabaseTable();
}//GEN-LAST:event_resetLabelMouseClicked

    private void resetLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetLabelMouseExited
        resetLabel.setForeground( main.blue );
}//GEN-LAST:event_resetLabelMouseExited

    private void resetLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetLabelMouseEntered
        resetLabel.setForeground( main.black );
}//GEN-LAST:event_resetLabelMouseEntered

    private void addLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addLabelMouseClicked
        addLabel.setForeground( main.red );

        ( (DefaultTableModel)databaseTable.getModel() ).addRow( new Object[] { null, null, null, null } );
}//GEN-LAST:event_addLabelMouseClicked

    private void addLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addLabelMouseExited
        addLabel.setForeground( main.blue );
}//GEN-LAST:event_addLabelMouseExited

    private void addLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addLabelMouseEntered
        addLabel.setForeground( main.black );
}//GEN-LAST:event_addLabelMouseEntered

    private void removeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeLabelMouseClicked
        removeLabel.setForeground( main.red );

        selectedRows = databaseTable.getSelectedRows();
        tableModel = (DefaultTableModel)databaseTable.getModel();

        for( int i = selectedRows.length - 1; i > -1; i-- )
            tableModel.removeRow( selectedRows[i] );
}//GEN-LAST:event_removeLabelMouseClicked

    private void removeLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeLabelMouseExited
        removeLabel.setForeground( main.blue );
}//GEN-LAST:event_removeLabelMouseExited

    private void removeLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeLabelMouseEntered
        removeLabel.setForeground( main.black );
}//GEN-LAST:event_removeLabelMouseEntered

    public void appendTNSTableContent( Vector<Vector> tnsVector ) {
        Vector<Vector> initialVector = ( (DefaultTableModel)databaseTable.getModel() ).getDataVector();
        initialVector.addAll( tnsVector );
        tableModel = new DefaultTableModel( initialVector, new Vector( colNameVector ) ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        };
        
        remodelDatabaseTable();
    }
    
    private Vector getVector(TableModel model) {
        Vector databases=new Vector();
       for (int r=0;r<model.getRowCount();r++) {
             Vector databaseEntry = new Vector();
             for (int c=0;c<model.getColumnCount();c++) {
                 Object o = model.getValueAt(r,c);
                 if (o!=null) {
                    databaseEntry.add(model.getValueAt(r,c));
                 }
             }
           databases.add(databaseEntry);
       }
       return databases;
    }
    
    private void initColumnNames() {
        colNameVector = new Vector<String>( 4 );
        colNameVector.add( "Name" );
        colNameVector.add( "Host" );
        colNameVector.add( "Port" );
        colNameVector.add( "SID" );
    }
    
    public void initDatabaseTable() {
        File databasesFile = new File( DatabaseConfiguration.CONFIGNAME );
        Vector databaseVector = null;
        File tnsnamesFile = new File( "tnsnames.ora" );
        
        if( !databasesFile.exists() ) {
            try {
                databaseVector = new TNSNamesReader( main, tnsnamesFile ).getTNSVector();
            }
            catch( Exception e ) {}
        }

        try {
            if( databaseVector == null ) {
                databaseConfigReader.read();
                setDatabaseTableContent( databaseConfigReader.getDatabaseVector() );
            }
            else if( databaseVector.size() > 0 ) {
                databaseConfigReader.setDatabaseVector( databaseVector );
                databaseConfigReader.write();
                setDatabaseTableContent( databaseVector );

                tnsnamesFile.renameTo( new File( "tnsnames.old" ) );
            }
        }
        catch( ConfigException ce ) {
            main.setEnabled( false );
            new TNSExceptionFrame( main, ce ).setVisible( true );
        }
    }
    
    private void remodelDatabaseTable() {
        databaseTable.setModel( tableModel );
        
        tnsTableHeader = databaseTable.getTableHeader();
        tnsTableHeader.setReorderingAllowed( false );
        columnModel = (DefaultTableColumnModel)tnsTableHeader.getColumnModel();
        tnsNameCol = columnModel.getColumn( 0 );
        hostCol = columnModel.getColumn( 1 );
        portCol = columnModel.getColumn( 2 );
        sidCol = columnModel.getColumn( 3 );
        tnsNameCol.setPreferredWidth( 125 );
        hostCol.setPreferredWidth( 125 );
        portCol.setPreferredWidth( 25 );
        sidCol.setPreferredWidth( 25 );
    }
    
    public void setDatabaseTableContent( Vector tnsVector ) {
        tableModel = new DefaultTableModel( new Vector( tnsVector ), new Vector( colNameVector ) ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        };
        
        remodelDatabaseTable();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addLabel;
    private javax.swing.JLabel closeLabel;
    public javax.swing.JScrollPane databaseScrollPane;
    public javax.swing.JTable databaseTable;
    private javax.swing.JLabel removeLabel;
    private javax.swing.JLabel resetLabel;
    private javax.swing.JLabel saveLabel;
    // End of variables declaration//GEN-END:variables

    private DatabaseConfiguration databaseConfigReader;
    private DefaultTableColumnModel columnModel;
    private DefaultTableModel tableModel;
    private int[] selectedRows;
    private JTableHeader tnsTableHeader;
    private Main main;
    private TableColumn tnsNameCol;
    private TableColumn hostCol;
    private TableColumn portCol;
    private TableColumn sidCol;
    private TNSNamesReader tnsNamesReader;
    private Vector<String> colNameVector;
}
