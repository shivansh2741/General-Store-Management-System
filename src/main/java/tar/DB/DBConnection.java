package tar.DB;

/** DBConnection.java **/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class DBConnection {
    private static Connection conn = null;

    public static String url = "jdbc:mysql://localhost:3306/store?autoReconnect=true&useSSL=false";
    private static String user = "root";//Username of database


    public static Connection connect() throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        }catch(ClassNotFoundException cnfe){
            System.err.println("Error: "+cnfe.getMessage());
        }catch(InstantiationException ie){
            System.err.println("Error: "+ie.getMessage());
        }catch(IllegalAccessException iae){
            System.err.println("Error: "+iae.getMessage());
        }
        conn = DriverManager.getConnection(url,user,"rootroot");
        //  System.out.println(url);
        return conn;
    }
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(conn !=null && !conn.isClosed())
            return conn;
        connect();
        return conn;
    }
}