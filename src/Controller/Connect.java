package Controller;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;  
import javax.swing.JOptionPane;

public class Connect {
	public static Connection ConnecrDb() {
		Properties props = new Properties();
		Connection conn = null; 
		String name,pass,url; 
		try {
			name="root";  
            pass="";  
			url ="jdbc:mysql://localhost:3306/biblio?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
				 conn=DriverManager.getConnection(url,name,pass);  
					return conn;
		}catch(Exception e) {
			System.out.print("not gg");
			JOptionPane.showMessageDialog(null,e);
			return null;
			
		}
		}

}
