package com.example.andra.inventory;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.andra.inventory.data.BooksContract.BookEntry;

public class InvCursorAdapter extends CursorAdapter{

    public InvCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        TextView supplierTextView = (TextView) view.findViewById(R.id.supplier);
        TextView phoneTextView = (TextView) view.findViewById(R.id.phone);

        int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_QUANTITY);
        int supplierColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_SUPPLIER_NAME);
        int phoneColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER);

        String bookName = cursor.getString(nameColumnIndex);
        String bookPrice = cursor.getString(priceColumnIndex);
        String bookQuantity = cursor.getString(quantityColumnIndex);
        String bookSupplier = cursor.getString(supplierColumnIndex);
        String supplierPhone = cursor.getString(phoneColumnIndex);

        nameTextView.setText(bookName);
        priceTextView.setText(bookPrice);
        quantityTextView.setText(bookQuantity);
        supplierTextView.setText(bookSupplier);
        phoneTextView.setText(supplierPhone);

    }

}
