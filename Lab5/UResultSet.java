import java.sql.*;

public class UResultSet {

    public static void main(String[] args) {

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/lab5";
        String user = "jdbcuser";
        String password = "jdbc123";

        try {

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection con = DriverManager.getConnection(
                    url,
                    user,
                    password
            );

            System.out.println("Database connected successfully.");

            // Create scrollable and updatable Statement
            Statement st = con.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );

            // Execute SELECT query
            ResultSet rs = st.executeQuery(
                    "SELECT RollNo, Name, Address FROM Student"
            );

            // ------------------------------------
            // DELETE THE LAST ROW
            // ------------------------------------

            rs.last();

            int deletedRollNo = rs.getInt("RollNo");

            rs.deleteRow();

            System.out.println(
                    "Last student record deleted successfully."
            );

            System.out.println(
                    "Deleted RollNo: " + deletedRollNo
            );


            // ------------------------------------
            // INSERT A NEW ROW
            // ------------------------------------

            rs.moveToInsertRow();

            rs.updateInt("RollNo", 105);
            rs.updateString("Name", "John Doe");
            rs.updateString("Address", "Hyderabad");

            rs.insertRow();

            System.out.println(
                    "New student record inserted successfully."
            );


            // ------------------------------------
            // DISPLAY UPDATED RECORDS
            // ------------------------------------

            System.out.println("\nStudent records after update:");

            rs.beforeFirst();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("RollNo") + " "
                        + rs.getString("Name") + " "
                        + rs.getString("Address")
                );
            }


            // ------------------------------------
            // CLOSE RESOURCES
            // ------------------------------------

            rs.close();
            st.close();
            con.close();

            System.out.println("\nConnection closed.");

        } catch (ClassNotFoundException e) {

            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Database error occurred.");
            e.printStackTrace();
        }
    }
}
