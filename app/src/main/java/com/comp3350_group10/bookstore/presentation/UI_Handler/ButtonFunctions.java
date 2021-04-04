package com.comp3350_group10.bookstore.presentation.UI_Handler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.comp3350_group10.bookstore.business.BookDataHandler;
import com.comp3350_group10.bookstore.business.IBookDataHandler;
import com.comp3350_group10.bookstore.business.IUserDataHandler;
import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.hsqldb.ImageReferences;
import com.comp3350_group10.bookstore.presentation.BookDetailsActivity;
import com.comp3350_group10.bookstore.presentation.CreateUserActivity;
import com.comp3350_group10.bookstore.presentation.MainActivity;
import com.comp3350_group10.bookstore.presentation.ScreenSize;

import com.comp3350_group10.bookstore.presentation.UserSettingActivity;
import com.comp3350_group10.bookstore.presentation.login.LoginActivity;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import static androidx.core.content.ContextCompat.startActivity;

public class ButtonFunctions implements IButtonFunctions
{
    private IBookDataHandler bookHandler;
    private IUserDataHandler userHandler;
    private IBookDetailsFunctions bookDetailsFunctions;
    private final int IMAGE_HEIGHT = 120;

    public ButtonFunctions()
    {
        bookHandler = new BookDataHandler();
        userHandler = new UserDataHandler();
        bookDetailsFunctions = new BookDetailsFunctions();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void SearchButtonPressed(String keyword, TableLayout table, Context context, MainActivity main, String order, String searchBy) throws ClassNotFoundException {
        ClearResults(table);

        if (keyword.equals("")) main.FillTrendingTable();
        else PopulateResults(bookHandler.findBooks(keyword), table, context, main);
    }

    private void ClearResults(TableLayout table) {
        table.removeAllViews();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<IBook> PopulateResults(List<IBook> results, TableLayout table, Context context, MainActivity main) {
        SortResults(results, main);

        if (results != null) {
            for (IBook book : results) {
                TableRow row = CreateTableRow(context);

                row.addView(CreateImageView(context, book, ScreenSize.getPixelsFromDP(context, IMAGE_HEIGHT)));
                row.addView(CreateTextView(context, book));
                row.setOnClickListener(v -> OpenBookDetailsActivity(context, book, main));

                table.addView(row);
            }
        }

        return results;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void SortResults(List<IBook> results, MainActivity main) {
        String sortBy = main.getSortBy();
        if (sortBy.contains("Title"))
            results.sort(Comparator.comparing(IBook::getBookName));
        else if (sortBy.contains("Author"))
            results.sort(Comparator.comparing(IBook::getBookAuthor));
        else results.sort(Comparator.comparing(IBook::getGenre));

        if (main.getOrderString().toLowerCase().equals("desc"))
            Collections.reverse(results);
    }

    private void OpenBookDetailsActivity(Context context, IBook book, MainActivity main) {
        Intent intent = new Intent(context, BookDetailsActivity.class);
        BookDataHandler.currentBook = book;
        main.startActivity(intent);
    }

    private TableRow CreateTableRow(Context context) {
        TableRow row = new TableRow(context);
        row.setPadding(10,10,10,10);
        return row;
    }

    @SuppressLint("SetTextI18n")
    private TextView CreateTextView(Context context, IBook book) {
        TextView text = new TextView(context);

        float price = book.getPrice() / 100f;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        text.setText(book.getBookName() + "\n" + book.getBookAuthor() + "\n" + book.getBookIsbn() + "\n\n$" + df.format(price));

        return text;
    }

    private ImageView CreateImageView(Context context, IBook book, int height) {
        ImageView image = new ImageView(context);
        image.setImageResource(ImageReferences.Get(book.getImage()));
        image.setLayoutParams(new TableRow.LayoutParams(height, height));
        return image;
    }

    @Override
    public void LoginButtonPressed(String email, String password) throws ClassNotFoundException {
        userHandler.logIn(email, password);
    }



    @Override
    public void LogoutButtonPressed()
    {
        //TODO: maybe add popup notification
        userHandler.logOut();
    }


    @Override
    public void ChangePasswordPressed(String oldPw, String newPw, String confirmNewPw)
    {
        //TODO: maybe add popup confirmation
        userHandler.changePassword(oldPw, newPw, confirmNewPw);
    }



    @Override
    public void IncrementStock(TextView text)
    {
        bookHandler.incrementStock(BookDataHandler.currentBook);
        bookDetailsFunctions.UpdateBookDetails(text);
    }

    @Override
    public void DecrementStock(TextView text)
    {
        bookHandler.decrementStock(BookDataHandler.currentBook);
        bookDetailsFunctions.UpdateBookDetails(text);
    }

    @Override
    public void SetStock(int newStock) throws ClassNotFoundException {
        bookHandler.setStock(BookDataHandler.currentBook, newStock);
    }

    @Override
    public void SetPrice(int newPrice) {
        bookHandler.setPrice(BookDataHandler.currentBook, newPrice);
    }



    @Override
    //TODO: popup saying user creation successful or failed
    public IUser CreateUserButtonPressed(String name, String email, String password, boolean isManager) throws ClassNotFoundException {
        IUser createdUser = userHandler.createNewUser(name, email, password, isManager);
        if(createdUser!=null) {
            //popup saying successful
            //prompt return homescreen or create another user
        } else
        {
            //popup saying unsuccessful
        }
        return createdUser;
    }
}
