import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class checkoutBooks {
    String selectOption, booknm;
    JComboBox<String> comboBox, combo;

    checkoutBooks() {
        JFrame checkoutBooks = new JFrame("Book Checkouts");
        checkoutBooks.setBounds(300, 200, 700, 550);

        JLabel bookId, readerId, bookCheckoutDate, bookReturnDate, l, bookStatus;

        String[] initialData = {"Select a book..."};
        comboBox = new JComboBox<>(initialData);
        String[] comboBoxItems = {"checkout", "return"};
        combo = new JComboBox<>(comboBoxItems);

        l = new JLabel("Library Management");
        l.setBounds(250, 30, 200, 30);
        l.setFont(new Font("Releway",Font.BOLD,20));

        bookId = new JLabel("Book Id : ");
        bookId.setBounds(5, 95, 150, 30);
        readerId = new JLabel("Reader Id : ");
        readerId.setBounds(306, 95, 150, 30);
        bookCheckoutDate = new JLabel("Checkout Date : ");
        bookCheckoutDate.setBounds(5, 205, 150, 30);
        bookReturnDate = new JLabel("Return Date : ");
        bookReturnDate.setBounds(306, 205, 150, 30);
        bookStatus = new JLabel("Status : ");
        bookStatus.setBounds(5, 295, 150, 30);

        checkoutBooks.add(l);
        checkoutBooks.add(bookId);
        checkoutBooks.add(readerId);
        checkoutBooks.add(bookCheckoutDate);
        checkoutBooks.add(bookReturnDate);
        checkoutBooks.add(bookStatus);

        JTextField id, checkoutDate, returnDate;
        l  = new JLabel("Library Management");
        l.setBounds(230, 30, 200, 30);
        l.setFont(new Font("Releway",Font.BOLD,20));
    
        id = new JTextField("");
        id.setBounds(370, 100, 200, 30);
        checkoutDate = new JTextField("");
        checkoutDate.setBounds(100, 205, 200, 30);
        returnDate = new JTextField("");
        returnDate.setBounds(390, 205, 200, 30);

        checkoutBooks.add(comboBox);
        comboBox.setBounds(90, 100, 200, 30);
        checkoutBooks.add(id);
        checkoutBooks.add(checkoutDate);
        checkoutBooks.add(returnDate);
        checkoutBooks.add(combo);
        combo.setBounds(80, 295, 200, 30);

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(120, 350, 80, 40);
        JButton save = new JButton("Save");
        save.setBounds(360, 350, 80, 40);
        save.setBackground(Color.BLUE);
        save.setForeground(Color.WHITE);
    
        checkoutBooks.add(cancel);
        checkoutBooks.add(save);

        checkoutBooks.setLayout(null);
        checkoutBooks.setVisible(true);

        try {
            String url = "jdbc:mysql://localhost:3306/librarymanagement";
            String username = "root";
            String password = "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            String query2 = "SELECT * FROM books";

            ResultSet resultSet = statement.executeQuery(query2);
            while (resultSet.next()) {
                String bookid = resultSet.getString("book_Id");
                booknm = resultSet.getString("title");

                comboBox.addItem(bookid);
                selectOption = (String) comboBox.getSelectedItem() + " - " + booknm;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(checkoutBooks, "Failed to retrieve book data. Please try again.");
        }

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/librarymanagement";
                String username = "root";
                String password = "";

                String selectOption = (String) comboBox.getSelectedItem() + " - " + booknm;
                String readerId = id.getText().trim();
                String checkoutDateText = checkoutDate.getText().trim();
                String returnDateText = returnDate.getText().trim();
                String select = (String) combo.getSelectedItem();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);

                    Statement statement = connection.createStatement();
                    String query1 = "INSERT INTO `librarymanagement`.`transactions` "
                            + "(`book_Id`, `reader_Id`, `checkout_date`, `return_date`, `status`) "
                            + "VALUES('" + selectOption + "','" + readerId + "','" + checkoutDateText + "','"
                            + returnDateText + "','" + select + "')";

                    int i = statement.executeUpdate(query1);

                    if (i > 0) {
                        JOptionPane.showMessageDialog(checkoutBooks, "Books checked out successfully.");
                        checkoutBooks.dispose();
                    } else {
                        JOptionPane.showMessageDialog(checkoutBooks, "Failed to check out books.");
                    }
                    connection.close();
                } catch (ClassNotFoundException c) {
                    JOptionPane.showMessageDialog(checkoutBooks, "Database connection error.");
                } catch (SQLException SQLException) {
                    JOptionPane.showMessageDialog(checkoutBooks, "SQL error: " + SQLException.getMessage());
                }
            }
        });

        cancel.addActionListener(event -> {
            checkoutBooks.dispose();
        });
    }
}
