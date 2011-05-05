package application;

import java.sql.*;

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
            PreparedStatement pstmt = conn.prepareStatement(
            		  "SELECT `first_name`, `surname`, `beers`, `drinks`, `active` "
                    + "FROM `volunteer` "
                    + "WHERE `ST-ID` = ? "
                    + "UNION ALL "
                    + "SELECT NULL as `first_name`, NULL AS `surname`, `beers`, `drinks`, `active` "
                    + "FROM `guest` "
                    + "WHERE `ST-ID` = ? ");
            pstmt.setInt(1, ID);
            pstmt.setInt(2, ID);
            ResultSet rs = pstmt.executeQuery();
            
            rs.next();
            CardHolder cardHolder = new CardHolder(ID, rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
            rs.close();
            return cardHolder;
            
        }
        catch (Exception e)
        {
            System.err.println ("Cannot connect to database server");
            System.out.print(e.getCause());
            return null;
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
    
    public void sendTransaction(Transaction transaction){
        
        Connection conn = null;
        
        try
        {
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement psCardHolder = null;
            if(transaction.isGuest()) {
                psCardHolder = conn.prepareStatement("UPDATE `guest` "
                       + " SET `beers` = ? , `drinks` = ? WHERE `ST-ID` = ?");
            } else {
                psCardHolder = conn.prepareStatement("UPDATE `volunteer` "
                       + "SET `beers` = ? , `drinks` = ? WHERE `ST-ID` = ?");
            }
            
            psCardHolder.setInt(1, transaction.getNewBeers());
            psCardHolder.setInt(2, transaction.getNewDrinks());
            psCardHolder.setInt(3, transaction.getID());
            int statusCH = psCardHolder.executeUpdate();
            
            if (statusCH < 1) System.out.println("CARDHOLDER: Something went wrong, no rows were altered.");
            if (statusCH == 1) System.out.println("CARDHOLDER: Something went good, 1 row was altered!");
            if (statusCH > 1) System.out.println("CARDHOLDER: Something went REALLY wrong! Many rows where altered!!!");
            
            PreparedStatement psTransaction = conn.prepareStatement("INSERT INTO `stengade`.`transaction` "
                    + "(`ST-ID` ,`beers` ,`drinks` ,`time`) "
                    + "VALUES (?, ?, ?, CURRENT_TIMESTAMP) ");
           
            psTransaction.setInt(1, transaction.getID());
            psTransaction.setInt(2, transaction.getBoughtBeers());
            psTransaction.setInt(3, transaction.getBoughtDrinks());
            int statusT = psTransaction.executeUpdate();
            
            if (statusT < 1) System.out.println("TRANSACTION: Something went wrong, no rows were altered.");
            if (statusT == 1) System.out.println("TRANSACTION: Something went good, 1 row was altered!");
            if (statusT > 1) System.out.println("TRANSACTION: Something went REALLY wrong! Many rows where altered!!!");
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
