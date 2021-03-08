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
				makeBooks(bookParts[0], bookParts[1], bookParts[2], bookParts[3], bookParts[4], bookParts[5], bookParts[6], bookParts[7]);
				bookLine = readBooksTXT.readLine();
			}

			while(bookInfoLine != null){
				String[] bookInfoParts = bookInfoLine.split(",");
				makeBookInfo(bookInfoParts[0], bookInfoParts[1], bookInfoParts[2]);
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


}

