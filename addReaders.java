    import javax.swing.*;
    import java.awt.event.*;
    import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*; 
public class addReaders {
      String rDate;
      addReaders() {
        JFrame ReaderAdd = new JFrame("Add Reader");
        ReaderAdd.setBounds(300, 200, 700, 550);
        String dobString;
        Date dobDate = null;
        SimpleDateFormat dateFormat;
        JLabel readerFirstName, readerLastName, readerEmail, readerRegistrationDate, readerAddress,l;
        l  = new JLabel("Library Management");
        l.setBounds(230, 30, 200, 30);
        l.setFont(new Font("Releway",Font.BOLD,20));
        
        readerFirstName = new JLabel("First Name : ");
        readerFirstName.setBounds(5, 95, 150, 30);
        readerLastName = new JLabel("Last Name : ");
        readerLastName.setBounds(306, 95, 150, 30);
        readerEmail = new JLabel("Email : ");
        readerEmail.setBounds(5, 170, 150, 30);
        readerRegistrationDate = new JLabel("Registration Date : ");
        readerRegistrationDate.setBounds(306, 170, 150, 30);
        readerAddress = new JLabel("Address : ");
        readerAddress.setBounds(5, 240, 150, 30);

        ReaderAdd.add(l);
        ReaderAdd.add(readerFirstName);
        ReaderAdd.add(readerLastName);
        ReaderAdd.add(readerEmail);
        ReaderAdd.add(readerRegistrationDate);
        ReaderAdd.add(readerAddress);

        JTextField firstName, lastName, email, registrationDate,address;
        firstName = new JTextField("");
        firstName.setBounds(80, 100, 200, 30);
        lastName = new JTextField("");
        lastName.setBounds(380, 100, 200, 30);
        email = new JTextField("");
        email.setBounds(80, 175, 200, 30);
        registrationDate = new JTextField("");
        registrationDate.setBounds(430, 175, 200, 30);
        address = new JTextField("");
        address.setBounds(80, 245, 200, 30);

        ReaderAdd.add(firstName);
        ReaderAdd.add(lastName);
        ReaderAdd.add(email);
        ReaderAdd.add(registrationDate);
        ReaderAdd.add(address);

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(120, 305, 80, 40);
        JButton save1 = new JButton("Save");
        save1.setBounds(360, 305, 80, 40);
        save1.setBackground(Color.BLUE);
        save1.setForeground(Color.WHITE);

    
        ReaderAdd.add(cancel);
        ReaderAdd.add(save1);
    
        ReaderAdd.setLayout(null);
        ReaderAdd.setVisible(true);
        try {
          
           dateFormat = new SimpleDateFormat("dd/MM/yyyy");
           dobString = registrationDate.getText();
           dobDate = dateFormat.parse(dobString);
           rDate = dateFormat.format(dobDate);
        } catch(ParseException d) {
           System.out.print(d);
        } 
        save1.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String url = "jdbc:mysql://localhost:3306/librarymanagement";
              String username = "root";
              String password = "";
      
              try {
                  Class.forName("com.mysql.cj.jdbc.Driver");
                  Connection connection = DriverManager.getConnection(url, username, password);
      
                  Statement statement = connection.createStatement();
                  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                  String dobString = registrationDate.getText(); 
                  Date dobDate = dateFormat.parse(dobString); 
                  rDate = dateFormat.format(dobDate);
      
                  String query1 = "insert into `librarymanagement`.`reader`"
                          + "(`first_name`, `last_name`, `email`, `registration_date`, `address`)"
                          + "values('" + firstName.getText() + "','" + lastName.getText() + "','"
                          + email.getText() + "','" + rDate + "','" + address.getText() + "')";
      
                  int i = statement.executeUpdate(query1);
                  if (i > 0) {
                      System.out.println("ROW INSERTED");
                      JOptionPane.showMessageDialog(ReaderAdd, "Readers Added Successfully......");
                      ReaderAdd.dispose();
                  } else {
                      System.out.println("ROW NOT INSERTED");
                  }
                  connection.close();
              } catch (ClassNotFoundException c) {
                  System.out.println(c);
              } catch (SQLException SQLException) {
                  System.out.println(SQLException.getMessage());
              } catch (ParseException parseException) {
                  System.out.println("Error parsing date: " + parseException.getMessage());
              }
          }
      });
      cancel.addActionListener(event->{ 
        ReaderAdd.dispose();
    });    
      }
    }
    
    
