package com.example.gradesearch;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imageButton;
    private TextView textView_inquiry, textView_quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        textView_inquiry = (TextView) findViewById(R.id.textView_inquiry);
        textView_quit = (TextView) findViewById(R.id.textView_quit);

        imageButton.setOnClickListener(this);
        textView_inquiry.setOnClickListener(this);
        textView_quit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton:
                startActivity(new Intent(this,ForSearch.class));
                break;
            case R.id.textView_inquiry:
                startActivity(new Intent(this,ForSearch.class));
                break;
            case R.id.textView_quit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("您确定要退出登录吗？").setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                startActivity(new Intent(MainActivity.this, com.example.gradesearch.LoginActivity.class));
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            default:
                break;
        }
    }
}