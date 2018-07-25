package com.example.andra.inventory;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.andra.inventory.data.BooksContract;

import java.io.PrintWriter;
import java.io.StringWriter;

public class DetailActivity extends AppCompatActivity{

    private Uri mCurrentBookUri;
    private Context mContext;

    final long id = cursor.getLong(idColumnIndex);
    TextView quantityTextView = (TextView) findViewById(R.id.quantity);
    Button mIncrementButton = (Button) findViewById(R.id.increment_book);
    Button mDecrementButton = (Button) findViewById(R.id.decrement_book);
    TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary);
   int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inv_management);

        mIncrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity += quantity;
                ContentValues values = new ContentValues();
                try {
                    String bookQuantity = quantityTextView.getText().toString().trim();

                    if (Integer.valueOf(bookQuantity) == 0) {
                        throw new IllegalArgumentException("No quantity available");
                    }

                    mCurrentBookUri = ContentUris.withAppendedId(BooksContract.BookEntry.CONTENT_URI, id);
                    values.put(BooksContract.BookEntry.COLUMN_QUANTITY, String.valueOf(Integer.valueOf(bookQuantity) - 1));
                    int rowsAffected = mContext.getContentResolver().update(mCurrentBookUri, values, null, null);
                } catch (Exception ex) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    ex.printStackTrace(pw);
                    String sStackTrace = sw.toString();
                    System.out.println(sStackTrace);
                }
            }
        });

        mDecrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity>0) {
                    quantity += quantity;
                } else {
                    throw new IllegalArgumentException("All items removed");   // de verif cum se face toast
                }
                ContentValues values = new ContentValues();
                try {
                    String bookQuantity = quantityTextView.getText().toString().trim();

                    if (Integer.valueOf(bookQuantity) == 0) {
                        throw new IllegalArgumentException("No quantity available");
                    }

                    mCurrentBookUri = ContentUris.withAppendedId(BooksContract.BookEntry.CONTENT_URI, id);
                    values.put(BooksContract.BookEntry.COLUMN_QUANTITY, String.valueOf(Integer.valueOf(bookQuantity) + 1));
                    int rowsAffected = mContext.getContentResolver().update(mCurrentBookUri, values, null, null);
                } catch (Exception ex) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    ex.printStackTrace(pw);
                    String sStackTrace = sw.toString();
                    System.out.println(sStackTrace);
                }
            }
        });

        public String setOrderSummaryTextView {

        }
    }
}
