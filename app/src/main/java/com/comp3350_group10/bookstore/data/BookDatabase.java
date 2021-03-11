/**
 * HSQLDB Book Database
 */
package com.comp3350_group10.bookstore.data;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Scanner;


public class BookDatabase implements IBookDatabase{

	private Connection connection;
	private final String bookTXT = "books.txt";
	private final String bookInfoTXT = "bookInfo.txt";

    private List<String> bookList;

    public static void main(String[] args){

    	/*System.out.println("aa");
    	IBookDatabase bookDB = new BookDatabase();*/
		try{
			//INSERT INTO PERSONS (PERSONID,LASTNAME,FIRSTNAME,ADDRESS,CITY) VALUES (0,"DUY","THAN","Bach Dang","TPHCM")

			//Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			//creates an in-memory database
			Connection connection1  = DriverManager.getConnection("jdbc:hsqldb:file:nwind", "SA", "");
			if(connection1!=null)
				System.out.println("connect successfully");
			//createTables();
			//readInData();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace(System.out);
		}
		catch(SQLException e){
			e.printStackTrace(System.out);
		}
	}
	public BookDatabase(){
		try{
			//INSERT INTO PERSONS (PERSONID,LASTNAME,FIRSTNAME,ADDRESS,CITY) VALUES (0,"DUY","THAN","Bach Dang","TPHCM")

			//Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			//creates an in-memory database
			//connection  = DriverManager.getConnection("jdbc:hsqldb:file:nwind", "SA", "");
			connection  = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");

			if(connection!=null)
				System.out.println("connect successfully");
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

	private void createTables(){
		try{
		//Creating table for books which would have the following attributes.
		String books = "create table books ( "+
			" bookName VARCHAR(200)," +
			"isbn VARCHAR(15),"+
			"quantity integer," +
			"price integer," + 
			"yearPublished integer," +
			"monthPublished VARCHAR(10)," +
			"dayPublished integer," +
			"reserveNumber integer," + 
			"primary key(isbn)" + 
			");";

		String bookInfo = "create table bookInfo ( " + 
			"isbn VARCHAR(15),"+
			"genre VARCHAR(100),"+
			"author VARCHAR(100),"+
			"primary key(isbn, genre, author),"+
			"foreign key (isbn) references books"+
			");";
		

		connection.createStatement().executeUpdate(books);
		connection.createStatement().executeUpdate(bookInfo);
		}
		catch(SQLException e){
			e.printStackTrace(System.out);
		}
	}


	//findByTitle method: Finds the book by its title and inserts the results into a list.
	public List<String> findByTitle(String bookName){

		List<String> bookTitle = new ArrayList<>();

		try{
			PreparedStatement pstmt = connection.prepareStatement(
				"" /**TO-DO*/
			);
			pstmt.setString(1, bookName);

			ResultSet resultSet = pstmt.executeQuery();

			while(resultSet.next()){
				String isbn = resultSet.getString("isbn");
			}

			pstmt.close();
		}
		catch(SQLException e){
			e.printStackTrace(System.out);
		}

		return bookTitle;
	}
	
	//readInData method: Reads data from the .txt files provided and helps populate the data.
	private void readInData(){

		BufferedReader readBooksTXT = null;
		BufferedReader readBookInfoTXT = null;

		try{
			readBooksTXT = new BufferedReader((new FileReader(bookTXT)));
			readBookInfoTXT = new BufferedReader((new FileReader(bookInfoTXT)));
			readBooksTXT.readLine();
			readBookInfoTXT.readLine();

			String bookLine = readBooksTXT.readLine();
			String bookInfoLine = readBookInfoTXT.readLine();

			while(bookLine != null){
				String[] bookParts = bookLine.split(",");
				createBooks(bookParts[0], bookParts[1], bookParts[2], bookParts[3], bookParts[4], bookParts[5], bookParts[6], bookParts[7]);
				bookLine = readBooksTXT.readLine();
			}

			while(bookInfoLine != null){
				String[] bookInfoParts = bookInfoLine.split(",");
				createBookInfo(bookInfoParts[0], bookInfoParts[1], bookInfoParts[2]);
				bookInfoLine = readBookInfoTXT.readLine();
			}
			readBookInfoTXT.close();
			readBooksTXT.close();
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
	private String createBooks(String bookName, String isbn, String quantity, String price, String yearPublished, String monthPublished, String dayPublished, String reserve){
		String isbnValue = "";
		try{
			PreparedStatement pstmt = connection.prepareStatement(
				"Select isbn from books where isbn = ?;"
			);
			pstmt.setString(1, bookName);
			pstmt.setString(2, isbn);
			pstmt.setInt(3, Integer.parseInt(quantity));
			pstmt.setInt(4, Integer.parseInt(price));
			pstmt.setInt(5, Integer.parseInt(yearPublished));
			pstmt.setInt(6, Integer.parseInt(monthPublished));
			pstmt.setInt(7, Integer.parseInt(dayPublished));
			pstmt.setInt(8, Integer.parseInt(reserve));

			ResultSet resultSet = pstmt.executeQuery();

			if(resultSet.next()){
				isbnValue = resultSet.getString("isbn");
				if(isbnValue == resultSet.getString("isbn")){
					isbnValue = "This already exists";
				}
			}
			else{
				PreparedStatement addIsbn = connection.prepareStatement(
					"insert into books (bookName, isbn, quantity, price, yearPublished, monthPublished, dayPublished, reserve) values (?, ?, ?, ?, ?, ?, ?, ?);"
				);
				addIsbn.setString(2, isbn);

				int numUpdated = addIsbn.executeUpdate();  //Just to keep a track how any times we have executed this statement as this SQL statement would not return anything
				
				addIsbn.close();
				resultSet.close();
			}
			pstmt.close();
		}
		catch(SQLException e){
			System.out.println("Error in " + isbn+ " "+ bookName);
			e.printStackTrace(System.out);
		}
		return isbnValue;
	}

	/**
	 * createBookInfo method:
	 * Searches from the input if that specific entry exists in the table or not.
	 * If it does not exist then we insert it into our table.
	 */
	private String createBookInfo(String isbn, String genre, String author){
		String bookInfoString = "";
		try{
			PreparedStatement pstmt = connection.prepareStatement(
				"Select isbn, genre, author from bookInfo where isbn = ? and genre = ? and author = ?;"
			);
			pstmt.setString(1, isbn);
			pstmt.setString(2, genre);
			pstmt.setString(3, author);

			ResultSet resultSet = pstmt.executeQuery();

			if(resultSet.next()){
				if(isbn == resultSet.getString("isbn")){
					if(genre == resultSet.getString("genre")){
						if(author == resultSet.getString("author")){
							bookInfoString  = "This entry already exists";
						}
					}
				}
			}
			else{
				PreparedStatement addBookInfo = connection.prepareStatement(
					"insert into bookInfo (isbn, genre, author) values (?, ?, ?);"
				);
				addBookInfo.setString(1, isbn);
				addBookInfo.setString(2, genre);
				addBookInfo.setString(3, author);

				int numUpdated = addBookInfo.executeUpdate(); //Just to keep a track how any times we have executed this statement as this SQL statement would not return anything
				
				addBookInfo.close();
				resultSet.close();
			}
			pstmt.close();
		}
		catch(SQLException e){
			System.out.println("Error in "+ isbn + " "+ genre+ " "+ author);
			e.printStackTrace(System.out);
		}
		return bookInfoString;
	}

	@Override
	public List<IBook> findBook(String searchTerm) {
		return null;
	}
}


// public class BookDatabase implements IBookDatabase{
//     //List for our book database which would store the list of Book objects
//     private List<Book> bookList;

//     //Constructor
//     public BookDatabase() {
//         //Calling createDatabase method here so every time the BookDatabase object is created it would have all the data loaded into it
//         createDatabase();
//     }

//     /**
//      * createDatabase: Populates the database by adding new book objects with information about the books into the list
//      */
//     private void createDatabase(){
//         bookList = new ArrayList<>();
//         bookList.add(new Book("3214686513501", "Harry Potter and the Philosopher's Stone", "J.K. Rowling", 2630, 2, R.drawable.philosophers_stone));
//         bookList.add(new Book("1100303121502", "Harry Potter and the Chamber of secrets", "J.K. Rowling", 2650, 19, R.drawable.harry_potter_and_the_chamber_of_secrets));
//         bookList.add(new Book("9260783121503", "The Da Vinci Code", "Dan Brown", 3000, 20, R.drawable.the_da_vinci_code));
//         bookList.add(new Book("4458860121504", "Angels and Demons", "Dan Brown", 3000, 5, R.drawable.angels_demons));
//         bookList.add(new Book("1288903121505", "Diary of Wimpy Kid:The Getaway", "Jeff Kinney", 2500, 15, R.drawable.diary_of_wimpy_kid_the_getaway));
//         bookList.add(new Book("6481103121506", "Diary of Wimpy Kid: Double Down", "Jeff Kinney", 2500, 13, R.drawable.diary_of_wimpy_kid_double_down));
//         bookList.add(new Book("6003255121507", "Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 2630, 11, R.drawable.prisoner_of_azkaban));
//         bookList.add(new Book("6783908521508", "Harry Potter and the Goblet of Fire", "J.K. Rowling", 2630, 10, R.drawable.harry_potter_and_the_goblet_fire));
//         bookList.add(new Book("6588503121509", "Harry Potter and the Order of Phoenix", "J.K. Rowling", 2630, 8, R.drawable.harry_potter_and_the_order_of_the_phoenix));
//         bookList.add(new Book("6654684858510", "Harry Potter and the Half-Blood Prince", "J.K. Rowling", 2630, 6, R.drawable.harry_potter_and_the_half_blood_prince));
//         bookList.add(new Book("6874684025221", "Harry Potter and the Deathly Hallows", "J.K. Rowling", 2630, 6, R.drawable.harry_potter_and_the_deathly_hallows));
//         bookList.add(new Book("5354684656848", "Harry Potter and the Cursed Child", "J.K.Rowling",2730, 7, R.drawable.harry_potter_and_the_cursed_child));
//         bookList.add(new Book("2510323255565", "Twilight", "Stephenie Meyer", 2030, 12, R.drawable.twilight));
//         bookList.add(new Book("2551819816185", "Eclipse", "Stephenie Meyer", 2230, 14, R.drawable.eclipse));
//         bookList.add(new Book("2516511685000", "New Moon", "Stephenie Meyer", 2130, 8, R.drawable.new_moon));
//         bookList.add(new Book("2051512546452", "Breaking Dawn", "Stephenie Meyer", 2230, 7, R.drawable.breaking_dawn));
//         bookList.add(new Book("2510018982862", "Midnight Sun", "Stephenie Meyer", 2230, 3, R.drawable.midnightsun));
//         bookList.add(new Book("2500186257772", "The Lord of The Rings, The Fellowship of the Ring", "J. R. R. Tolkien", 2030, 5, R.drawable.lotr));
//         bookList.add(new Book("2500114562233", "The Lord of The Rings, The Two Towers", "J. R. R. Tolkien", 2030, 12, R.drawable.the_two_towers));
//         bookList.add(new Book("2500885265433", "The Book of Lost Tales", "J. R. R. Tolkien", 2030, 13, R.drawable.the_book_of_lost_tails));
//         bookList.add(new Book("2369852102742", "The Children of Húrin", "J. R. R. Tolkien", 2030, 2, R.drawable.the_children_of_hurin));
//     }


//     /**
//      * findBooks: Finds and returns the book objects based on their book ISBN
//      * @param searchTerm
//      * @return
//      */
//     public List<IBook> findBook(String searchTerm) {

//         //Lists which contains book objects related to specific search terms
//         List<IBook> findByISBN = findByISBN(searchTerm);
//         List<IBook> findByAuthor = findByAuthor(searchTerm);
//         List<IBook> findByTitle = findByTitle(searchTerm);

//         //Filtering by removing duplicates and adding them all into a single list which has elements of the search term
//         List<IBook> bookResult = new ArrayList<>();

//         for(IBook book: findByISBN){
//             if(!bookResult.contains(book)){
//                 bookResult.add(book);
//             }
//         }
//         for(IBook book: findByAuthor){
//             if(!bookResult.contains(book)){
//                 bookResult.add(book);
//             }
//         }
//         for(IBook book: findByTitle){
//             if(!bookResult.contains(book)){
//                 bookResult.add(book);
//             }
//         }

//         return bookResult;
//     }

//     /**
//      * findByISBN: Finds books from our bookList by ISBN
//      * @param isbn
//      */
//     private List<IBook> findByISBN(String isbn){
//         List<IBook> bookIsbn = new ArrayList<>();
//         if(isbn != null){
//             //Going through all the bookList
//             for(Book book: bookList){
//                 //If the string inputted matches any of the strings in the our bookList, then add that to our local list
//                 if(book.getBookIsbn().startsWith(isbn)){
//                     bookIsbn.add(book);
//                 }
//             }
//         }
//         return bookIsbn;
//     }

//     /**
//      * findByAuthor: Finds books from our bookList by author
//      * @param author
//      */
//     private List<IBook> findByAuthor(String author){
//         List<IBook> bookAuthor = new ArrayList<>();
//         String[] split;
//         if(author != null){
//             //Going through all the bookList
//             for(Book book: bookList) {
//                 //***CHANGED***:
//                 // now instead of pulling whole string and try to match with the search term,
//                 // we split the term by delimeters and try to match each search term with each data term
//                 //e.g. "J. K. Rowling" now becomes "J", "K", "Rowling" so if we search "Rowling" it now matches
//                 split = book.getBookAuthor().toLowerCase().split("[-. ,:]+");
//                 for(String splitTerm: split){
//                     //If the string inputted matches any of the terms in the book's author name, then add that book to our return list
//                     if (splitTerm.startsWith(author)) {
//                         bookAuthor.add(book);
//                     }
//                 }
//             }
//         }
//         return bookAuthor;
//     }

//     /**
//      * findByTitle: Finds books from our bookList by title
//      * @param title
//      */
//     private List<IBook> findByTitle(String title){
//         List<IBook> bookTitle = new ArrayList<>();
//         String[] split;

//         if(title != null){
//             //Going through all the bookList
//             for(Book book: bookList) {
//                 //***CHANGED***: see findByAuthor for detail
//                 split = book.getBookName().toLowerCase().split("[-. ,:]+");
//                 for(String splitTerm: split){
//                     //If the string inputted matches any of the terms in the book titles, then add the book to our return list
//                     if (splitTerm.startsWith(title)) {
//                         bookTitle.add(book);
//                     }
//                 }
//             }
//         }
//         return bookTitle;
//     }
// }
