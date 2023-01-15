package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private ArrayList<User> users;
    String username,psw;
    TextView txtusername,txtpsw,txtsgn,icon_menu;
    Button btnlgn;
    UserDao ud;
    Switch cwrem_me;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ud=new UserDao(this);





    }


    public int authentifier()
    {
        int t=-1;
        String username,psw;
        username=txtusername.getText().toString();
        psw=txtpsw.getText().toString();
        for (int i=0;i<users.size();i++)
        {
            if (username.equals(users.get(i).getUsername().toString()) && psw.equals(users.get(i).getPassword().toString()))
            {
                if (cwrem_me.isChecked())
                {
                    prefs.edit().putString("username",username).commit();
                    prefs.edit().putString("psw",psw).commit();
                }
                t=1;
            }
        }
        return t;
    }

    @Override
    protected void onResume() {
        super.onResume();
        users=ud.getAllInList();
        txtsgn=findViewById(R.id.txtsgn);
        txtusername=findViewById(R.id.txtusername);
        txtpsw=findViewById(R.id.txtpsw);
        btnlgn=findViewById(R.id.btn_lgn);
        cwrem_me=findViewById(R.id.cwrem_me);
        prefs=getPreferences(Context.MODE_PRIVATE);




        username=prefs.getString("username","default");
        psw=prefs.getString("psw","default");

        if (!username.equals("default") && !psw.equals("default"))
        {
            Intent intent=new Intent(MainActivity.this,TransactionActivity.class);
            MainActivity.this.startActivity(intent);
        }

        txtsgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SigninActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        btnlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (authentifier()>-1)
                {
                    Intent intent=new Intent(MainActivity.this,TransactionActivity.class);
                    MainActivity.this.startActivity(intent);
                    Toast.makeText(MainActivity.this, R.string.hello_msg, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, R.string.login_info_err_msg, Toast.LENGTH_SHORT).show();
                }
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
       // Toast.makeText(this, "Oncreate"+username+psw+cwrem_me.isChecked(), Toast.LENGTH_SHORT).show();


    }
}