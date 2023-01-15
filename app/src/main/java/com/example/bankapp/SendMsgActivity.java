package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SendMsgActivity extends AppCompatActivity {

    EditText txt_phone_num,txt_msg_cont;
    Button btn_send;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_msg);
    }

    @Override
    protected void onResume() {
        super.onResume();

        txt_msg_cont=findViewById(R.id.txt_msg_cont);
        txt_phone_num=findViewById(R.id.txt_phone_number);
        btn_send=findViewById(R.id.btn_send);



      /*  Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse(phone_num));
        smsIntent.putExtra("sms_body", msg);*/

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone_num=txt_phone_num.getText().toString();
                String msg=txt_msg_cont.getText().toString();

                if (msg.equals("")){
                    Toast.makeText(SendMsgActivity.this, R.string.msg_err, Toast.LENGTH_SHORT).show();
                    return;
                }

                try {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.putExtra("sms_body", msg);
                    intent.setData(Uri.parse("sms:"+phone_num));
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException anfe) {
                    Log.d("Error" , "Error");
                    Toast.makeText(SendMsgActivity.this, R.string.sms_err, Toast.LENGTH_SHORT).show();
                }

                //SmsManager manager=SmsManager.getDefault();
                //manager.sendTextMessage(phone_num,null,msg,null,null);

                //Toast.makeText(SendMsgActivity.this, "Message sent successfully", Toast.LENGTH_SHORT).show();


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