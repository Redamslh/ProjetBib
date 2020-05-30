/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;

import java.awt.event.ActionEvent;

import javax.swing.*;

import Controller.AdherentController;
/**
 *
 * @author Kevin
 */
import Controller.EmpruntController;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
public class AdherentGui extends javax.swing.JFrame {

    private JTable jtable1; // The table displayed on the GUI
    private AdherentController AdherentController = null;

    public AdherentGui() {
        
        // This method creates the labels and text fields on the GUI
        initComponents();
        
        // Creates a controller for the table
        AdherentController = new AdherentController(this);

        // Add a table JTable to the GUI
        addJTable();

    }
    
    public void addJTable() {
        // Add the data and column names to a JTable
        jtable1 = new JTable(AdherentController.getTableModel());
    
        // Add a ListSelectionListener to the table
        jtable1.getSelectionModel().addListSelectionListener(AdherentController);
        
        // Add the table to a scrollpane
        JScrollPane scrollpane = new JScrollPane(jtable1);
        
        // Create a window
        // This was originally jPanel
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(scrollpane, BorderLayout.CENTER);

        // User can only select one row at a time
        jtable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    // Update the JTable
    public void updateTable() {
        jtable1.setModel(AdherentController.getTableModel());
    }

    // Set data on the courseNameTextField
    public void setCourseNameTextField(String i) {
        courseNameTextField.setText(i);
    }

    // Set data on the courseNumberTextField
    public void setCourseNumberTextField(String value) { courseNumberTextField.setText(value); }

    // Set data on the enrollmentTextField
    public void setEnrollmentTextField(String num) {
        enrollmentTextField.setText(num);
    }

    // Set data on the startDateTextField
    public void setStartDateTextField(String startDate) {
        startDateTextField.setText(startDate);
    }
    public void setTextField(String startDate) {
        textField.setText(startDate);
    }
    public void setTextField1(String startDate) {
        textField_1.setText(startDate);
    }
    public void setTextField2(String startDate) {
        textField_2.setText(startDate);
    }
    public void setTextField3(String startDate) {
        textField_3.setText(startDate);
    }

    // Set data on the endDateTextField
    public void setEndDateTextField(String endDate) {
        endDateTextField.setText(endDate);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        courseNameLabel = new javax.swing.JLabel();
        courseNumberLabel = new javax.swing.JLabel();
        enrollmentLabel = new javax.swing.JLabel();
        startDateLabel = new javax.swing.JLabel();
        endDateLabel = new javax.swing.JLabel();
        courseNameTextField = new javax.swing.JTextField();
        courseNumberTextField = new javax.swing.JTextField();
        enrollmentTextField = new javax.swing.JTextField();
        endDateTextField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        courseListLabel = new javax.swing.JLabel();

        // Program will terminate when the window is closed
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        courseNameLabel.setText("Login");

        courseNumberLabel.setText("Num d'Adherent");

        enrollmentLabel.setText("Nom");


        endDateLabel.setText("Prenom");

        // Details for the Add button
        addButton.setText("Ajouter");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        // Details for the Delete button
        deleteButton.setText("Supprimer");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        // Details for the Update button
        updateButton.setText("Modifier");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        quitButton.setText("Quitter");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	quitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 432, Short.MAX_VALUE)
        );
        
        JLabel enrollmentLabel_1 = new JLabel();
        enrollmentLabel_1.setText("Mot de Passe");
        
        JLabel endDateLabel_1 = new JLabel();
        endDateLabel_1.setText("Role");
        
        JLabel courseNumberLabel_1 = new JLabel();
        courseNumberLabel_1.setText("Telephone");
        
        textField = new JTextField();
        
        textField_1 = new JTextField();
        
        textField_2 = new JTextField();
        
        JLabel courseNameLabel_1 = new JLabel();
        courseNameLabel_1.setText("Adresse");
        
        textField_3 = new JTextField();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap(877, Short.MAX_VALUE)
        			.addComponent(quitButton)
        			.addGap(18))
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(18)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(24)
        					.addComponent(addButton)
        					.addGap(18)
        					.addComponent(updateButton)
        					.addGap(18)
        					.addComponent(deleteButton)
        					.addGap(69)
        					.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(courseNameLabel)
        						.addComponent(endDateLabel)
        						.addComponent(enrollmentLabel)
        						.addComponent(courseNumberLabel))
        					.addGap(147)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(endDateTextField)
        						.addComponent(enrollmentTextField)
        						.addComponent(courseNameTextField)
        						.addComponent(courseNumberTextField, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addComponent(courseNameLabel_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
        							.addGap(151)
        							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
        								.addComponent(endDateLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        								.addComponent(enrollmentLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        								.addComponent(courseNumberLabel_1, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        									.addGroup(jPanel1Layout.createSequentialGroup()
        										.addGap(129)
        										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
        									.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        										.addGap(129)
        										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)))
        								.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        									.addGap(129)
        									.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)))))))
        			.addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(courseNameLabel)
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addComponent(courseNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(courseNumberTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(courseNumberLabel))
        							.addGap(18)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(enrollmentTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(enrollmentLabel))
        							.addGap(18)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(endDateTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(endDateLabel)))
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(courseNameLabel_1)
        								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        							.addGap(18)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addGroup(jPanel1Layout.createSequentialGroup()
        									.addGap(3)
        									.addComponent(courseNumberLabel_1)))
        							.addGap(18)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addGroup(jPanel1Layout.createSequentialGroup()
        									.addGap(3)
        									.addComponent(enrollmentLabel_1)))
        							.addGap(18)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(jPanel1Layout.createSequentialGroup()
        									.addGap(3)
        									.addComponent(endDateLabel_1))
        								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        					.addGap(29)
        					.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addComponent(quitButton))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(352)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(addButton)
        						.addComponent(updateButton)
        						.addComponent(deleteButton))))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1.setLayout(jPanel1Layout);

        courseListLabel.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        courseListLabel.setText("Gestion Adherent");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 964, GroupLayout.PREFERRED_SIZE)
        				.addComponent(courseListLabel))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(courseListLabel)
        			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Code for the add button
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        String[] array = new String[jtable1.getColumnCount()];
        array[0] = courseNameTextField.getText();
        array[1] = courseNumberTextField.getText();
        array[2] = enrollmentTextField.getText();
        array[3] = endDateTextField.getText();
        array[4] = textField.getText();
        array[5] = textField_1.getText();
        array[6] = textField_2.getText();
        array[7] = textField_3.getText();
        // Send data to the controller to add it to the model
        AdherentController.addRow(array);
    }//GEN-LAST:event_addButtonActionPerformed
	private void quitButtonActionPerformed(ActionEvent evt) {
		JFrame frame = null;
		// TODO Auto-generated method stub
		System.exit(0);;
	}
    // Code for the delete button
    // NEED TO FINISH MAKING THIS. SEE addRow() METHODS IN
    // CourseListTableController.java (LINE 65) and CouseListTableModel.java (LINE 149)
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        String[] array = new String[jtable1.getColumnCount()];
        array[0] = courseNameTextField.getText();
        array[1] = courseNumberTextField.getText();
        array[2] = enrollmentTextField.getText();
        array[3] = endDateTextField.getText();
        array[4] = textField.getText();
        array[5] = textField_1.getText();
        array[6] = textField_2.getText();
        array[7] = textField_3.getText();
        // Send data to the controller to remove it from the model
        AdherentController.deleteRow(array);
    }//GEN-LAST:event_deleteButtonActionPerformed

    // Code for the update button
    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        String[] array = new String[jtable1.getColumnCount()];
        array[0] = courseNameTextField.getText();
        array[1] = courseNumberTextField.getText();
        array[2] = enrollmentTextField.getText();
        array[3] = endDateTextField.getText();
        array[4] = textField.getText();
        array[5] = textField_1.getText();
        array[6] = textField_2.getText();
        array[7] = textField_3.getText();
        // Send data to the controller to update it in the model
        AdherentController.updateRow(array, jtable1);
    }//GEN-LAST:event_updateButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdherentGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdherentGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdherentGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdherentGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdherentGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel courseListLabel;
    private javax.swing.JLabel courseNameLabel;
    private javax.swing.JTextField courseNameTextField;
    private javax.swing.JLabel courseNumberLabel;
    private javax.swing.JTextField courseNumberTextField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel endDateLabel;
    private javax.swing.JTextField endDateTextField;
    private javax.swing.JLabel enrollmentLabel;
    private javax.swing.JTextField enrollmentTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton quitButton;
    private javax.swing.JLabel startDateLabel;
    private javax.swing.JTextField startDateTextField;
    private javax.swing.JButton updateButton;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    }
