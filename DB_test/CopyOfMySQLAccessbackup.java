package DB_test;

import java.sql.*;

public class CopyOfMySQLAccessbackup {
	
	public static void main (String[] args)
    {
        Connection conn = null;

        try
        {
            String userName = "kasperhelweg_dk";
            String password = "sql2211";
            String url = "jdbc:mysql://kasperhelweg.dk.mysql/kasperhelweg_dk";
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println ("Database connection established");
        }
        catch (Exception e)
        {
            System.err.println ("Cannot connect to database server");
            System.out.print(e.getCause());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close ();
                    System.out.println ("Database connection terminated");
                }
                catch (Exception e) { /* ignore close errors */ }
            }
        }
    }
}
