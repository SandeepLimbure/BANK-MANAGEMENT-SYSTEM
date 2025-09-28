import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MiniStatement extends JFrame {
    
    String pinnumber;
    
    MiniStatement(String pinnumber) {
        this.pinnumber = pinnumber;
        setTitle("Mini Statement");
        setLayout(null);
        
        // We will add this label to a scroll pane later
        JLabel mini = new JLabel();
        
        JLabel bank = new JLabel("Indian Bank");
        bank.setBounds(150, 20, 100, 20);
        add(bank);
        
        JLabel card = new JLabel();
        card.setBounds(20, 80, 300, 20);
        add(card);
        
        JLabel balance = new JLabel();
        balance.setBounds(20, 400, 300, 20);
        add(balance);
        
        try {
            Conn conn = new Conn();
            String query = "select * from login where pin = ?";
            PreparedStatement pstmt = conn.c.prepareStatement(query);
            pstmt.setString(1, pinnumber);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                card.setText("Card Number: " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));
            }
        } catch (Exception h) {
            System.out.println(h);
        }
        
        try {
            Conn conn = new Conn();
            double bal = conn.getBalance(pinnumber); // Use the efficient getBalance method
            balance.setText("Your current account balance is Rs " + bal);
            
            String query = "select * from bank where pin = ?";
            PreparedStatement pstmt = conn.c.prepareStatement(query);
            pstmt.setString(1, pinnumber);
            ResultSet rs = pstmt.executeQuery();
            
            StringBuilder statementText = new StringBuilder("<html>");
            while (rs.next()) {
                statementText.append(rs.getString("date"))
                        .append("&nbsp;&nbsp;")
                        .append(rs.getString("type"))
                        .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(rs.getString("amount"))
                        .append("<br><br>");
            }
            statementText.append("</html>");
            mini.setText(statementText.toString());
            
        } catch (Exception b) {
            System.out.println(b);
        }
        
        // --- THIS IS THE VISUAL FIX ---
        // 1. Create a JScrollPane and put the 'mini' label inside it.
        JScrollPane scrollPane = new JScrollPane(mini);
        scrollPane.setBounds(20, 140, 350, 200);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Optional: for a cleaner look
        
        // 2. Add the scrollPane to the frame instead of the label directly.
        add(scrollPane);
        
        setSize(400, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.white);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new MiniStatement("");
    }
}
