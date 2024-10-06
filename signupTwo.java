
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class signupTwo extends JFrame implements ActionListener{
    JTextField pan,aadhar;
    JButton next;
    JRadioButton syes,sno,eyes,eno;
    JComboBox religion , category ,income, occupation , education;
    String formno;


    signupTwo(String formno){

        this.formno=formno;
        setLayout(null);
        setTitle("NEW APPLICATION FORM : PAGE 2");

        JLabel additionalDetails = new JLabel("Page 2 : Additional Details " );
        additionalDetails.setFont(new Font("Raleway",Font.BOLD,22));
        additionalDetails.setBounds(290,80,400,30);
        add(additionalDetails);

        JLabel name = new JLabel("Religion :  " );
        name.setFont(new Font("Raleway",Font.BOLD,20));
        name.setBounds(100,140,100,30);
        add(name);

        String []valReligion = {"Hindu","Sikh","Christian","Muslim"};
        religion = new JComboBox(valReligion);
        religion.setBounds(300,140,400,30);
        religion.setBackground(Color.white);
        add(religion);

        JLabel fname = new JLabel("category :  " );
        fname.setFont(new Font("Raleway",Font.BOLD,20));
        fname.setBounds(100,190,200,30);
        add(fname);

        String []valCategory = {"General","OBC","SC","ST","Other"};
        category = new JComboBox<>(valCategory);
        category.setBounds(300,190,400,30);
        category.setBackground(Color.white);
        add(category);

        JLabel dob = new JLabel("Income :  " );
        dob.setFont(new Font("Raleway",Font.BOLD,20));
        dob.setBounds(100,240,200,30);
        add(dob);

        String []valIncome = {"Null","<1,50,000","< 2,50,000","< 5,00,000","upto 10,00,000"};
        income = new JComboBox<>(valIncome);
        income.setBounds(300,240,400,30);
        income.setBackground(Color.white);
        add(income);

        JLabel gender = new JLabel("Educational  " );
        gender.setFont(new Font("Raleway",Font.BOLD,20));
        gender.setBounds(100,290,200,30);
        add(gender);

        JLabel email = new JLabel("Qualification :  " );
        email.setFont(new Font("Raleway",Font.BOLD,20));
        email.setBounds(100,315,200,30);
        add(email);

        String []educationValues = {"Uneducation","Graduate","Post Graduate","Doctrate","Others"};
        education = new JComboBox<>(educationValues);
        education.setBounds(300,315,400,30);
        education.setBackground(Color.white);
        add(education);

        JLabel marital = new JLabel("Occupation :  " );
        marital.setFont(new Font("Raleway",Font.BOLD,20));
        marital.setBounds(100,390,200,30);
        add(marital);

        String []OccupationValues = {"Salaried","Self-Employed","Bussiness-man","Student","Retired","Others"};
        occupation = new JComboBox<>(OccupationValues);
        occupation.setBounds(300,390,400,30);
        occupation.setBackground(Color.white);
        add(occupation);

        JLabel address = new JLabel("PAN Number :  " );
        address.setFont(new Font("Raleway",Font.BOLD,20));
        address.setBounds(100,440,200,30);
        add(address);

        pan = new JTextField();
        pan.setFont(new Font("Raleway",Font.BOLD,14));
        pan.setBounds(300,440,400,30);
        add(pan);

        JLabel City = new JLabel("Aadhar Number :  " );
        City.setFont(new Font("Raleway",Font.BOLD,20));
        City.setBounds(100,490,200,30);
        add(City);

        aadhar = new JTextField();
        aadhar.setFont(new Font("Raleway",Font.BOLD,14));
        aadhar.setBounds(300,490,400,30);
        add(aadhar);

        JLabel state = new JLabel("Senior Citizen :  " );
        state.setFont(new Font("Raleway",Font.BOLD,20));
        state.setBounds(100,540,200,30);
        add(state);

        syes = new JRadioButton("YES");
        syes.setBackground(Color.white);
        syes.setBounds(300,540,60,30);
        add(syes);

        sno = new JRadioButton("NO");
        sno.setBackground(Color.white);
        sno.setBounds(450,540,60,30);
        add(sno);

        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(syes);
        gendergroup.add(sno);

        JLabel pincode = new JLabel("Existing Account :  " );
        pincode.setFont(new Font("Raleway",Font.BOLD,20));
        pincode.setBounds(100,590,200,30);
        add(pincode);

        eyes = new JRadioButton("YES");
        eyes.setBackground(Color.white);
        eyes.setBounds(300,590,60,30);
        add(eyes);

        eno = new JRadioButton("NO");
        eno.setBackground(Color.white);
        eno.setBounds(450,590,80,30);
        add(eno);

        ButtonGroup egendergroup = new ButtonGroup();
        egendergroup.add(eyes);
        egendergroup.add(eno);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.white);
        next.setFont(new Font("Raleway",Font.BOLD,14));
        next.setBounds(620,660,80,30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.white);
        setSize(850,800);
        setLocation(350,10);
        setVisible(true);
    }
    public static void main(String[] args) {
        new signupTwo("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sreligion = (String) religion.getSelectedItem();
        String scategory = (String) category.getSelectedItem();
        String sincome = (String)income.getSelectedItem() ;
        String seducation = (String)education.getSelectedItem() ;
        String soccupation = (String)occupation.getSelectedItem();
        String seniorcitizen = null;
        if(syes.isSelected()){
            seniorcitizen = "YES";
        } else if (sno.isSelected()) {
            seniorcitizen="NO";
        }
        String existingaccount = null;
        if(eyes.isSelected()){
            existingaccount="YES";
        } else if (eno.isSelected()) {
            existingaccount="NO";
        }
        String span = pan.getText();
        String saadhar = aadhar.getText();

        try{
                Conn c = new Conn();
                String query = "insert into signuptwo values('"+formno+"','"+sreligion+"','"+scategory+"','"+sincome+"','"+seducation+"','"+soccupation+"','"+span+"','"+saadhar+"','"+seniorcitizen+"','"+existingaccount+"')";
                c.s.executeUpdate(query);

                //signup3 object
                setVisible(false);
                new signupThree(formno).setVisible(true);
        }catch (Exception a){
            System.out.println(a);
        }
    }
}

