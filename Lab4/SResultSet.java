import java.sql.*;

public class SResultSet {

    public static void main(String[] args) throws Exception {

        // MySQL database details
        String url = "jdbc:mysql://localhost:3306/lab4";
        String user = "jdbcuser";
        String password = "jdbc123";

        try {

            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL database
            Connection con = DriverManager.getConnection(
                url,
                user,
                password
            );

            // Create a scrollable, read-only ResultSet
            Statement st = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );

            // Execute query on Student table
            ResultSet rs = st.executeQuery("SELECT * FROM Student");

            // -----------------------------------------
            // 1. Records in Forward Direction
            // -----------------------------------------

            System.out.println("Records in Forward Direction:");
            System.out.println("RollNo\tName\t\tAddress");
            System.out.println("--------------------------------");

            while (rs.next()) {

                System.out.println(
                    rs.getInt("RollNo") + "\t" +
                    rs.getString("Name") + "\t\t" +
                    rs.getString("Address")
                );
            }

            // -----------------------------------------
            // 2. Records in Backward Direction
            // -----------------------------------------

            System.out.println("\nRecords in Backward Direction:");
            System.out.println("RollNo\tName\t\tAddress");
            System.out.println("--------------------------------");

            while (rs.previous()) {

                System.out.println(
                    rs.getInt("RollNo") + "\t" +
                    rs.getString("Name") + "\t\t" +
                    rs.getString("Address")
                );
            }

            // -----------------------------------------
            // 3. First Record
            // -----------------------------------------

            rs.first();

            System.out.println("\nFirst Record:");

            System.out.println(
                "Row " + rs.getRow() + ": " +
                rs.getInt("RollNo") + "\t" +
                rs.getString("Name") + "\t\t" +
                rs.getString("Address")
            );

            // -----------------------------------------
            // 4. Last Record
            // -----------------------------------------

            rs.last();

            System.out.println("\nLast Record:");

            System.out.println(
                "Row " + rs.getRow() + ": " +
                rs.getInt("RollNo") + "\t" +
                rs.getString("Name") + "\t\t" +
                rs.getString("Address")
            );

            // -----------------------------------------
            // 5. 2nd Record from Last
            // -----------------------------------------

            rs.relative(-1);

            System.out.println("\n2nd Record from Last:");

            System.out.println(
                "Row " + rs.getRow() + ": " +
                rs.getInt("RollNo") + "\t" +
                rs.getString("Name") + "\t\t" +
                rs.getString("Address")
            );

            // -----------------------------------------
            // 6. 2nd Record from Beginning
            // -----------------------------------------

            rs.absolute(2);

            System.out.println("\n2nd Record from Beginning:");

            System.out.println(
                "Row " + rs.getRow() + ": " +
                rs.getInt("RollNo") + "\t" +
                rs.getString("Name") + "\t\t" +
                rs.getString("Address")
            );

            // -----------------------------------------
            // Close resources
            // -----------------------------------------

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
