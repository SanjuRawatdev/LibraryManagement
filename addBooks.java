import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class addBooks {
  addBooks() {
    JFrame BooksAdd = new JFrame("Add Books");
    BooksAdd.setBounds(300, 200, 600, 550);
    JLabel bookTitle,bookAuthor, bookIsbn,bookAvailability,l;
    l  = new JLabel("Library Management");
    l.setBounds(230, 30, 200, 30);
    l.setFont(new Font("Releway",Font.BOLD,20));

    bookTitle = new JLabel("Title : ");
    bookTitle.setBounds(5, 95, 150, 30);
    bookAuthor = new JLabel("Author : ");
    bookAuthor.setBounds(306, 95, 150, 30);
    bookIsbn = new JLabel("ISBN : ");
    bookIsbn.setBounds(5, 205, 150, 30);
    bookAvailability = new JLabel("Availability : ");
    bookAvailability.setBounds(306, 205, 150, 30);
    BooksAdd.add(l);
    BooksAdd.add(bookTitle);
    BooksAdd.add(bookAuthor);
    BooksAdd.add(bookIsbn);
    BooksAdd.add(bookAvailability);

    JTextField title, author, isbn, availability;
    title = new JTextField("");
    title.setBounds(50, 100, 200, 30);
    author = new JTextField("");
    author.setBounds(360, 100, 200, 30);
    isbn = new JTextField("");
    isbn.setBounds(50, 205, 200, 30);
    availability = new JTextField("");
    availability.setBounds(380, 205, 200, 30);

    BooksAdd.add(title);
    BooksAdd.add(author);
    BooksAdd.add(isbn);
    BooksAdd.add(availability);
    JButton cancel = new JButton("Cancel");
    cancel.setBounds(120, 305, 80, 40);
    JButton save = new JButton("Save");
    save.setBounds(360, 305, 80, 40);
    save.setBackground(Color.BLUE);
    save.setForeground(Color.WHITE);


    BooksAdd.add(cancel);
    BooksAdd.add(save);

    BooksAdd.setLayout(null);
    BooksAdd.setVisible(true);

    save.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String url = "jdbc:mysql://localhost:3306/librarymanagement";
        String username = "root";
        String password = "";

        try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection connection = DriverManager.getConnection(url, username, password);

          Statement statement = connection.createStatement();
          String query1 = "insert into `librarymanagement`.`books`"
              + "(`title` , `author` , `isbn` , `availability`)"
              + "values('" + title.getText() + "','" + author.getText() + "','"
              + isbn.getText() + "','" + availability.getText() + "')";

          int i = statement.executeUpdate(query1);
          if (i > 0) {
            System.out.println("ROW INSERTED");
            JOptionPane.showMessageDialog(BooksAdd, "Books Added Successfully......");
            BooksAdd.dispose();
          } else {
            System.out.println("ROW NOT INSERTED");
          }
          connection.close();
        } catch (ClassNotFoundException c) {
          System.out.println(c);
        } catch (SQLException SQLException) {
          System.out.println(SQLException.getMessage());
        }
      }
    });
    cancel.addActionListener(event->{ 
      BooksAdd.dispose();
  });
  }
}

