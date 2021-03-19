/**
 * HSQLDB Book Database
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// import com.comp3350_group10.bookstore.data.IBookDatabase;

import java.sql.PreparedStatement;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;



 public class BookDatabase{
	private static Connection connection;
	private final String bookTXT = "books.txt";
	//private List<Book> bookList;
	
	public static void main(String[] args){
		BookDatabase bookDatabase = new BookDatabase();
		bookDatabase.findBooks();
	}


	public BookDatabase(){
		try{
			Class.forName("org.hsqldb.jdbcDriver");
			//creates an in-memory database
			connection  = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "");
			createTables();
			readInData();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace(System.out);
		}
		catch(SQLException e){
			e.printStackTrace(System.out);
		}
	}

	//  /**
    //  * findBooks: Finds and returns the book objects based on their book ISBN
    //  * @param searchTerm
    //  * @return
    //  */
    // public List<IBook> findBook(String searchTerm) {

    //     //Lists which contains book objects related to specific search terms
    //     List<IBook> findByISBN = findByISBN(searchTerm);
    //     List<IBook> findByAuthor = findByAuthor(searchTerm);
    //     List<IBook> findByTitle = findByTitle(searchTerm);

    //     //Filtering by removing duplicates and adding them all into a single list which has elements of the search term
    //     List<IBook> bookResult = new ArrayList<>();

    //     for(IBook book: findByISBN){
    //         if(!bookResult.contains(book)){
    //             bookResult.add(book);
    //         }
    //     }
    //     for(IBook book: findByAuthor){
    //         if(!bookResult.contains(book)){
    //             bookResult.add(book);
    //         }
    //     }
    //     for(IBook book: findByTitle){
    //         if(!bookResult.contains(book)){
    //             bookResult.add(book);
    //         }
    //     }

    //     return bookResult;
    // }

    // /**
    //  * findByISBN: Finds books from our bookList by ISBN
    //  * @param isbn
    //  */
    // private List<IBook> findByISBN(String isbn){
    //     List<IBook> bookIsbn = new ArrayList<>();
    //     if(isbn != null){
    //         //Going through all the bookList
    //         for(Book book: bookList){
    //             //If the string inputted matches any of the strings in the our bookList, then add that to our local list
    //             if(book.getBookIsbn().startsWith(isbn)){
    //                 bookIsbn.add(book);
    //             }
    //         }
    //     }
    //     return bookIsbn;
    // }

    // /**
    //  * findByAuthor: Finds books from our bookList by author
    //  * @param author
    //  */
    // private List<IBook> findByAuthor(String author){
    //     List<IBook> bookAuthor = new ArrayList<>();
    //     String[] split;
    //     if(author != null){
    //         //Going through all the bookList
    //         for(Book book: bookList) {
    //             split = book.getBookAuthor().toLowerCase().split("[-. ,:]+");
    //             for(String splitTerm: split){
    //                 //If the string inputted matches any of the terms in the book's author name, then add that book to our return list
    //                 if (splitTerm.startsWith(author)) {
    //                     bookAuthor.add(book);
    //                 }
    //             }
    //         }
    //     }
    //     return bookAuthor;
    // }

    // /**
    //  * findByTitle: Finds books from our bookList by title
    //  * @param title
    //  */
    // private List<IBook> findByTitle(String title){
    //     List<IBook> bookTitle = new ArrayList<>();
    //     String[] split;

    //     if(title != null){
    //         //Going through all the bookList
    //         for(Book book: bookList) {
    //             //***CHANGED***: see findByAuthor for detail
    //             split = book.getBookName().toLowerCase().split("[-. ,:]+");
    //             for(String splitTerm: split){
    //                 //If the string inputted matches any of the terms in the book titles, then add the book to our return list
    //                 if (splitTerm.startsWith(title)) {
    //                     bookTitle.add(book);
    //                 }
    //             }
    //         }
    //     }
    //     return bookTitle;
    // }

	public void findBooks(){
		ResultSet resultSet = null;
		try{
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM books");
			resultSet = pstmt.executeQuery();
			while(resultSet.next()){
				System.out.println(resultSet.getString("bookName"));
			}
		}
		catch(SQLException e){
			e.printStackTrace(System.out);
		}
		
	}

	private void createTables(){
		//Creating table for books which would have the following attributes.
		String books = "create table books ( "+
			"bookName VARCHAR(200)," +
			"isbn VARCHAR(15),"+
			"quantity integer," +
			"price integer," + 
			"date VARCHAR(100)," +
			"author VARCHAR(100),"+
			"genre VARCHAR(100),"+
			"reserve integer," + 
			"image VARCHAR(200),"+
			"primary key(isbn)" + 
			");";
		
		try{
			connection.createStatement().executeUpdate(books);
		}
		catch(SQLException e){
			e.printStackTrace(System.out);
		}
	}
	
	//readInData method: Reads data from the .txt files provided and helps populate the data.
	private void readInData(){

		BufferedReader readBookTXT = null;

		try{
			readBookTXT = new BufferedReader((new FileReader(bookTXT)));
			readBookTXT.readLine();

			String bookLine = readBookTXT.readLine();

			while(bookLine != null){
				String[] bookParts = bookLine.trim().split(",");
				createBooks(bookParts[0].trim(), bookParts[1].trim(), bookParts[2].trim(), bookParts[3].trim(), bookParts[4].trim(), bookParts[5].trim(), bookParts[6].trim(), bookParts[7].trim(), bookParts[8].trim());
				bookLine = readBookTXT.readLine();
			}
			readBookTXT.close();
		}
		catch(IOException e){
			e.printStackTrace(System.out);
		}
	}	




	/**
	 * createBooks method:
	 * Searches from the input if that specific entry exists in the table or not.
	 * If it does not exist then we insert it into our table.
	 */
	private String createBooks(String bookName, String isbn, String quantity, String price, String date, String author, String genre, String reserve, String image){
		String isbnValue = "";
		int result;
		try{
			PreparedStatement addIsbn = connection.prepareStatement(
				"INSERT INTO books (bookName, isbn, quantity, price, date, author, genre, reserve, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);"
			);
			addIsbn.setString(1, bookName);
			addIsbn.setString(2, isbn);
			addIsbn.setInt(3, Integer.parseInt(quantity));
			addIsbn.setInt(4, Integer.parseInt(price));
			addIsbn.setString(5, date);
			addIsbn.setString(6, author);
			addIsbn.setString(7, genre);
			addIsbn.setInt(8, Integer.parseInt(reserve));
			addIsbn.setString(9, image);

			result = addIsbn.executeUpdate(); 

			addIsbn.close();
		}
		catch(SQLException e){
			e.printStackTrace(System.out);
		}
		return isbnValue;
	}
}
