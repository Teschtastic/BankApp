import java.sql.*;

public class Application {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bank",
                    "root",
                    "Sat123@home");

            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from user");

            while(rs.next())
                System.out.println(
                        rs.getInt(1) + " " +
                        rs.getString(2) + " " +
                        rs.getString(3) + " " +
                        rs.getString(4) + " " +
                        rs.getString(5) + " " +
                        rs.getString(6) + " " +
                        rs.getString(3));
            con.close();

        }catch(Exception e) {
            System.out.println("error" + e);
        }
    }
}
