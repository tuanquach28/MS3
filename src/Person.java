
import java.sql.*;
import java.util.*;

public class Person {

    private static Connection con = null;

    // constructor
    public Person() {

        // create a connection to the sqlite
        con = getLocalConnection();
        try {
            if(con !=null){
                con.createStatement().execute("create table IF NOT EXISTS people(firstName,LastName,website,gender,img,company,cost,H,I,city)");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    /*
    Function that add people to the database(sqlite)
     */
    public static int addPerson(String firstName,String LastName, String website, String gender, String img,
                                String company,String  cost, boolean H, boolean I,String city) {


        if (con == null) {
            System.out.println("Connection failed");
            return -1;
        }

        try{
            if(con != null){

                PreparedStatement insert = con.prepareStatement("INSERT INTO people(firstName,LastName,website,gender,img,company,cost,H,I,city)  VALUES (?,?,?,?,?,?,?,?,?,?)");
                insert.setString(1,firstName);
                insert.setString(2,LastName);
                insert.setString(3,website);
                insert.setString(4,gender);
                insert.setString(5,img);
                insert.setString(6,company);
                insert.setString(7,cost);
                insert.setBoolean(8,H);
                insert.setBoolean(9,I);
                insert.setString(10,city);

                int a = insert.executeUpdate();
                if(a == 0) return -1; // return -1 for failure

                //return 1 for successful
                return 1;


            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }


        // return -1 for failure
        return -1;
    }



    /*
    Connect to the local database for development purposes
     */
    private static Connection getLocalConnection() {

        Connection conn = null;
        try {
            // db parameters

            // This is so we can choose to do memory or actual title for the databse

            // uncomment if you want to use main memory
//            String url = "jdbc:sqlite::memory";
            // sample can be change for different title for the database
            String url = "jdbc:sqlite:sample2.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;


    }


    /*
        This is a function to close out the connection
     */
    public static void close(){
        try {
            if (con != null) {
                con.close();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
