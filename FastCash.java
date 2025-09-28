import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    
    JButton b100, b500, b1000, b2000, b5000, b10000, back;
    String pinnumber;
    
    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
        
        JLabel text = new JLabel("SELECT WITHDRAWAL AMOUNT");
        text.setBounds(210, 300, 700, 35);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);
        
        b100 = new JButton("Rs 100");
        b100.setBounds(170, 415, 150, 30);
        b100.addActionListener(this);
        image.add(b100);
        
        b500 = new JButton("Rs 500");
        b500.setBounds(355, 415, 150, 30);
        b500.addActionListener(this);
        image.add(b500);
        
        b1000 = new JButton("Rs 1000");
        b1000.setBounds(170, 450, 150, 30);
        b1000.addActionListener(this);
        image.add(b1000);
        
        b2000 = new JButton("Rs 2000");
        b2000.setBounds(355, 450, 150, 30);
        b2000.addActionListener(this);
        image.add(b2000);
        
        b5000 = new JButton("Rs 5000");
        b5000.setBounds(170, 485, 150, 30);
        b5000.addActionListener(this);
        image.add(b5000);
        
        b10000 = new JButton("Rs 10000");
        b10000.setBounds(355, 485, 150, 30);
        b10000.addActionListener(this);
        image.add(b10000);
        
        back = new JButton("BACK");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);
        
        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else {
            // Get the amount from the button's text by removing "Rs "
            String amount = ((JButton) e.getSource()).getText().substring(3);
            Conn conn = new Conn();
            
            try {
                // Get the current balance
                double currentBalance = conn.getBalance(pinnumber);
                
                // Convert the amount to a number for comparison
                double withdrawAmount = Double.parseDouble(amount);
                
                // Check for sufficient balance
                if (currentBalance < withdrawAmount) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }
                
                Date date = new Date();
                // Use a secure PreparedStatement
                String query = "insert into bank values(?, ?, 'Withdrawl', ?)";
                PreparedStatement pstmt = conn.c.prepareStatement(query);
                pstmt.setString(1, pinnumber);
                // Use date.toString() for the full, detailed timestamp
                pstmt.setString(2, date.toString());
                pstmt.setString(3, amount);
                pstmt.executeUpdate();
                
                // Commit the transaction to save it immediately
                conn.c.commit();
                
                JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Successfully");
                
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
                
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
    
    public static void main(String[] args) {
        new FastCash("");
    }
}
