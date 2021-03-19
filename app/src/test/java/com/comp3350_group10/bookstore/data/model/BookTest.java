//package com.comp3350_group10.bookstore.data.model;
//
//import com.comp3350_group10.bookstore.R;
//import com.comp3350_group10.bookstore.objects.Book;
//import com.comp3350_group10.bookstore.persistence.IBook;
//
//import junit.framework.TestCase;
//
//public class BookTest extends TestCase {
//
//    public void setUp() throws Exception {
//        super.setUp();
//    }
//
//    public void tearDown() throws Exception {
//
//    }
//
//    public static void testGetBookName() {
//        //String isbn[13], String bookName, String bookAuthor, int price, int stockAmount
//        //In this test case , I default some fields:
//        //isbn = 1111111111111 ; bookAuthor = Dan, price = stock = 15
//        String []bookNames = new String[]{"Doraemon","Tom And Jerry","Harry Potter"};
//        for(int i=0;i<bookNames.length;i++){
//            IBook b = new Book("1111111111111",bookNames[i],"Dan",15,15, R.drawable.lotr);
//            assertEquals(b.getBookName(),bookNames[i]);
//        }
//    }
//
//    public static void testGetBookAuthor() {
//        //String isbn[13], String bookName, String bookAuthor, int price, int stockAmount
//        //In this test case , I default some fields:
//        //isbn = 1111111111111 ; bookName = Doraemon, price = stock = 15
//        String []bookAuthors = new String[]{"Dan","Kevin","Animesh"};
//        for(int i=0;i<bookAuthors.length;i++){
//            Book b = new Book("1111111111111","Doraemon",bookAuthors[i],15,15, R.drawable.lotr);
//            assertEquals(b.getBookAuthor(),bookAuthors[i]);
//        }
//    }
//
//    public static void testGetPrice() {
//        //String isbn[13], String bookName, String bookAuthor, int price, int stockAmount
//        //In this test case , I default some fields:
//        //isbn = 1111111111111 ; bookName = Doraemon, bookAuthor = Dan, stock = 15
//        int []bookPrices = new int[]{10,20,30};
//        for(int i=0;i<bookPrices.length;i++){
//            Book b = new Book("1111111111111","Doraemon","Dan",bookPrices[i],15, R.drawable.lotr);
//            assertEquals(b.getPrice(),bookPrices[i]);
//        }
//    }
//
//    public static void testGetBookIsbn() {
//        //String isbn[13], String bookName, String bookAuthor, int price, int stockAmount
//        //In this test case , I default some fields:
//        //bookName = Doraemon, bookAuthor = Dan, price = stock = 15
//        String []bookIsbn = new String[]{"1111111111111","2222222222222","3333333333333"};
//        for(int i=0;i<bookIsbn.length;i++){
//            Book b = new Book(bookIsbn[i],"Doraemon","Dan",15,15, R.drawable.lotr);
//            assertEquals(b.getBookIsbn(),bookIsbn[i]);
//        }
//    }
//
//    public static void testGetStockAmount() {
//        //String isbn[13], String bookName, String bookAuthor, int price, int stockAmount
//        //In this test case , I default some fields:
//        //isbn = 1111111111111, bookName = Doraemon, bookAuthor = Dan, price  = 15
//        int []bookStock = new int[]{1,2,3};
//        for(int i=0;i<bookStock.length;i++){
//            Book b = new Book("1111111111111","Doraemon","Dan",15,bookStock[i], R.drawable.lotr);
//            assertEquals(b.getStock(),bookStock[i]);
//        }
//    }
//
//    public static void testSetPrice() {
//        //defaults all fields except the price
//        Book b = new Book("1111111111111","Doraemon","Dan",15,20, R.drawable.lotr);
//        b.setPrice(30);
//        assertEquals(b.getPrice(),30);
//
//        Book b1 = new Book("1111111111111","Doraemon","Dan",50,20, R.drawable.lotr);
//        b1.setPrice(100);
//        assertEquals(b1.getPrice(),100);
//
//        Book b2 = new Book("1111111111111","Doraemon","Dan",0,20, R.drawable.lotr);
//        b2.setPrice(200);
//        assertEquals(b2.getPrice(),200);
//    }
//
//    public static void testSetStockAmount() {
//        //defaults all fields except the stock
//        Book b = new Book("1111111111111","Doraemon","Dan",15,20, R.drawable.lotr);
//        b.setStock(30);
//        assertEquals(b.getStock(),30);
//
//        Book b1 = new Book("1111111111111","Doraemon","Dan",15,40, R.drawable.lotr);
//        b1.setStock(80);
//        assertEquals(b1.getStock(),80);
//
//        Book b2 = new Book("1111111111111","Doraemon","Dan",15,1, R.drawable.lotr);
//        b2.setStock(200);
//        assertEquals(b2.getStock(),200);
//    }
//}