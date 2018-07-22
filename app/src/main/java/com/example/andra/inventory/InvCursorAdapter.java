package com.example.andra.inventory;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.andra.inventory.data.BooksContract.BookEntry;

import java.io.PrintWriter;
import java.io.StringWriter;

public class InvCursorAdapter extends CursorAdapter {

    private Uri mCurrentBookUri;
    private EditText mQuantityEditText;
    private Context mContext;

    public InvCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
        mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }


    @Override
    public void bindView(View view, Context context, final Cursor cursor) {
        /*TextView idTextView = (TextView) view.findViewById(R.id.id);*/
        final TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        final TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        TextView supplierTextView = (TextView) view.findViewById(R.id.supplier);
        TextView phoneTextView = (TextView) view.findViewById(R.id.phone);
        Button mBuyButton = (Button) view.findViewById(R.id.buy_book);

        int idColumnIndex = cursor.getColumnIndex(BookEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_QUANTITY);
        int supplierColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_SUPPLIER_NAME);
        int phoneColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER);

        final long id = cursor.getLong(idColumnIndex);
        String bookName = cursor.getString(nameColumnIndex);
        String bookPrice = cursor.getString(priceColumnIndex);
        String bookQuantity = cursor.getString(quantityColumnIndex);
        String bookSupplier = cursor.getString(supplierColumnIndex);
        String supplierPhone = cursor.getString(phoneColumnIndex);

        mBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                try {
                    String bookQuantity = quantityTextView.getText().toString().trim();

                    if (Integer.valueOf(bookQuantity) == 0) {
                        throw new IllegalArgumentException("No quantity available");
                    }

                    mCurrentBookUri = ContentUris.withAppendedId(BookEntry.CONTENT_URI, id);
                    values.put(BookEntry.COLUMN_QUANTITY, String.valueOf(Integer.valueOf(bookQuantity) - 1));
                    int rowsAffected = mContext.getContentResolver().update(mCurrentBookUri, values, null, null);
                    /*nameTextView.setText(nameTextView.getText()+"|"+String.valueOf(id));*/
                } catch (Exception ex) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    ex.printStackTrace(pw);
                    String sStackTrace = sw.toString(); // stack trace as a string
                    System.out.println(sStackTrace);
                }
            }
        });


        /*idTextView.setText(String.valueOf(id));*/
        nameTextView.setText(bookName);
        priceTextView.setText(bookPrice);
        quantityTextView.setText(bookQuantity);
        supplierTextView.setText(bookSupplier);
        phoneTextView.setText(supplierPhone);
    }

}
