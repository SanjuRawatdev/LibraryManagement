import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class deleteBooks {
  deleteBooks() {
    JFrame BooksDelete = new JFrame("Update Books");
    BooksDelete.setBounds(300, 200, 600, 550);
    JLabel bookId, bookTitle, bookIsbn,l;

    l  = new JLabel("Library Management");
    l.setBounds(230, 30, 200, 30);
    l.setFont(new Font("Releway",Font.BOLD,20));
    bookId = new JLabel("Book Id : ");
    bookId.setBounds(5, 95, 150, 30);
    JButton search = new JButton("Search");
    search.setBounds(360, 95, 80, 40);
    bookTitle = new JLabel("Title : ");
    bookTitle.setBounds(5, 150, 150, 30);
    bookIsbn = new JLabel("ISBN : ");
    bookIsbn.setBounds(306, 150, 150, 30);
    BooksDelete.add(l);
    BooksDelete.add(bookId);
    BooksDelete.add(search);
    BooksDelete.add(bookTitle);
    BooksDelete.add(bookIsbn);

    JTextField id,title,isbn ;
    id = new JTextField("");
    id.setBounds(70, 100, 200, 30);
    title = new JTextField("");
    title.setBounds(50, 155, 200, 30);
    isbn = new JTextField("");
    isbn.setBounds(360, 155, 200, 30);
    BooksDelete.add(id);
    BooksDelete.add(title);
    BooksDelete.add(isbn);
    JButton cancel = new JButton("Cancel");
    cancel.setBounds(120, 305, 80, 40);
    JButton delete = new JButton("Delete");
    delete.setBounds(360, 305, 80, 40);
    delete.setBackground(Color.BLUE);
    delete.setForeground(Color.WHITE);


    BooksDelete.add(cancel);
    BooksDelete.add(delete);

    BooksDelete.setLayout(null);
    BooksDelete.setVisible(true);
    delete.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String url = "jdbc:mysql://localhost:3306/librarymanagement";
        String username = "root";
        String password = "";

        try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection connection = DriverManager.getConnection(url, username, password);

          Statement statement = connection.createStatement();
          String query = "DELETE FROM `books` WHERE book_Id=?";
          PreparedStatement preparedStatement = connection.prepareStatement(query);
          preparedStatement.setString(1, id.getText());
          
          int rowsDeleted = preparedStatement.executeUpdate();
          if (rowsDeleted > 0) {
              JOptionPane.showMessageDialog(BooksDelete, "Book deleted successfully.");
          } else {
              JOptionPane.showMessageDialog(BooksDelete, "Error: Unable to update the book.");
          }
          
          preparedStatement.close();
          connection.close();
      } catch (ClassNotFoundException | SQLException ex) {
          ex.printStackTrace();
          // Handle database errors and display an error message
          JOptionPane.showMessageDialog(BooksDelete, "Error: Unable to update the book.");
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

          String query = "SELECT * FROM books WHERE book_Id=?";
          PreparedStatement preparedStatement = connection.prepareStatement(query);
          preparedStatement.setString(1, id.getText());

          ResultSet resultSet = preparedStatement.executeQuery();

          if (resultSet.next()) {
              title.setText(resultSet.getString("title"));
              isbn.setText(resultSet.getString("isbn"));
          } else {
              JOptionPane.showMessageDialog(BooksDelete, "Book not found.");
          }

          resultSet.close();
          preparedStatement.close();
          connection.close();
      } catch (ClassNotFoundException | SQLException ex) {
          ex.printStackTrace();
          JOptionPane.showMessageDialog(BooksDelete, "Error: Unable to search for the book.");
      }
}
    });
    cancel.addActionListener(event->{ 
      BooksDelete.dispose();
  });
  }
}
