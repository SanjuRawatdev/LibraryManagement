import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class viewAllTransactions {
String cDate,rDate;
String selectOption,selectbook,booknm,select;
String[] comboBoxItems;
JComboBox<String> comboBox,combo;
      viewAllTransactions() {
        JFrame viewAllTransactions = new JFrame("View All Books");
        viewAllTransactions.setBounds(300, 200, 700, 550);
        viewAllTransactions.setVisible(true);
    String url = "jdbc:mysql://localhost:3306/librarymanagement";
    String username = "root";
    String password = "";

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection(url, username, password);

      String query = "SELECT * FROM transactions";
      PreparedStatement preparedStatement = connection.prepareStatement(query);

      ResultSet resultSet = preparedStatement.executeQuery();

      DefaultTableModel tableModel = new DefaultTableModel();
      tableModel.addColumn("ID");
      tableModel.addColumn("Book_ID End Book_Name");
      tableModel.addColumn("Reader_ID");
      tableModel.addColumn("Checkout_Date");
      tableModel.addColumn("Return_Date");
      tableModel.addColumn("Status");

      while (resultSet.next()) {
        String renewalsId = resultSet.getString("Id");
        String renewalsBook_Id = resultSet.getString("book_Id");
        String renewalsReader_Id = resultSet.getString("reader_Id");
        String renewalsCheckout_Date = resultSet.getString("checkout_date");
        String renewalsReturn_Date = resultSet.getString("return_date");
        String status = resultSet.getString("status");

        tableModel.addRow(new Object[]{renewalsId, renewalsBook_Id, renewalsReader_Id, renewalsCheckout_Date, renewalsReturn_Date,status});
      }

      JTable readerTable = new JTable(tableModel);
      JScrollPane scrollPane = new JScrollPane(readerTable);

      viewAllTransactions.getContentPane().removeAll(); 
      viewAllTransactions.getContentPane().add(scrollPane);
      viewAllTransactions.revalidate();

      resultSet.close();
      preparedStatement.close();
      connection.close();
    } catch (ClassNotFoundException | SQLException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(viewAllTransactions, "Error: Unable to retrieve book information.");
    }
  }
}
