/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import javax.swing.*;

import javax.swing.table.*;
import java.sql.*;
import java.util.Hashtable;

import javax.sql.*;
import javax.sql.rowset.*;
import com.sun.rowset.CachedRowSetImpl;

import view.EmpruntGui;
/**
 * Model for the courselist table. Contains database-specific code to read the
 * data from the database into the table model.
 * @author Kevin
 */

// AbstractTableModel is a class that implements the TableModel interface.
public class EmpruntModel extends AbstractTableModel {
    
    CachedRowSet courseListRowSet; // Contains data
    ResultSetMetaData metadata; // Additional info about the data
    Connection connection;
    Statement statement;
    int numcols, numrows; // Number of rows and columns

    // Constructor used to connect to the database
    public EmpruntModel() {
        
        try {
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost/biblio?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
                    "root", "");
        } catch (SQLException sqlerr) {
            System.out.println(sqlerr.getMessage());
            System.out.println(sqlerr.getSQLState());
            System.out.println(sqlerr.getErrorCode());
        }
        
        System.out.println("Connected Successfully");
        
        try {
            connection.setAutoCommit(false);
            String nom = "mon.implementation.de.MonSyncProvider"; 
            courseListRowSet = new CachedRowSetImpl();
            courseListRowSet.setSyncProvider(nom);
            courseListRowSet.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            courseListRowSet.setConcurrency(ResultSet.CONCUR_UPDATABLE);
            courseListRowSet.setCommand("select* from emprunter");
            courseListRowSet.execute(connection);
            
            metadata = courseListRowSet.getMetaData();
            numcols = metadata.getColumnCount();
            numrows = courseListRowSet.size();
            courseListRowSet.first();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

    // Constructor that uses a RowSet and a database connection
    public EmpruntModel(RowSet rowSet, Connection conn) {
        try {
            courseListRowSet = (CachedRowSet) rowSet;
            courseListRowSet.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            courseListRowSet.setConcurrency(ResultSet.CONCUR_UPDATABLE);
            metadata = courseListRowSet.getMetaData();
            numcols = metadata.getColumnCount();
            numrows = courseListRowSet.size();
            connection = conn;
        } catch(SQLException exp) {
            exp.printStackTrace();
        }
    }

    // Gets the total number of rows
    public int getRowCount() {
        return numrows;
    }

    // Gets the total number of columns
    public int getColumnCount() {
        return numcols;
    }

    // Gets the value of an object at a given row and column
    public Object getValueAt(int row, int col) {
        try {
            if (row >= courseListRowSet.size())
                //Error: Trying to access a deleted row
                return null;

            courseListRowSet.absolute(row + 1);
            Object obj = courseListRowSet.getObject(col + 1);
            if (obj == null)
                return null;
            else
                return obj.toString();
        } catch (SQLException err) {
            err.printStackTrace();
            return err.toString();
        }
    }

    // Returns false because cells are not editable
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    // Returns the name of a column
    public String getColumnName(int col) {
        try {
            return metadata.getColumnLabel(col + 1);
        } catch (SQLException err) {
            return err.toString();
        }
    }

    // Sets the value of an object at a given row and column
    public void setValueAt(Object aValue, int row, int col) {
        try {
            courseListRowSet.moveToInsertRow();
            System.out.println(aValue + " setValueAt " + (row+1) + " " + (col+1));
            courseListRowSet.updateObject(col+1, (String) aValue);
        } catch (SQLException err) {
            err.getMessage();
            err.printStackTrace();
        }
    }

    // Checks if the inserted or updated record has the same primary key as a record already in the database.
    // Returns true if there is no record with the same primary key; otherwise, return false and display a message
    public boolean isPrimaryKeyInDB(Object[] array) {
        try {

            if (getRowCount() == 0)
                // Database is empty, so the desired primary key does not exist
                return false;

            courseListRowSet.beforeFirst();
            while (courseListRowSet.next()) {
                if (courseListRowSet.getString("NumEmp").equals(array[0]))
                    // Desired primary key is in the database
                    return true;
            }

            // Desired primary key is not in the database
            return false;
        } catch(SQLException err) {
            err.getMessage();
            err.printStackTrace();
            return false;
        }
    }

    // Adds a row to the database
    public void addRow(Object[] array) {
        try {

            if(!isValidEnrollmentField(array))
                // Invalid information entered in to Enrollment field
                return;

            // Insert the row if the desired primary key is not already in use
            if (!isPrimaryKeyInDB(array)) {
            	System.out.print("gg");
                courseListRowSet.last();
                courseListRowSet.moveToInsertRow();
                courseListRowSet.updateString("NumEmp", (String) array[0]);
                courseListRowSet.updateString("NL", (String) array[1]);
                courseListRowSet.updateString("dateEmp", (String) array[2]);
                courseListRowSet.updateString("dateRet","Pas encore retourn�");
                courseListRowSet.updateString("NA", (String) array[4]);
                courseListRowSet.updateString("dureeMax", (String) array[5]);
                courseListRowSet.insertRow();
                courseListRowSet.moveToCurrentRow();
                courseListRowSet.acceptChanges(connection);
            } else {
                // Error: User's desired primary key is already in the database
                JOptionPane.showMessageDialog(null,
                        "Cannot have multiple records with the same primary key (NumEmp).",
                        "Primary Key Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch(SQLException err) {
            err.getMessage();
            err.printStackTrace();
        }
    }

    // Deletes a row from the database
    public void deleteRow(Object[] array) {
        try {
            courseListRowSet.beforeFirst();
            while (courseListRowSet.next()) {
                if (courseListRowSet.getString("NumEmp").equals(array[0])) {
                    courseListRowSet.deleteRow();
                    break;
                }
            }
            courseListRowSet.acceptChanges(connection);
        } catch(SQLException err) {
            err.getMessage();
            err.printStackTrace();
        }
    }

    // Updates a row in the database
    // array: Grabs what is in the text field
    // getString(): Grabs where the cursor is
    public void updateRow(Object[] array, JTable jtable) {
        try {
            if (jtable.getSelectedRow() == -1)
                // Error: No row is selected for updating. This error is quietly handled.
                return;

            if(!isValidEnrollmentField(array))
                return;

            // Move to the selected row
            courseListRowSet.absolute(jtable.getSelectedRow() + 1);

            // Current course number in the selected row
            String selectedCourseNumber = courseListRowSet.getString("NumEmp");

            // Course number entered into the text field
            String desiredCourseNumber = (String) array[0];

            // Update the row if the desired primary key is not in the database, or the desired primary key is in the
            // database, but the row's primary key is not being updated
            if (!isPrimaryKeyInDB(array) || desiredCourseNumber.equals(selectedCourseNumber)) {
                courseListRowSet.absolute(jtable.getSelectedRow() + 1);
                courseListRowSet.updateString("NumEmp", (String) array[0]);
                courseListRowSet.updateString("NL", (String) array[1]);
                courseListRowSet.updateString("dateEmp", (String) array[2]);
                courseListRowSet.updateString("dateRet", (String) array[3]);
                courseListRowSet.updateString("NA", (String) array[4]);
                courseListRowSet.updateString("dureeMax", (String) array[5]);
                courseListRowSet.updateRow();
                courseListRowSet.first();
                courseListRowSet.acceptChanges(connection);
                return;
            }

            // Error: User is trying to change the primary key to a new primary key already in the database
            JOptionPane.showMessageDialog(null,
                    "Cannot have multiple records with the same primary key (NumEmp).",
                    "Primary Key Error", JOptionPane.ERROR_MESSAGE);

        } catch(SQLException err) {
            err.getMessage();
            err.printStackTrace();
        }
    }
    
    // Returns the current CachedRowSet in the TableModel
    public CachedRowSet getRowSet() {
        return courseListRowSet;
    }
    
    // Helps preserve the old connection for the new TableModel
    public Connection getConnection() {
        return connection;
    }

    // Checks if the enrollment field is an integer
    public boolean isValidEnrollmentField(Object[] array) {
        try {
            Integer.valueOf((String) array[0]).intValue();
            return true;
        } catch(NumberFormatException err) {
            JOptionPane.showMessageDialog(null,
                    "ID field must be an integer.",
                    "Number Format Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        EmpruntGui courseListGUI = new EmpruntGui();
        courseListGUI.setVisible(true);                   

    }
    
}