import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    
    JButton deposit, back;
    JTextField amount;
    String pinnumber;
    
    Deposit(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
        
        JLabel text = new JLabel("Enter the amount you want to deposit");
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170, 300, 400, 20);
        image.add(text);
        
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 350, 320, 25);
        image.add(amount);
        
        deposit = new JButton("Deposit");
        deposit.setBounds(355, 485, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);
        
        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);
        
        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deposit) {
            String number = amount.getText();
            Date date = new Date(); // Creates a java.util.Date object with full timestamp
            
            if (number.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit.");
                return;
            }
            
            try {
                double depositAmount = Double.parseDouble(number);
                if (depositAmount <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter an amount greater than zero.");
                    return;
                }
                
                Conn conn = new Conn();
                String query = "insert into bank values(?, ?, 'Deposit', ?)";
                PreparedStatement pstmt = conn.c.prepareStatement(query);
                pstmt.setString(1, pinnumber);
                
                // --- THIS IS THE FIX ---
                // Use date.toString() to get the full, detailed timestamp string
                pstmt.setString(2, date.toString());
                
                pstmt.setString(3, number);
                pstmt.executeUpdate();
                
                conn.c.commit();
                
                JOptionPane.showMessageDialog(null, "Rs " + number + " Deposited Successfully");
                
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
                
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Invalid amount. Please enter numbers only.");
            } catch (Exception ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "An error occurred. Please try again.");
            }
            
        } else if (e.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        new Deposit("");
    }
}
