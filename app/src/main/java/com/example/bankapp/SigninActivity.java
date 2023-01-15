package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity {

    TextView txtusername,txtpsw,txtlgn;
    Button btnsgn;
    UserDao ud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ud=new UserDao(this);


    }

    @Override
    protected void onResume() {
        super.onResume();


        txtlgn=findViewById(R.id.txtlgn);
        txtusername=findViewById(R.id.txtsusername);
        txtpsw=findViewById(R.id.txtspsw);
        btnsgn=findViewById(R.id.btn_sgn);


        txtlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SigninActivity.this,MainActivity.class);
                SigninActivity.this.startActivity(intent);
            }
        });

        btnsgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username,psw;
                User u ;
                username=txtusername.getText().toString();
                psw=txtpsw.getText().toString();

                if (username.equals("") && psw.equals("")){
                    Toast.makeText(SigninActivity.this, R.string.user_info_err, Toast.LENGTH_SHORT).show();
                    return;
                }
                u=new User(username,psw);
                ud.SignIn(u);

                Intent intent=new Intent(SigninActivity.this,MainActivity.class);
                SigninActivity.this.startActivity(intent);
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