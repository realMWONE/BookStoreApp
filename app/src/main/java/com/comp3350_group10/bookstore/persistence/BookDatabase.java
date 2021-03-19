 /**
 * HSQLDB Book Database
 */

 package com.comp3350_group10.bookstore.persistence;
import com.comp3350_group10.bookstore.objects.Book;
import com.comp3350_group10.bookstore.persistence.hsqldb.PersistenceException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;


public class BookDatabase{
	private static Connection connection;
	private final String bookTXT = "books.txt";

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

	/**
	 * findBooks: Finds and returns the book objects based on their book ISBN
	 * @param searchTerm
	 * @return
	 */
	public List<IBook> findBook(String searchTerm) {

		//Lists which contains book objects related to specific search terms
		List<IBook> findByISBN = findByISBN(searchTerm);
		List<IBook> findByAuthor = findByAuthor(searchTerm);
		List<IBook> findByTitle = findByTitle(searchTerm);

		//Filtering by removing duplicates and adding them all into a single list which has elements of the search term
		List<IBook> bookResult = new ArrayList<>();

		for(IBook book: findByISBN){
			if(!bookResult.contains(book)){
				bookResult.add(book);
			}
		}
		for(IBook book: findByAuthor){
			if(!bookResult.contains(book)){
				bookResult.add(book);
			}
		}
		for(IBook book: findByTitle){
			if(!bookResult.contains(book)){
				bookResult.add(book);
			}
		}

		return bookResult;
	}

	/**
	 * findByISBN: Finds books from our bookList by ISBN
	 * @param isbn
	 */
	private List<IBook> findByISBN(String isbn){
		List<IBook> bookIsbn = getBooks();
		List<IBook> books = new ArrayList<>();
		if(isbn != null){
			//Going through all the bookList
			for(IBook book: bookIsbn){
				//If the string inputted matches any of the strings in the our bookList, then add that to our local list
				if(book.getBookIsbn().startsWith(isbn)){
					books.add(book);
				}
			}
		}
		return books;
	}

	/**
	 * findByAuthor: Finds books from our bookList by author
	 * @param author
	 */
	private List<IBook> findByAuthor(String author) {
		List<IBook> bookAuthor = getBooks();
		List<IBook> books = new ArrayList<>();
		String[] split;
		if(author != null){
			//Going through all the bookList
			for(IBook book: bookAuthor) {
				split = book.getBookAuthor().toLowerCase().split("[-. ,:]+");
				for(String splitTerm: split){
					//If the string inputted matches any of the terms in the book's author name, then add that book to our return list
					if (splitTerm.startsWith(author)) {
						books.add(book);
					}
				}
			}
		}
		return books;
	}

	/**
	 * findByTitle: Finds books from our bookList by title
	 * @param title
	 */
	private List<IBook> findByTitle(String title) {
		List<IBook> bookTitle = getBooks();
		List<IBook> books = new ArrayList<>();
		String[] split;

		if(title != null){
			//Going through all the bookList
			for(IBook book: bookTitle) {
				split = book.getBookName().toLowerCase().split("[-. ,:]+");
				for(String splitTerm: split){
					//If the string inputted matches any of the terms in the book titles, then add the book to our return list
					if (splitTerm.startsWith(title)) {
						books.add(book);
					}
				}
			}
		}
		return books;
	}

	private Book createBook(final ResultSet rs) throws SQLException{
		final String bookName = rs.getString("bookName");
		final String isbn = rs.getString("isbn");
		final int quantity = rs.getInt("quantity");
		final int price = rs.getInt("price");
		final String date = rs.getString("date");
		final String author = rs.getString("author");
		final String genre = rs.getString("genre");
		final int reserve = rs.getInt("reserve");
		final String imageReference = rs.getString("image");

		return new Book(bookName, isbn, quantity, price, date, author, genre, reserve, imageReference);
	}


	public List<IBook> getBooks(){
		ResultSet resultSet = null;
		List<IBook> books = new ArrayList<>();
		try{
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM books");
			resultSet = pstmt.executeQuery();
			while(resultSet.next()){
				final IBook book = createBook(resultSet);
				books.add(book);
			}
		}
		catch(SQLException e){
			e.printStackTrace(System.out);
		}
		return books;
	}


	public IBook insertBook(IBook book) {
		try {
			final PreparedStatement pstmt = connection.prepareStatement("INSERT INTO BOOKS VALUES(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getBookIsbn());
			pstmt.setInt(3, book.getStock());
			pstmt.setInt(4, book.getPrice());
			pstmt.setString(5, book.getDate());
			pstmt.setString(6, book.getBookAuthor());
			pstmt.setString(7, book.getGenre());
			pstmt.setInt(8, book.getReserve());
			pstmt.setString(9, book.getImage());
			pstmt.executeUpdate();

			return book;
		}
		catch(final SQLException e){
			throw new PersistenceException(e);
		}
	}



	public void updateBook(IBook book) {
		try{
			final PreparedStatement pstmt = connection.prepareStatement("UPDATE BOOKS SET quantity=?,price=?, reserve=? WHERE isbn = ?");
			pstmt.setInt(1, book.getStock());
			pstmt.setInt(2, book.getPrice());
			pstmt.setInt(3, book.getReserve());
			pstmt.setString(4, book.getBookIsbn());
			pstmt.executeUpdate();
		}
		catch(final SQLException e){
			throw new PersistenceException(e);
		}
	}

	public void deleteBook(IBook book) {
		try{
			final PreparedStatement pstmt = connection.prepareStatement("DELETE FROM BOOKS WHERE isbn=?");
			pstmt.setString(1, book.getBookIsbn());
			pstmt.executeUpdate();
		}
		catch(final SQLException e){
			throw new PersistenceException(e);
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
				fillBooks(bookParts[0].trim(), bookParts[1].trim(), bookParts[2].trim(), bookParts[3].trim(), bookParts[4].trim(), bookParts[5].trim(), bookParts[6].trim(), bookParts[7].trim(), bookParts[8].trim());
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
	private void fillBooks(String bookName, String isbn, String quantity, String price, String date, String author, String genre, String reserve, String image){
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
		catch(SQLException e) {
			e.printStackTrace(System.out);
		}
	}
}
