import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;
import java.awt.event.*;

public class Librarymanagement {
  public static void main(String[] args) {
    JFrame fram = new JFrame("Library Management");
    fram.setVisible(true);
    fram.setBounds(100, 100, 1000, 600);
    fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fram.setBackground(ColorUIResource.GREEN);

    JMenuBar menu = new JMenuBar();
    menu.setLocation(100, 20);

    JMenu books = new JMenu("Books");
    books.setBackground(Color.black);
    JMenuItem addBook = new JMenuItem("Add");
    JMenuItem updateBook = new JMenuItem("Update");
    JMenuItem deleteBook = new JMenuItem("Delete");
    JMenuItem viewBook = new JMenuItem("View");
    addBook.setBackground(Color.lightGray);
    updateBook.setBackground(Color.lightGray);
    deleteBook.setBackground(Color.lightGray);
    viewBook.setBackground(Color.lightGray);
    books.add(addBook);
    books.add(updateBook);
    books.add(deleteBook);
    books.add(viewBook);
    JMenu readers = new JMenu("Readers");
    readers.setBackground(Color.black);
    JMenuItem addReader = new JMenuItem("Add");
    JMenuItem updateReader = new JMenuItem("Update");
    JMenuItem deleteReader = new JMenuItem("Delete");
    JMenuItem viewReader = new JMenuItem("View");
    readers.add(addReader);
    readers.add(updateReader);
    readers.add(deleteReader);
    readers.add(viewReader);
    addReader.setBackground(Color.lightGray);
    updateReader.setBackground(Color.lightGray);
    deleteReader.setBackground(Color.lightGray);
    viewReader.setBackground(Color.lightGray);
    JMenu transactions = new JMenu("Transactions");
    transactions.setBackground(Color.black);
    JMenuItem checkout = new JMenuItem("Book Checkout");
    JMenuItem returns = new JMenuItem("Book Returns");
    JMenuItem renewals = new JMenuItem("Renewals");
    transactions.add(checkout);
    transactions.add(returns);
    transactions.add(renewals);
    checkout.setBackground(Color.lightGray);
    returns.setBackground(Color.lightGray);
    renewals.setBackground(Color.lightGray);

    menu.add(books);
    menu.add(readers);
    menu.add(transactions);
    fram.setJMenuBar(menu);

    addBook.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new addBooks();
      }
    });  
    updateBook.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new updateBooks();
      }
    });
    deleteBook.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new deleteBooks();
      }
    });
viewBook.addActionListener(new ActionListener() {
  @Override
  public void actionPerformed(ActionEvent e) {
         new viewAllBooks();

  }
});
    addReader.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new addReaders();
      }
    }); 
    updateReader.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new updateReaders();
      }
    });  
     deleteReader.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new deleteReaders();
      }
    });  
viewReader.addActionListener(new ActionListener() {
  @Override
  public void actionPerformed(ActionEvent e) {
        new viewAllReader();
  }
});
     checkout.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new checkoutBooks();
      }
    });  
    returns.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new returnBooks();
      }
    }); 
    renewals.addActionListener(new ActionListener() {
  @Override
  public void actionPerformed(ActionEvent e) {
    new viewAllTransactions();
  }
}); 
}
}




