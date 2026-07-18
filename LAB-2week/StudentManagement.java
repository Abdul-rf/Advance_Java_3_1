import java.sql.*;

public class StudentManagement {

    // Database URL
    static final String URL = "jdbc:mysql://localhost:3306/college";

    // Database Username
    static final String USER = "root";

    // Database Password
    static final String PASSWORD = "Root@123";   // Change according to your MySQL password

    public static void main(String[] args) {

        try {

            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Database Connected Successfully.\n");

            // ----------------------------------------------------
            // a. Create Student Table
            // ----------------------------------------------------

            Statement st = con.createStatement();

            String createTable = "CREATE TABLE IF NOT EXISTS Student("
                    + "RollNo INT PRIMARY KEY,"
                    + "Name VARCHAR(50),"
                    + "Address VARCHAR(100))";

            st.executeUpdate(createTable);

            System.out.println("Student Table Created.\n");

            // ----------------------------------------------------
            // Insert Few Records
            // ----------------------------------------------------

            String insert = "INSERT IGNORE INTO Student VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(insert);

            ps.setInt(1,101);
            ps.setString(2,"Abdul");
            ps.setString(3,"Bhimavaram");
            ps.executeUpdate();

            ps.setInt(1,102);
            ps.setString(2,"Rahul");
            ps.setString(3,"Hyderabad");
            ps.executeUpdate();

            ps.setInt(1,103);
            ps.setString(2,"Suresh");
            ps.setString(3,"Chennai");
            ps.executeUpdate();

            System.out.println("Initial Records Inserted.\n");

            // ----------------------------------------------------
            // b. Display Records
            // ----------------------------------------------------

            System.out.println("----- Student Records -----");

            PreparedStatement display = con.prepareStatement("SELECT * FROM Student");

            ResultSet rs = display.executeQuery();

            while(rs.next())
            {
                System.out.println(
                        rs.getInt("RollNo")+"  "
                        +rs.getString("Name")+"  "
                        +rs.getString("Address"));
            }

            // ----------------------------------------------------
            // c. Insert Two Records
            // ----------------------------------------------------

            PreparedStatement insertTwo = con.prepareStatement(
                    "INSERT INTO Student VALUES(?,?,?)");

            insertTwo.setInt(1,104);
            insertTwo.setString(2,"Kiran");
            insertTwo.setString(3,"Vijayawada");
            insertTwo.executeUpdate();

            insertTwo.setInt(1,105);
            insertTwo.setString(2,"Ramesh");
            insertTwo.setString(3,"Visakhapatnam");
            insertTwo.executeUpdate();

            System.out.println("\nTwo Records Inserted.\n");

            // ----------------------------------------------------
            // d. Update One Record
            // ----------------------------------------------------

            PreparedStatement update = con.prepareStatement(
                    "UPDATE Student SET Address=? WHERE RollNo=?");

            update.setString(1,"Bangalore");
            update.setInt(2,102);

            int u = update.executeUpdate();

            System.out.println("Updated Records = "+u+"\n");

            // ----------------------------------------------------
            // e. Delete One Record
            // ----------------------------------------------------

            PreparedStatement delete = con.prepareStatement(
                    "DELETE FROM Student WHERE RollNo=?");

            delete.setInt(1,103);

            int d = delete.executeUpdate();

            System.out.println("Deleted Records = "+d+"\n");

            // ----------------------------------------------------
            // f. Display Final Records
            // ----------------------------------------------------

            System.out.println("----- Final Student Records -----");

            PreparedStatement finalDisplay =
                    con.prepareStatement("SELECT * FROM Student");

            ResultSet rs2 = finalDisplay.executeQuery();

            while(rs2.next())
            {
                System.out.println(
                        rs2.getInt("RollNo")+"  "
                        +rs2.getString("Name")+"  "
                        +rs2.getString("Address"));
            }

            con.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
