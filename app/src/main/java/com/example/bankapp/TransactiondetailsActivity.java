package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TransactiondetailsActivity extends AppCompatActivity {


    TextView txtnumcmp;
    TextView txtnumref;
    TextView txtdesc;
    TextView txtdateop;
    TextView txtmnt;
    TextView txtsold;

    TextView btn_send_msg,btn_localis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactiondetails);


    }

    @Override
    protected void onResume() {
        super.onResume();

        txtnumcmp=findViewById(R.id.compte);
        txtnumref=findViewById(R.id.reference);
        txtdesc=findViewById(R.id.description);
        txtdateop=findViewById(R.id.date);
        txtmnt=findViewById(R.id.montant);
        txtsold=findViewById(R.id.solde);
        btn_send_msg=findViewById(R.id.btn_sendmsg);
        btn_localis=findViewById(R.id.btn_localis);


        Intent intent=getIntent();
        String numcmp=intent.getExtras().getString("numcmp");
        String numref=intent.getExtras().getString("numref");
        String desc=intent.getExtras().getString("label");
        String dateop=intent.getExtras().getString("dateop");
        String mant=intent.getExtras().getString("price");
        Double solde=intent.getExtras().getDouble("solde");

        txtnumcmp.setText(numcmp);
        txtnumref.setText(numref);
        txtdesc.setText(desc);
        txtdateop.setText(dateop);
        txtmnt.setText(mant);
        txtsold.setText(solde + " dh");

        btn_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(TransactiondetailsActivity.this,SendMsgActivity.class);
                startActivity(intent1);
            }
        });

        btn_localis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(TransactiondetailsActivity.this,AganceMapsActivity.class);
                startActivity(intent2);
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