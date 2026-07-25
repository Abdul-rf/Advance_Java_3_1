import java.sql.*;

public class JDBCStoredProcDemo {

    static final String URL = "jdbc:mysql://localhost:3306/company";
    static final String USER = "root";
    static final String PASSWORD = "Root@123";   // Change if needed

    public static void main(String[] args) {

        try {

            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected Successfully.");

            // -----------------------------
            // Call insert_employee Procedure
            // -----------------------------
            CallableStatement insertStmt =
                    conn.prepareCall("{call insert_employee(?, ?, ?)}");

            insertStmt.setInt(1, 101);
            insertStmt.setString(2, "John Doe");
            insertStmt.setDouble(3, 55000.00);

            insertStmt.execute();

            System.out.println("Employee Record Inserted Successfully.");

            // ---------------------------------
            // Call get_salary_by_id Procedure
            // ---------------------------------
            CallableStatement salaryStmt =
                    conn.prepareCall("{call get_salary_by_id(?, ?)}");

            salaryStmt.setInt(1, 101);

            salaryStmt.registerOutParameter(2, Types.DECIMAL);

            salaryStmt.execute();

            double salary = salaryStmt.getDouble(2);

            System.out.println("Salary of Employee ID 101 : " + salary);

            // Close Resources
            insertStmt.close();
            salaryStmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver Not Found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
