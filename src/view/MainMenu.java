package view;


import javax.swing.*;

import javax.swing.border.*;


import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame implements ActionListener{

	private JPanel contentPane;
        private JButton b1,b2,b3,b4,b5,b6;
        private JLabel l2_1;
        private JLabel l3_1;

	public static void main(String[] args) {
            new MainMenu().setVisible(true);
	}
        
        public MainMenu() {
	
            setBounds(400, 150, 862, 800);
            contentPane = new JPanel();
            setContentPane(contentPane);
            contentPane.setLayout(null);

            
            JMenuBar menuBar = new JMenuBar();
            menuBar.setBorderPainted(false);
            menuBar.setEnabled(false);
            menuBar.setForeground(Color.GREEN);
            menuBar.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 128, 0), new Color(128, 128, 128)));
            menuBar.setBackground(new Color(135, 206, 235));
            menuBar.setBounds(0, 0, 856, 35);
            contentPane.add(menuBar);

            JMenu mnExit = new JMenu("Exit");
            mnExit.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
            
            
            JMenuItem mntmLogout = new JMenuItem("Logout");
            mntmLogout.setBackground(new Color(211, 211, 211));
            mntmLogout.setForeground(new Color(105, 105, 105));
            mntmLogout.addActionListener(this);
            mnExit.add(mntmLogout);
            
            JMenuItem mntmExit = new JMenuItem("Exit");
            mntmExit.setForeground(new Color(105, 105, 105));
            mntmExit.setBackground(new Color(211, 211, 211));
            mntmExit.addActionListener(this);
            mnExit.add(mntmExit);
                
            

            JMenu mnHelp = new JMenu("Help");
            mnHelp.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
            

            JMenuItem mntmReadme = new JMenuItem("Read Me");
            mntmReadme.setBackground(new Color(211, 211, 211));
            mntmReadme.setForeground(new Color(105, 105, 105));
            mnHelp.add(mntmReadme);

            JMenuItem mntmAboutUs = new JMenuItem("About Us");
            mntmAboutUs.setForeground(new Color(105, 105, 105));
            mntmAboutUs.setBackground(new Color(211, 211, 211));
            mntmAboutUs.addActionListener(this);
            mnHelp.add(mntmAboutUs);

            JMenu mnRecord = new JMenu("Record");
            mnRecord.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
            

            JMenuItem bookdetails = new JMenuItem("Book Details");
            bookdetails.addActionListener(this);
            bookdetails.setBackground(new Color(211, 211, 211));
            bookdetails.setForeground(Color.DARK_GRAY);
            mnRecord.add(bookdetails);

            JMenuItem studentdetails = new JMenuItem("Student Details");
            studentdetails.setBackground(new Color(211, 211, 211));
            studentdetails.setForeground(Color.DARK_GRAY);
            studentdetails.addActionListener(this);
            mnRecord.add(studentdetails);
            
            menuBar.add(mnRecord);
            menuBar.add(mnHelp);
            menuBar.add(mnExit);

            JLabel l2 = new JLabel("");
            l2.setVerticalAlignment(SwingConstants.TOP);
            ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/second.png"));
            Image i2 = i1.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            l2_1 = new JLabel(i3);
            l2_1.setForeground(Color.RED);
            l2_1.setBounds(60, 140, 159, 152);
            contentPane.add(l2_1);

            JLabel l3 = new JLabel("");
            ImageIcon i4  = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/third.png"));
            Image i5 = i4.getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT);
            ImageIcon i6 = new ImageIcon(i5);
            l3_1 = new JLabel(i6);
            l3_1.setForeground(Color.WHITE);
            l3_1.setBounds(318, 164, 134, 128);
            contentPane.add(l3_1);

            JLabel l4 = new JLabel("");
            ImageIcon i7  = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/fourth.png"));
            Image i8 = i7.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
            ImageIcon i9 = new ImageIcon(i8);
            l4 = new JLabel(i9);
            l4.setBounds(530, 140, 225, 152);
            contentPane.add(l4);

            b1 = new JButton("Ajouter Livres");
            b1.setFont(new Font("Tahoma", Font.BOLD, 14));
            b1.addActionListener(this);
            b1.setBackground(new Color(218, 165, 32));
            b1.setForeground(new Color(255, 255, 255));
            b1.setBounds(60, 320, 159, 44);
            contentPane.add(b1);

            b2 = new JButton("Ajouter Exemplaire");
            b2.setFont(new Font("Tahoma", Font.BOLD, 14));
            b2.addActionListener(this);
            b2.setBackground(new Color(218, 165, 32));
            b2.setForeground(new Color(255, 255, 255));
            b2.setBounds(313, 320, 167, 44);
            contentPane.add(b2);

            b3 = new JButton("Ajouter Adherent");
            b3.setFont(new Font("Tahoma", Font.BOLD, 14));
            b3.addActionListener(this);
            b3.setBackground(new Color(218, 165, 32));
            b3.setForeground(Color.WHITE);
            b3.setBounds(562, 320, 167, 44);
            contentPane.add(b3);

            b4 = new JButton("Efectuer Emprunt/Retour");
            b4.setFont(new Font("Tahoma", Font.BOLD, 14));
            b4.addActionListener(this);
            b4.setBackground(new Color(218, 165, 32));
            b4.setForeground(new Color(255, 255, 255));
            b4.setBounds(76, 620, 143, 41);
            contentPane.add(b4);

            b5 = new JButton("Emprunt en cours");
            b5.setFont(new Font("Tahoma", Font.BOLD, 14));
            b5.addActionListener(this);
            b5.setBackground(new Color(218, 165, 32));
            b5.setForeground(new Color(255, 255, 255));
            b5.setBounds(310, 620, 159, 41);
            contentPane.add(b5);

            b6 = new JButton("Livres");
            b6.setFont(new Font("Tahoma", Font.BOLD, 14));
            b6.addActionListener(this);
            b6.setBackground(new Color(218, 165, 32));
            b6.setForeground(new Color(255, 255, 255));
            b6.setBounds(562, 620, 159, 41);
            contentPane.add(b6);

            JLabel l5 = new JLabel("");
            ImageIcon i10  = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/fifth.png"));
            Image i11 = i10.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
            ImageIcon i12 = new ImageIcon(i11);
            l5 = new JLabel(i12);
            l5.setBounds(60, 440, 159, 163);
            contentPane.add(l5);

            JLabel l6 = new JLabel("");
            ImageIcon i13  = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/sixth.png"));
            Image i14 = i13.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
            ImageIcon i15 = new ImageIcon(i14);
            l6 = new JLabel(i15);
            l6.setBounds(330, 440, 139, 152);
            contentPane.add(l6);

            JLabel l7 = new JLabel("");
            ImageIcon i16  = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/seventh.png"));
            Image i17 = i16.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
            ImageIcon i18 = new ImageIcon(i17);
            l7 = new JLabel(i18);
            l7.setBounds(562, 440, 157, 152);
            contentPane.add(l7);
            
            getContentPane().setBackground(Color.WHITE);
            contentPane.setBackground(Color.WHITE);
            
            JLabel lblNewLabel = new JLabel("New label");
            lblNewLabel.setIcon(new ImageIcon(MainMenu.class.getResource("/library/management/system/icons/f4b4942c3d6c8e3b296da37e5e64521f.jpg")));
            lblNewLabel.setBounds(0, 0, 846, 761);
            contentPane.add(lblNewLabel);
	}
        
        
        public void actionPerformed(ActionEvent ae){
            String msg = ae.getActionCommand();
            if(msg.equals("Logout")){
                setVisible(false);
                new login().setVisible(true);
		new login().setVisible(true);
            }else if(msg.equals("Exit")){
                System.exit(ABORT);
            
            }else if(msg.equals("Read Me")){
            
            }else if(msg.equals("About Us")){
                setVisible(false);
		new ActualEmpruntGui().setVisible(true);
            
            }else if(msg.equals("Book Details")){
                setVisible(false);
		new EmpruntGui().setVisible(true);
            }else if(msg.equals("Student Details")){
                setVisible(false);
                new AdherentGui().setVisible(true);
			
            }
            
            if(ae.getSource() == b1){
            
                new LivresGui().setVisible(true);
            }
            if(ae.getSource() == b2){
         
                try {
					new ExemplaireGui().setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            if(ae.getSource() == b3){
               
                new AdherentGui().setVisible(true);
            }
            if(ae.getSource() == b4){
            
                new EmpruntGui().setVisible(true);
            }
            if(ae.getSource() == b5){
               
                new ActualEmpruntGui().setVisible(true);
            
            }
            if(ae.getSource() == b6){
                
                new EmpruntGui().setVisible(true);
            
            }
            
        }
}
