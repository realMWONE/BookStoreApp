package com.comp3350_group10.bookstore.objects;

import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.persistence.IBook;

import junit.framework.TestCase;

public class BookTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {

    }

    public void testGetBookName() throws Exception {
        setUp();
        //In this test case , I default some fields:
        //isbn = 1111111111111 ; bookAuthor = Dan, price = stock = 15
        String[] bookNames = new String[]{"Doraemon", "Tom And Jerry", "Harry Potter"};
        for (String bookName : bookNames) {
            IBook b = new Book(bookName, "1111111111111", 15, 15, "19 March 2021", "Dan", "Documentary", 0, R.drawable.lotr);
            assertEquals(b.getBookName(), bookName);
        }
    }

    public void testGetBookAuthor() {
        //In this test case , I default some fields:
        //isbn = 1111111111111 ; bookName = Doraemon, price = stock = 15
        String[] bookAuthors = new String[]{"Dan", "Kevin", "Animesh"};
        for (String author : bookAuthors) {
            IBook b = new Book("This Is A Book Name", "1111111111111", 15, 15, "19 March 2021", author, "Documentary", 0, R.drawable.lotr);
            assertEquals(b.getBookAuthor(), author);
        }
    }

    public void testGetPrice() {
        //String isbn[13], String bookName, String bookAuthor, int price, int stockAmount
        //In this test case , I default some fields:
        //isbn = 1111111111111 ; bookName = Doraemon, bookAuthor = Dan, stock = 15
        int[] bookPrices = new int[]{10, 20, 30};
        for (int price : bookPrices) {
            IBook b = new Book("This Is A Book Name", "1111111111111", 15, price, "19 March 2021", "Dan", "Documentary", 0, R.drawable.lotr);
            assertEquals(b.getPrice(), price);
        }
    }

    public void testGetBookIsbn() {
        //String isbn[13], String bookName, String bookAuthor, int price, int stockAmount
        //In this test case , I default some fields:
        //bookName = Doraemon, bookAuthor = Dan, price = stock = 15
        String[] bookIsbn = new String[]{"1111111111111", "2222222222222", "3333333333333"};
        for (String isbn : bookIsbn) {
            IBook b = new Book("This Is A Book Name", isbn, 15, 15, "19 March 2021", "Dan", "Documentary", 0, R.drawable.lotr);
            assertEquals(b.getBookIsbn(), isbn);
        }
    }

    public void testGetStockAmount() {
        //String isbn[13], String bookName, String bookAuthor, int price, int stockAmount
        //In this test case , I default some fields:
        //isbn = 1111111111111, bookName = Doraemon, bookAuthor = Dan, price  = 15
        int[] bookStock = new int[]{1, 2, 3};
        for (int stock : bookStock) {
            IBook b = new Book("This Is A Book Name", "1111111111111", stock, 15, "19 March 2021", "Dan", "Documentary", 0, R.drawable.lotr);
            assertEquals(b.getStock(), stock);
        }
    }

    public void testSetPrice() {
        //defaults all fields except the price
        IBook b = new Book("This Is A Book Name", "1111111111111", 15, 15, "19 March 2021", "Dan", "Documentary", 0, R.drawable.lotr);
        b.setPrice(30);
        assertEquals(b.getPrice(), 30);

        IBook b1 = new Book("This Is A Book Name", "1111111111111", 15, 15, "19 March 2021", "Dan", "Documentary", 0, R.drawable.lotr);
        b1.setPrice(100);
        assertEquals(b1.getPrice(), 100);

        IBook b2 = new Book("This Is A Book Name", "1111111111111", 15, 15, "19 March 2021", "Dan", "Documentary", 0, R.drawable.lotr);
        b2.setPrice(200);
        assertEquals(b2.getPrice(), 200);
    }

    public void testSetStockAmount() {
        //defaults all fields except the stock
        IBook b = new Book("This Is A Book Name", "1111111111111", 15, 15, "19 March 2021", "Dan", "Documentary", 0, R.drawable.lotr);
        b.setStock(30);
        assertEquals(b.getStock(), 30);

        IBook b1 = new Book("This Is A Book Name", "1111111111111", 15, 15, "19 March 2021", "Dan", "Documentary", 0, R.drawable.lotr);
        b1.setStock(80);
        assertEquals(b1.getStock(), 80);

        IBook b2 = new Book("This Is A Book Name", "1111111111111", 15, 15, "19 March 2021", "Dan", "Documentary", 0, R.drawable.lotr);
        b2.setStock(200);
        assertEquals(b2.getStock(), 200);
    }

    public void testGetDate() {
        IBook b = new Book("This Is A Book Name", "1111111111111", 15, 15, "19 March 2021", "Dan", "Documentary", 0, R.drawable.lotr);
        assertEquals(b.getDate(), "19 March 2021");
    }

    public void testGetReserve() {
        IBook b = new Book("This Is A Book Name", "1111111111111", 15, 15, "19 March 2021", "Dan", "Documentary", 0, R.drawable.lotr);
        assertEquals(b.getReserve(), 0);
    }

    public void testGetGenre() {
        IBook b = new Book("This Is A Book Name", "1111111111111", 15, 15, "19 March 2021", "Dan", "Documentary", 0, R.drawable.lotr);
        assertEquals(b.getGenre(), "Documentary");
    }

    public void testGetImage() {
        IBook b = new Book("This Is A Book Name", "1111111111111", 15, 15, "19 March 2021", "Dan", "Documentary", 0, R.drawable.lotr);
        assertEquals(b.getImage(), R.drawable.lotr);
    }

    public void testSetReserve() {
        IBook b = new Book("This Is A Book Name", "1111111111111", 15, 15, "19 March 2021", "Dan", "Documentary", 0, R.drawable.lotr);
        b.setReserve(100);
        assertEquals(b.getReserve(), 100);
    }
}