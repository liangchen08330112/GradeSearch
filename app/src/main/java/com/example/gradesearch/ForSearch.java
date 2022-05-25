package com.example.gradesearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ForSearch extends AppCompatActivity implements View.OnClickListener {
    EditText xuehao,name,grade;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_search);
        //用于连接云数据库
        Bmob.initialize(this, "b5275c7bd39777e5666d5832ba50478e");
        init();
    }
    /*
    * 用于初始化控件
    * */
    private void init(){
        xuehao=findViewById(R.id.et_xuehao);
        name=findViewById(R.id.editText_name);
        grade=findViewById(R.id.editText_grades);
        search=findViewById(R.id.button_insert);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String n=name.getText().toString().trim();
        String x=xuehao.getText().toString().trim();
        if (n.equals("")){
            Toast.makeText(this, "请输入姓名！", Toast.LENGTH_SHORT).show();
        }else if(x.equals("")){
            Toast.makeText(this, "请输入学号！", Toast.LENGTH_SHORT).show();
        }else {
            BmobQuery<StudentGrade> b1=new BmobQuery<>();//查询条件1
            b1.addWhereEqualTo("Name",n);
            b1.findObjects(new FindListener<StudentGrade>() {
                @Override
                public void done(List<StudentGrade> list, BmobException e) {
                    if (e==null){
                        if (list.size()==0){
                            Toast.makeText(ForSearch.this, "没有此人！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
            });
            BmobQuery<StudentGrade> b2=new BmobQuery<>();//查询条件2
            b2.addWhereEqualTo("Number",x);
            b2.findObjects(new FindListener<StudentGrade>() {
                @Override
                public void done(List<StudentGrade> list, BmobException e) {
                    if (e==null){
                        if (list.size()==0){
                            Toast.makeText(ForSearch.this, "没有此学号！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
            });
            List<BmobQuery<StudentGrade>> queries = new ArrayList<BmobQuery<StudentGrade>>();
            queries.add(b1);
            queries.add(b2);

            BmobQuery<StudentGrade> search=new BmobQuery<>();//查询条件1
            search.and(queries);

                search.findObjects(new FindListener<StudentGrade>() {
                    @Override
                    public void done(List<StudentGrade> list, BmobException e) {
                        if (e == null) {
                            try {
                                grade.setText(list.get(0).getGrade());
                            }catch (Exception a){
                            Toast.makeText(getApplicationContext(), "学号和姓名不匹配！", Toast.LENGTH_SHORT).show();
                        }
                        } else {
                            Toast.makeText(ForSearch.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        }
    }
}