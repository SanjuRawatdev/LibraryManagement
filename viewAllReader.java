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
    
public class viewAllReader {
    String cDate,rDate;
    String selectOption,selectbook,booknm,select;
    String[] comboBoxItems;
    JComboBox<String> comboBox,combo;
          viewAllReader() {
            JFrame  viewAllReader = new JFrame("View All Readers");
            viewAllReader.setBounds(300, 200, 700, 550);
            viewAllReader.setVisible(true);
    String url = "jdbc:mysql://localhost:3306/librarymanagement";
    String username = "root";
    String password = "";

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection(url, username, password);

      String query = "SELECT * FROM reader";
      PreparedStatement preparedStatement = connection.prepareStatement(query);

      ResultSet resultSet = preparedStatement.executeQuery();

      DefaultTableModel tableModel = new DefaultTableModel();
      tableModel.addColumn("First Name");
      tableModel.addColumn("Last Name");
      tableModel.addColumn("Email");
      tableModel.addColumn("Registration Date");
      tableModel.addColumn("Address");

      while (resultSet.next()) {
        String readerFirstName = resultSet.getString("first_name");
        String readerLastName = resultSet.getString("last_name");
        String readerEmail = resultSet.getString("email");
        String readerRegistrationDate = resultSet.getString("registration_date");
        String readerAddress = resultSet.getString("address");

        tableModel.addRow(new Object[]{readerFirstName, readerLastName, readerEmail, readerRegistrationDate, readerAddress});
      }

      JTable readerTable = new JTable(tableModel);
      JScrollPane scrollPane = new JScrollPane(readerTable);

      viewAllReader.getContentPane().removeAll(); 
      viewAllReader.getContentPane().add(scrollPane);
      viewAllReader.revalidate();

      resultSet.close();
      preparedStatement.close();
      connection.close();
    } catch (ClassNotFoundException | SQLException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(viewAllReader, "Error: Unable to retrieve book information.");
    }
  }
}