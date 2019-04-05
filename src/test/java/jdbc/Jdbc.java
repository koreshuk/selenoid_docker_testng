package jdbc;

//import edu.emory.mathcs.backport.java.util.Arrays;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Jdbc {

    static final String DB_URL = "jdbc:mysql://localhost:3306/sakila";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String USER  = "root";
    static final String PASS  = "root";


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        PreparedStatement preparedStatement = null;

        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

        /**
         * Select statement
         */

        Statement stmt = conn.createStatement();
        String strSql = "SELECT * FROM sakila.category where name='Horror'";

        ResultSet rs = stmt.executeQuery(strSql);
        while (rs.next()) {
            int category_id = rs.getInt("category_id");
           String name = rs.getString("name");
           System.out.println(category_id + " " +name+"\n");
        }


        /**
         * insert
         *
         preparedStatement = conn.prepareStatement("INSERT INTO category(category_id, name, last_update) VALUES (?,?,?)");
         preparedStatement.setInt(1, 32);
         preparedStatement.setString(2,"qweqwe32");
         preparedStatement.setInt(3, 20180215);
         preparedStatement.executeUpdate();
*/

        /**
         * delete
         *
        preparedStatement = conn.prepareStatement("DELETE FROM category where name =?");
        preparedStatement.setString(1,"qweqwe32");
        preparedStatement.executeUpdate();
*/


        conn.close();
    }

}
