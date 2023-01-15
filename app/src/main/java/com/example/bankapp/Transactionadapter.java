package com.example.bankapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Transactionadapter extends ArrayAdapter <Transaction> {

    private ArrayList<Transaction> transactions;

    public Transactionadapter(@NonNull Context context, int resource, ArrayList<Transaction> transactions) {
        super(context, resource,transactions);
        this.transactions=transactions;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.transaction_row,parent,false);

        ImageView imageView=convertView.findViewById(R.id.cover);
        imageView.setBackgroundResource(transactions.get(position).getIcon());

        TextView txtdesc=convertView.findViewById(R.id.txt_tran_desc);
        txtdesc.setText(transactions.get(position).getLabel());

        TextView txtmant=convertView.findViewById(R.id.txt_tran_mant);
        txtmant.setText(transactions.get(position).getPrice());

        TextView txtdate=convertView.findViewById(R.id.txt_tran_date);
        txtdate.setText(transactions.get(position).getDate().toString());

        Intent intent = new Intent(getContext(),TransactionActivity.class);

        intent.putExtra("label",transactions.get(position).getLabel());

        return convertView;
    }
}
