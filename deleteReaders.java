import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class deleteReaders {
    deleteReaders() {
        JFrame readersUpdate = new JFrame("Delete Readers");
        readersUpdate.setBounds(300, 200, 600, 550);
        JLabel readerId, readerFirstName, readerLastName,l;

        l  = new JLabel("Library Management");
        l.setBounds(230, 30, 200, 30);
        l.setFont(new Font("Releway",Font.BOLD,20));

            readerId = new JLabel("Reader Id : ");
        readerId.setBounds(5, 95, 150, 30);
        JButton search = new JButton("Search");
        search.setBounds(360, 95, 80, 40);
        readerFirstName = new JLabel("First Name : ");
        readerFirstName.setBounds(5, 170, 150, 30);
        readerLastName = new JLabel("Last Name : ");
        readerLastName.setBounds(306, 170, 150, 30);

        readersUpdate.add(l);
        readersUpdate.add(readerId);
        readersUpdate.add(search);
        readersUpdate.add(readerFirstName);
        readersUpdate.add(readerLastName);

        JTextField id, firstName, lastName;
        id = new JTextField("");
        id.setBounds(80, 100, 200, 30);
        firstName = new JTextField("");
        firstName.setBounds(80, 175, 200, 30);
        lastName = new JTextField("");
        lastName.setBounds(380, 175, 200, 30);

        readersUpdate.add(id);
        readersUpdate.add(firstName);
        readersUpdate.add(lastName);

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(120, 390, 80, 40);
        JButton update = new JButton("Delete");
        update.setBounds(360, 390, 80, 40);
        update.setBackground(Color.BLUE);
        update.setForeground(Color.WHITE);
    
        readersUpdate.add(cancel);
        readersUpdate.add(update);

        readersUpdate.setLayout(null);
        readersUpdate.setVisible(true);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/librarymanagement";
                String username = "root";
                String password = "";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);

                    String query = "DELETE FROM `reader` WHERE reader_Id=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, id.getText());

                    int rowsUpdated = preparedStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(readersUpdate, "Reader deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(readersUpdate, "Error: Unable to delete the reader.");
                    }

                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    // Handle database errors and display an error message
                    JOptionPane.showMessageDialog(readersUpdate, "Error: Unable to delete the reader.");
                }
            }
        });

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/librarymanagement";
                String username = "root";
                String password = "";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);

                    String query = "SELECT * FROM reader WHERE reader_Id=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, id.getText());

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        firstName.setText(resultSet.getString("first_name"));
                        lastName.setText(resultSet.getString("last_name"));
                    } else {
                        JOptionPane.showMessageDialog(readersUpdate, "Reader not found.");
                    }

                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(readersUpdate, "Error: Unable to search for the reader.");
                }
            }
        });
        cancel.addActionListener(event->{ 
            readersUpdate.dispose();
        });
    }
}
