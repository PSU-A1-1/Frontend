package application;

import java.sql.*;
import java.util.Date;

import application.CardHolder;

public class DBConnection {
	
	private static final String URL = "jdbc:mysql://localhost/stengade";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	
	public CardHolder getCardHolder(int ID){
		
		Connection conn = null;

        try
        {
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement("SELECT first_name, surname, beers, drinks, active "
            		+ " FROM volunteer LEFT JOIN guest ON ST_ID WHERE ST-ID = ?");
            pstmt.setInt(1, ID);
            ResultSet rs = pstmt.executeQuery();
            
            
        }
        catch (Exception e)
        {
            System.err.println ("Cannot connect to database server");
            System.out.print(e.getCause());
        }
	}

}
