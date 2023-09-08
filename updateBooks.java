import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class updateBooks {
  updateBooks() {
    JFrame BooksUpdate = new JFrame("Update Books");
    BooksUpdate.setBounds(300, 200, 600, 550);
    JLabel bookId, bookTitle,bookAuthor, bookIsbn,bookAvailability,l;

    l  = new JLabel("Library Management");
    l.setBounds(230, 30, 200, 30);
    l.setFont(new Font("Releway",Font.BOLD,20));
    bookId = new JLabel("Book Id : ");
    bookId.setBounds(5, 95, 150, 30);
    JButton search = new JButton("Search");
    search.setBounds(360, 95, 80, 40);
    bookTitle = new JLabel("Title : ");
    bookTitle.setBounds(5, 150, 150, 30);
    bookAuthor = new JLabel("Author : ");
    bookAuthor.setBounds(306, 150, 150, 30);
    bookIsbn = new JLabel("ISBN : ");
    bookIsbn.setBounds(5, 200, 150, 30);
    bookAvailability = new JLabel("Availability : ");
    bookAvailability.setBounds(306, 200, 150, 30);
    BooksUpdate.add(l);
    BooksUpdate.add(bookId);
    BooksUpdate.add(search);
    BooksUpdate.add(bookTitle);
    BooksUpdate.add(bookAuthor);
    BooksUpdate.add(bookIsbn);
    BooksUpdate.add(bookAvailability);

    JTextField id,title, author, isbn, availability;
    id = new JTextField("");
    id.setBounds(70, 100, 200, 30);
    title = new JTextField("");
    title.setBounds(50, 155, 200, 30);
    author = new JTextField("");
    author.setBounds(360, 155, 200, 30);
    isbn = new JTextField("");
    isbn.setBounds(50, 205, 200, 30);
    availability = new JTextField("");
    availability.setBounds(380, 205, 200, 30);
    BooksUpdate.add(id);
    BooksUpdate.add(title);
    BooksUpdate.add(author);
    BooksUpdate.add(isbn);
    BooksUpdate.add(availability);
    JButton cancel = new JButton("Cancel");
    cancel.setBounds(120, 305, 80, 40);
    JButton update = new JButton("Update");
    update.setBounds(360, 305, 80, 40);
    update.setBackground(Color.BLUE);
    update.setForeground(Color.WHITE);


    BooksUpdate.add(cancel);
    BooksUpdate.add(update);

    BooksUpdate.setLayout(null);
    BooksUpdate.setVisible(true);
    update.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String url = "jdbc:mysql://localhost:3306/librarymanagement";
        String username = "root";
        String password = "";

        try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection connection = DriverManager.getConnection(url, username, password);

          Statement statement = connection.createStatement();
          String query = "UPDATE books SET title=?, author=?, isbn=?, availability=? WHERE book_Id=?";
          PreparedStatement preparedStatement = connection.prepareStatement(query);
          preparedStatement.setString(1, title.getText());
          preparedStatement.setString(2, author.getText());
          preparedStatement.setString(3, isbn.getText());
          preparedStatement.setString(4, availability.getText());
          preparedStatement.setString(5, id.getText());
          
          int rowsUpdated = preparedStatement.executeUpdate();
          if (rowsUpdated > 0) {
              JOptionPane.showMessageDialog(BooksUpdate, "Book updated successfully.");
          } else {
              JOptionPane.showMessageDialog(BooksUpdate, "Error: Unable to update the book.");
          }
          
          preparedStatement.close();
          connection.close();
      } catch (ClassNotFoundException | SQLException ex) {
          ex.printStackTrace();
          // Handle database errors and display an error message
          JOptionPane.showMessageDialog(BooksUpdate, "Error: Unable to update the book.");
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
              author.setText(resultSet.getString("author"));
              isbn.setText(resultSet.getString("isbn"));
              availability.setText(resultSet.getString("availability"));
          } else {
              JOptionPane.showMessageDialog(BooksUpdate, "Book not found.");
          }

          resultSet.close();
          preparedStatement.close();
          connection.close();
      } catch (ClassNotFoundException | SQLException ex) {
          ex.printStackTrace();
          JOptionPane.showMessageDialog(BooksUpdate, "Error: Unable to search for the book.");
      }
}
    });
    cancel.addActionListener(event->{ 
      BooksUpdate.dispose();
  });
  }
}

