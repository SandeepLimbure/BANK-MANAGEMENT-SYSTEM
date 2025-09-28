import java.sql.*;

public class Conn {
    public Connection c;
    public Statement s;
    
    public Conn() {
        try {
            // Replace with your actual database details
            String url = "jdbc:mysql:///bankmanagementsystem";
            String user = "root";
            String password = "xxxxxxx";
            
            c = DriverManager.getConnection(url, user, password);
            c.setAutoCommit(false);
            s = c.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // --- THIS IS THE CORRECTED METHOD ---
    // It is no longer 'static' and does NOT create a new connection
    public double getBalance(String pinnumber) {
        double balance = 0.0;
        try {
            // It uses the existing connection 'c' from this object
            String query = "select * from bank where pin = ?";
            PreparedStatement pstmt = this.c.prepareStatement(query);
            pstmt.setString(1, pinnumber);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                if (rs.getString("type").equalsIgnoreCase("Deposit")) {
                    balance += Double.parseDouble(rs.getString("amount"));
                } else {
                    balance -= Double.parseDouble(rs.getString("amount"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return balance;
    }
}
