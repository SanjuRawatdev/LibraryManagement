import javax.swing.*;

import java.sql.*;
public class viewBooks {
    viewBooks()
    {
        JFrame viewbooks = new JFrame(" Book Checkouts");
        viewbooks.setBounds(300, 200, 700, 550);
        viewbooks.setVisible(true);

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

      viewBooks.getContentPane().removeAll(); 
      viewBooks.getContentPane().add(scrollPane);
      viewBooks.revalidate(); 
      resultSet.close();
      preparedStatement.close();
      connection.close();
    } catch (ClassNotFoundException | SQLException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(viewBooks, "Error: Unable to retrieve book information.");
    }
  }
    }

