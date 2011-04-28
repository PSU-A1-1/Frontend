package DB_test;

import java.sql.*;

public class MySQLAccess {
	
	public static void main (String[] args)
    {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			System.err.println("Couldn't find driver class:");
			System.out.println("Couldn't find driver class:");
			cnfe.printStackTrace();
		}
		
		System.out.println("Drivers are great.");
        Connection postGresConn = null;
        try {
            postGresConn = 
                    DriverManager.getConnection("jdbc:mysql://kasperhelweg.dk.mysql/kasperhelweg_dk", 
                                                "kasperhelweg_dk", "sql2211");
        } catch (SQLException se) {
            System.out.println("Could not connect: here's a trace, then exit.");
            se.printStackTrace();
            System.exit(1);
        }
        
        if (postGresConn != null)
            System.out.println("Connected to Postgres Database with great succes");
        else
            System.out.println("Hmm. Fail!");
	}
}
