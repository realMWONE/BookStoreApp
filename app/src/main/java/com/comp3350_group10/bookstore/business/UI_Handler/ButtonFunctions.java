package com.comp3350_group10.bookstore.business.UI_Handler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.comp3350_group10.bookstore.business.Data_Handler.BookDataHandler;
import com.comp3350_group10.bookstore.business.Data_Handler.IBookDataHandler;
import com.comp3350_group10.bookstore.business.Data_Handler.IUserDataHandler;
import com.comp3350_group10.bookstore.business.Data_Handler.UserDataHandler;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.presentation.BookDetailsActivity;
import com.comp3350_group10.bookstore.presentation.MainActivity;
import com.comp3350_group10.bookstore.presentation.ScreenSize;
import com.comp3350_group10.bookstore.presentation.UserSettingActivity;
import com.comp3350_group10.bookstore.presentation.login.LoginActivity;

import java.text.DecimalFormat;
import java.util.List;

public class ButtonFunctions implements IButtonFunctions
{
    private IBookDataHandler bookHandler;
    private IUserDataHandler userHandler;
    private final int IMAGE_HEIGHT = 120;

    public ButtonFunctions() {
        bookHandler = new BookDataHandler();
        userHandler = new UserDataHandler();
    }

    @Override
    public void SearchButtonPressed(String keyword, TableLayout table, Context context, MainActivity main, String order, String searchBy) throws ClassNotFoundException {
        ClearResults(table);

        if (keyword.equals("")) main.FillTrendingTable();
        else PopulateResults(bookHandler.findBooks(keyword), table, context, main);
    }

    private void ClearResults(TableLayout table) {
        table.removeAllViews();
    }

    private List<IBook> PopulateResults(List<IBook> results, TableLayout table, Context context, MainActivity main) {
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
        image.setImageResource(book.getImage());
        image.setLayoutParams(new TableRow.LayoutParams(height, height));
        return image;
    }

    @Override
    public void LoginButtonPressed()
    {

    }

    @Override
    public void SwitchToLoginActivity(MainActivity main, Context context)
    {
        Intent intent = new Intent(context, LoginActivity.class);
        main.startActivity(intent);
    }

    @Override
    public void LogoutButtonPressed()
    {
        userHandler.logOut();
    }


    @Override
    public void ChangePasswordPressed(String oldPw, String newPw, String confirmNewPw)
    {
        userHandler.changePassword(oldPw, newPw, confirmNewPw);
    }

    @Override
    public void UserSettingButtonPressed(Context context, MainActivity main) {
        Intent intent = new Intent(context, UserSettingActivity.class);
        main.startActivity(intent);
    }

    @Override
    public void IncrementStock(TextView text)
    {
        IBookDetailsFunctions bookDetailsFunctions = new BookDetailsFunctions();

        bookHandler.incrementStock(BookDataHandler.currentBook);
        bookDetailsFunctions.UpdateBookDetails(text);
    }

    @Override
    public void DecrementStock(TextView text)
    {
        IBookDetailsFunctions bookDetailsFunctions = new BookDetailsFunctions();

        bookHandler.decrementStock(BookDataHandler.currentBook);
        bookDetailsFunctions.UpdateBookDetails(text);
    }

    @Override
    public void SetStock()
    {

    }


}
