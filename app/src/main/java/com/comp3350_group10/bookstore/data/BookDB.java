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

public class BookDB{

	static Connection connection;
	BookDatabase db = new BookDatabase();
	
}

class BookDatabase{
	private Connection connection;
	private final String bookTXT = "books.txt";
	private final String bookInfoTXT = "bookInfo.txt";

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

	private void createTables(){
		try{
		//Creating table for books which would have the following attributes.
		String books = "create table books ( "+
			" bookName VARCHAR(200)," +
			"isbn VARCHAR(15)"+
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
	public void findByTitle(String bookName){

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
	}
	
	//readInData method: Reads data from the .txt files provided and helps populate the data.
	private void readInData(){

		BufferedReader readBooksTXT = null;
		BufferedReader readBookInfoTXT = null;

		try{
			readBooksTXT = new BufferedReader((new FileReader(booksTXT)));
			readBookInfo = new BufferedReader((new FileReader(bookInfoTXT)));
			readBooksTXT.readLine();
			readBookInfo.readLine();

			String bookLine = readBooksTXT.readLine();
			String bookInfoLine = readBookInfo.readLine();

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
			in.close();
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
}

