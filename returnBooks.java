import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
public class returnBooks {
    String cDate, rDate;
    String selectOption, selectbook, booknm, statusText;
    String[] comboBoxItems;
    JComboBox<String> comboBox, combo, selects;
    JTextField id, bId, rId, checkoutDate, returnDate, status;

    returnBooks() {
        String docString, dorString;
        Date docDate = null, dorDate = null;
        SimpleDateFormat dateFormat;
        JFrame returnBooks = new JFrame(" Book Returns");
        returnBooks.setBounds(300, 200, 700, 550);
        JLabel checkoutId, bookId, readerId, bookCheckoutDate, bookReturnDate, l, bookStatus;
        l  = new JLabel("Library Management");
        l.setBounds(230, 30, 200, 30);
        l.setFont(new Font("Releway",Font.BOLD,20));
    
        String[] initialData = {};
        comboBox = new JComboBox<>(initialData);

        String[] comboBoxItems = { "checkout", "return" };
        combo = new JComboBox<>(comboBoxItems);
        combo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    statusText = (String) combo.getSelectedItem();
                }
            }
        });

        l = new JLabel("Library Management");
        l.setBounds(250, 30, 200, 30);
        l.setFont(new Font("Releway",Font.BOLD,20));

        checkoutId = new JLabel("Checkout Id : ");
        checkoutId.setBounds(5, 95, 150, 30);
        JButton search = new JButton("Search");
        search.setBounds(370, 95, 80, 40);
        bookId = new JLabel("Book Id : ");
        bookId.setBounds(5, 170, 150, 30);
        readerId = new JLabel("Reader Id : ");
        readerId.setBounds(306, 170, 150, 30);
        bookCheckoutDate = new JLabel("Checkout Date : ");
        bookCheckoutDate.setBounds(5, 230, 150, 30);
        bookReturnDate = new JLabel("Return Date : ");
        bookReturnDate.setBounds(306, 230, 150, 30);
        bookStatus = new JLabel("Status : ");
        bookStatus.setBounds(5, 295, 150, 30);

        returnBooks.add(l);
        returnBooks.add(checkoutId);
        returnBooks.add(search);
        returnBooks.add(bookId);
        returnBooks.add(readerId);
        returnBooks.add(bookCheckoutDate);
        returnBooks.add(bookReturnDate);
        returnBooks.add(bookStatus);

        id = new JTextField("");
        id.setBounds(85, 100, 200, 30);
        bId = new JTextField("");
        bId.setBounds(80, 175, 200, 30);
        bId.setEnabled(false);
        rId = new JTextField("");
        rId.setBounds(370, 175, 200, 30);
        rId.setEnabled(false);
        checkoutDate = new JTextField("");
        checkoutDate.setBounds(100, 235, 200, 30);
        checkoutDate.setEnabled(false);
        returnDate = new JTextField("");
        returnDate.setBounds(390, 235, 200, 30);
        returnDate.setEnabled(false);

        returnBooks.add(bId);
        comboBox.setBounds(90, 175, 200, 30);
        returnBooks.add(id);
        returnBooks.add(rId);
        returnBooks.add(checkoutDate);
        returnBooks.add(returnDate);
        returnBooks.add(combo);
        combo.setBounds(80, 295, 200, 30);

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(120, 350, 80, 40);
        JButton save = new JButton("Save");
        save.setBounds(360, 350, 80, 40);
    save.setBackground(Color.BLUE);
    save.setForeground(Color.WHITE);

        returnBooks.add(cancel);
        returnBooks.add(save);

        returnBooks.setLayout(null);
        returnBooks.setVisible(true);

        try {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            docString = checkoutDate.getText();
            dorString = returnDate.getText();
            if (!docString.isEmpty() && !dorString.isEmpty()) {
                docDate = dateFormat.parse(docString);
                dorDate = dateFormat.parse(dorString);
                cDate = dateFormat.format(docDate);
                rDate = dateFormat.format(dorDate);
            }
        } catch (ParseException d) {
            System.out.print(d);
        }

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
            JOptionPane.showMessageDialog(returnBooks, "Not Select Books, Please Try again");
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

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);

                    Statement statement = connection.createStatement();
                    String query1 = "INSERT INTO `librarymanagement`.`transactions`"
                            + "(`book_Id` , `reader_Id` , `checkout_date` , `return_date`,`status`)"
                            + "VALUES ('" + selectOption + "','" + readerId + "','" + checkoutDateText + "','"
                            + returnDateText + "','" + statusText + "')";

                    int i = statement.executeUpdate(query1);

                    if (i > 0) {
                        System.out.println("ROW INSERTED");
                        JOptionPane.showMessageDialog(returnBooks, "Books return Successfully......");
                        returnBooks.dispose();
                    } else {
                        System.out.println("ROW NOT INSERTED");
                    }
                    connection.close();
                } catch (ClassNotFoundException c) {
                    System.out.println(c);
                    JOptionPane.showMessageDialog(returnBooks, "jai mata di");
                } catch (SQLException SQLException) {
                    System.out.println(SQLException.getMessage());
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

                    String query = "SELECT * FROM transactions WHERE Id=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, id.getText());

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        bId.setText(resultSet.getString("book_Id"));
                        rId.setText(resultSet.getString("reader_Id"));
                        checkoutDate.setText(resultSet.getString("checkout_date"));
                        returnDate.setText(resultSet.getString("return_date"));
                        statusText = resultSet.getString("status");
                        combo.setSelectedItem(statusText);
                    } else {
                        JOptionPane.showMessageDialog(returnBooks, "Book not found.");
                    }

                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(returnBooks, "Error: Unable to search for the book.");
                }
            }
        });

        cancel.addActionListener(event -> {
            returnBooks.dispose();
        });
    }
}
