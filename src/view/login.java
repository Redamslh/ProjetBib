package view;
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Connect;

import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class login extends JFrame {
java.sql.Connection conn;
ResultSet rs;
PreparedStatement pst ;
	private JPanel contentPane;
	private TextField Login_2;
	private Button Login_4;
	private JPasswordField Login_3;
	private JLabel lblMotDePasse;
	private JLabel lblLogin;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws DAOException 
	 */
	public login()  {
		super("Login");
		conn=Connect.ConnecrDb();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 568);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Login_2 = new TextField("");
		Login_2.setForeground(new Color(0, 0, 0));
		Login_2.setFont(new Font("Arial", Font.PLAIN, 20));
		Login_2.setBackground(new Color(255, 255, 255));
		Login_2.setBounds(269, 228, 181, 33);
		contentPane.add(Login_2);
		Login_3 = new JPasswordField();
		Login_3.setForeground(new Color(0, 0, 0));
		Login_3.setBounds(269, 298, 181, 33);
		contentPane.add(Login_3);
		Login_4 = new Button("se connecter");
		Login_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="select * from user where login=? and mdp=?";
				try {
					pst=conn.prepareStatement(sql);
					pst.setString(1,Login_2.getText());
					pst.setString(2,Login_3.getText());
					rs=pst.executeQuery();
					if(rs.next()) {
						String lastName = rs.getString("role");
						rs.close();
						pst.close();
						if(lastName.contentEquals("admin")) {
							System.out.println("Welcome Admin");
							MainMenu mn=new MainMenu();
							mn.setVisible(true); 
						}
						else {
							System.out.println("Welcome Adherent");
							SplashScreen sp=new SplashScreen("","","");

						}
					}
					else {
						JOptionPane.showMessageDialog(null, "login ou mot de passe incorrect");
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null,e1);
					System.out.print("not gg");

					
				}
			}
		});
		Login_4.setForeground(new Color(255, 255, 255));
		Login_4.setFont(new Font("Arial", Font.PLAIN, 14));
		Login_4.setBackground(new Color(0, 0, 0));
		Login_4.setBounds(272, 376, 97, 33);
		contentPane.add(Login_4);
		
		Button Login_4_1 = new Button("mot de passe oubli\u00E9?");
		Login_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Login_4_1.setForeground(new Color(255, 255, 255));
		Login_4_1.setFont(new Font("Arial", Font.PLAIN, 9));
		Login_4_1.setBackground(new Color(0, 0, 0));
		Login_4_1.setBounds(519, 486, 97, 33);
		contentPane.add(Login_4_1);
		
		lblMotDePasse = new JLabel("Mot de Passe");
		lblMotDePasse.setForeground(new Color(255, 255, 255));
		lblMotDePasse.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMotDePasse.setBounds(102, 295, 173, 33);
		contentPane.add(lblMotDePasse);
		
		lblLogin = new JLabel("Login");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setFont(new Font("Arial", Font.PLAIN, 20));
		lblLogin.setBounds(102, 228, 173, 33);
		contentPane.add(lblLogin);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(login.class.getResource("/library/management/system/icons/login-png-icon-7.png")));
		lblNewLabel_1.setBounds(226, -26, 534, 275);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(login.class.getResource("/library/management/system/icons/ffe304a2c7ef9860852e6f7724277e49.jpg")));
		lblNewLabel.setBounds(0, 0, 626, 529);
		contentPane.add(lblNewLabel);
		
	
	}
}
