package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {

    ListView trns_list;
    TransactionDao td;
    FloatingActionButton floatingActionButton_add;

    private ArrayList<Transaction> transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        td=new TransactionDao(this);

        floatingActionButton_add=findViewById(R.id.floating_add);


    }

    @Override
    protected void onResume() {
        super.onResume();

        trns_list=findViewById(R.id.list_trans);
        transactions=td.getAllInList();

        Transactionadapter transactionAdapter=new Transactionadapter(getApplicationContext(),R.layout.transaction_row,transactions);
        trns_list.setAdapter(transactionAdapter);

        trns_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),TransactiondetailsActivity.class);
                intent.putExtra("numcmp",transactions.get(position).getNumCompte());
                intent.putExtra("numref",transactions.get(position).getNumRef());
                intent.putExtra("label",transactions.get(position).getLabel());
                intent.putExtra("dateop",transactions.get(position).getDate());
                intent.putExtra("price",transactions.get(position).getPrice());
                intent.putExtra("solde",transactions.get(position).getSolde());
                getApplicationContext().startActivity(intent);
            }
        });

        floatingActionButton_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                td.AddTransaction(new Transaction(R.drawable.transaction_icon,"Transaction","200 dh","04/07/2022","54712581","658742",6000));

                Toast.makeText(TransactionActivity.this, R.string.add_succ, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TransactionActivity.this,TransactionActivity.class));
            }
        });

        TextView icon_lang=findViewById(R.id.icon_laung);
        RelativeLayout lang_fragment=findViewById(R.id.frg_ch_lang);
        TextView icon_close=findViewById(R.id.icon_close);

        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lang_fragment.setVisibility(View.GONE);
            }
        });

        icon_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lang_fragment.setVisibility(View.VISIBLE);
            }
        });
    }
}