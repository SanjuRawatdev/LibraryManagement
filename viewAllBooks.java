import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class viewAllBooks {
String cDate,rDate;
String selectOption,selectbook,booknm,select;
String[] comboBoxItems;
JComboBox<String> comboBox,combo;
      viewAllBooks() {
        JFrame viewAllBooks = new JFrame("View All Books");
        viewAllBooks.setBounds(300, 200, 700, 550);
        viewAllBooks.setVisible(true);
    String url = "jdbc:mysql://localhost:3306/librarymanagement";
    String username = "root";
    String password = "";

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection(url, username, password);

      String query = "SELECT * FROM books";
      PreparedStatement preparedStatement = connection.prepareStatement(query);

      ResultSet resultSet = preparedStatement.executeQuery();

      DefaultTableModel tableModel = new DefaultTableModel();
      tableModel.addColumn("Title");
      tableModel.addColumn("Author");
      tableModel.addColumn("ISBN");
      tableModel.addColumn("Availability");

      while (resultSet.next()) {
        String bookTitle = resultSet.getString("title");
        String bookAuthor = resultSet.getString("author");
        String bookIsbn = resultSet.getString("isbn");
        String bookAvailability = resultSet.getString("availability");

        tableModel.addRow(new Object[]{bookTitle, bookAuthor, bookIsbn, bookAvailability});
      }

      JTable bookTable = new JTable(tableModel);
      JScrollPane scrollPane = new JScrollPane(bookTable);

      viewAllBooks.getContentPane().removeAll(); 
      viewAllBooks.getContentPane().add(scrollPane);
      viewAllBooks.revalidate(); 
      resultSet.close();
      preparedStatement.close();
      connection.close();
    } catch (ClassNotFoundException | SQLException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(viewAllBooks, "Error: Unable to retrieve book information.");
    }
}
}
