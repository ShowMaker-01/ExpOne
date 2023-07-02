package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_pt;
    Button btn_add,btn_sub,btn_mul,btn_div;
    Button btn_clr,btn_del,btn_eq;
    Button btn_squ,btn_opp;

    Button btn_sin,btn_str;
    EditText et_input;
    boolean clr_flag; //判断文本框是否清空

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化对象
        btn_0= (Button) findViewById(R.id.btn_0);
        btn_1= (Button) findViewById(R.id.btn_1);
        btn_2= (Button) findViewById(R.id.btn_2);
        btn_3= (Button) findViewById(R.id.btn_3);
        btn_4= (Button) findViewById(R.id.btn_4);
        btn_5= (Button) findViewById(R.id.btn_5);
        btn_6= (Button) findViewById(R.id.btn_6);
        btn_7= (Button) findViewById(R.id.btn_7);
        btn_8= (Button) findViewById(R.id.btn_8);
        btn_9= (Button) findViewById(R.id.btn_9);
        btn_pt= (Button) findViewById(R.id.btn_pt);
        btn_add= (Button) findViewById(R.id.btn_add);
        btn_sub= (Button) findViewById(R.id.btn_sub);
        btn_mul= (Button) findViewById(R.id.btn_mul);
        btn_div= (Button) findViewById(R.id.btn_div);
        btn_clr= (Button) findViewById(R.id.btn_clr);
        btn_del= (Button) findViewById(R.id.btn_del);
        btn_eq= (Button) findViewById(R.id.btn_eq);
        btn_squ=(Button)findViewById(R.id.btn_squ);
        btn_opp=(Button)findViewById(R.id.btn_opp);
        btn_str=(Button)findViewById(R.id.btn_str);
        btn_sin=(Button)findViewById(R.id.btn_sin);
        et_input= (EditText) findViewById(R.id.et_input);

        //给按钮设置点击事件
        btn_0.setOnClickListener(this::onClick);
        btn_1.setOnClickListener(this::onClick);
        btn_2.setOnClickListener(this::onClick);
        btn_3.setOnClickListener(this::onClick);
        btn_4.setOnClickListener(this::onClick);
        btn_5.setOnClickListener(this::onClick);
        btn_6.setOnClickListener(this::onClick);
        btn_7.setOnClickListener(this::onClick);
        btn_8.setOnClickListener(this::onClick);
        btn_9.setOnClickListener(this::onClick);
        btn_pt.setOnClickListener(this::onClick);
        btn_add.setOnClickListener(this::onClick);
        btn_sub.setOnClickListener(this::onClick);
        btn_mul.setOnClickListener(this::onClick);
        btn_div.setOnClickListener(this::onClick);
        btn_clr.setOnClickListener(this::onClick);
        btn_del.setOnClickListener(this::onClick);
        btn_eq.setOnClickListener(this::onClick);
        btn_squ.setOnClickListener(this::onClick);
        btn_opp.setOnClickListener(this::onClick);
        btn_str.setOnClickListener(this::onClick);
        btn_sin.setOnClickListener(this::onClick);

    }

    public void onClick(View v){
        String str=et_input.getText().toString();
        if(v.getId()==R.id.btn_0||v.getId()==R.id.btn_1||v.getId()==R.id.btn_2){
            if(clr_flag){
                clr_flag=false;
                str="";
                et_input.setText("");
            }
            if(str.contains("Error")){
                et_input.setText("");
            }else{
                et_input.setText(str+((Button)v).getText());
            }

        }
        if(v.getId()==R.id.btn_3||v.getId()==R.id.btn_4||v.getId()==R.id.btn_5){
            if(clr_flag){
                clr_flag=false;
                str="";
                et_input.setText("");
            }
            if(str.contains("Error")){
                et_input.setText("");
                if(str.charAt(0)=='0'){
                    et_input.setText("");
                }

            }else{
                et_input.setText(str+((Button)v).getText());
            }
        }
        if(v.getId()==R.id.btn_6||v.getId()==R.id.btn_7||v.getId()==R.id.btn_8){
            if(clr_flag){
                clr_flag=false;
                str="";
                et_input.setText("");
            }
            if(str.contains("Error")){
                et_input.setText("");

            }else{
                et_input.setText(str+((Button)v).getText());
            }

        }
         if(v.getId()==R.id.btn_9||v.getId()==R.id.btn_pt){
            if(clr_flag){
                clr_flag=false;
                str="";
                et_input.setText("");
            }
             if(str.contains("Error")){
                 et_input.setText("");

             }else
            et_input.setText(str+((Button)v).getText());
        }
        if(v.getId()==R.id.btn_add||v.getId()==R.id.btn_sub||v.getId()==R.id.btn_mul||v.getId()==R.id.btn_div){
            if(clr_flag){
                clr_flag=false;
                str="";
                et_input.setText("");
            }
            if(str.contains("+")||str.contains("-")||str.contains("×")||str.contains("÷")) {
                str=str.substring(0,str.indexOf(" "));
            }
            et_input.setText(str+" "+((Button)v).getText()+" ");
        }



        if(v.getId()==R.id.btn_clr){
            if(clr_flag)
                clr_flag=false;
            str="";
            et_input.setText("");
        }

        if(v.getId()==R.id.btn_del){
            if(clr_flag){
                clr_flag=false;
                str="";
                et_input.setText("");
            }
            else if(str!=null&&!str.equals("")){
                et_input.setText(str.substring(0,str.length()-1));
            }
        }

        if(v.getId()==R.id.btn_opp){
            if(str.contains("Error")){
                et_input.setText("");
            }
            if(clr_flag){
                clr_flag=false;
                str="";
                et_input.setText("");
            }
                if(str.length()!=0){
                    if(!str.contains("+")&&!str.contains("×")&&!str.contains("÷")){
                        double number=Double.parseDouble(str);
                        number=-number;
                        str=String.valueOf(number);
                        et_input.setText(str);
                    }else{
                        str="Error";
                        et_input.setText(str);
                    }
                }else{
                    et_input.setText("Error");
                }

        }

        if(v.getId()==R.id.btn_pt){
            if(str.contains("Error")){
                et_input.setText("");
            }
            if(clr_flag){
                clr_flag=false;
                str="";
                et_input.setText("");
            }
            String begin=str;
            if(str.contains(".")){
                    str=begin;
                    et_input.setText(str);
            }
        }

        if(v.getId()==R.id.btn_squ){
            if(str.length()!=0){
            if(str.contains("Error")){
                et_input.setText("");
            }
            if(str.length()==0){
                str="Error";
                et_input.setText(str);
            }
            if(clr_flag){
                clr_flag=false;
                str="";
                et_input.setText("");
            }

                if(str.contains("+")||str.contains("-")||str.contains("×")||str.contains("÷")){
                    str="Error";
                    et_input.setText(str);
                }else {
                    double b=Double.parseDouble(str);
                    str=String.valueOf(b);

                    b=Math.sqrt(b);

                    if(b%1==0){
                        int result=(int)b;
                        str=String.valueOf(result);
                        et_input.setText(str);
                    }else {
                        str=String.valueOf(b);
                        et_input.setText(str);
                    }
                }
            }else {
                et_input.setText("Error");
            }


        }

        if(v.getId()==R.id.btn_str){
            if(clr_flag){
                clr_flag=false;
                str="";
                et_input.setText("");
            }
            if(str.length()==0){
                str="Error";
                et_input.setText(str);
            }
            if(str.length()!=0){
                char test=str.charAt(str.length()-1);
                if(test=='.'||test=='+'||test=='-'||test=='×'||test=='÷'){
                    str=str.substring(0,str.length()-1);
                }

                int total=1;
                int c=Integer.parseInt(str);
                if(c>=0){
                    for(int i=1;i<=c;i++){
                        total*=i;
                    }
                    str=String.valueOf(total);
                    et_input.setText(str);
                }else{
                    str="Error";
                    et_input.setText(str);
                }
            }else{
                str="Error";
                et_input.setText(str);
            }

        }

        if(v.getId()==R.id.btn_sin){
            if(clr_flag){
                clr_flag=false;
                str="";
                et_input.setText("");
            }
            if(str.length()==0){
                str="Error";
                et_input.setText(str);
            }
            if(str.contains("Error")){
                et_input.setText("");
            }
            char test=str.charAt(str.length()-1);
            if(test=='.'||test=='+'||test=='-'||test=='×'||test=='÷'){
                str=str.substring(0,str.length()-1);
            }
            double number=Double.parseDouble(str);
            double radians=Math.toRadians(number);
            double sinValue=Math.sin(radians);
            str=String.valueOf(sinValue);
            et_input.setText(str);
        }

        if(v.getId()==R.id.btn_add){
            if(str.contains("Error")){
                et_input.setText("");
            }
            if(str.length()!=0){
                char test=str.charAt(str.length()-1);
                if(test=='.'){
                    str=str.substring(0,str.length()-1)+"+";
                    et_input.setText(str);
                }
            }else {
                str="0";
                et_input.setText(str);
            }

        }

        if(v.getId()==R.id.btn_sub){
            if(str.contains("Error")){
                et_input.setText("");
            }
            if(str.length()!=0){
                char test=str.charAt(str.length()-1);
                if(test=='.'){
                    str=str.substring(0,str.length()-1)+"-";
                    et_input.setText(str);
                }
            }else {
                str="0";
                et_input.setText(str);
            }

        }

        if(v.getId()==R.id.btn_mul){
            if(str.contains("Error")){
                et_input.setText("");
            }
            if(str.length()!=0){
                char test=str.charAt(str.length()-1);
                if(test=='.'){
                    str=str.substring(0,str.length()-1)+"×";
                    et_input.setText(str);
                }
            }else {
                str="0";
                et_input.setText(str);
            }

        }

        if(v.getId()==R.id.btn_div){
            if(str.contains("Error")){
                et_input.setText("");
            }
            if(str.length()!=0){
                char test=str.charAt(str.length()-1);
                if(test=='.'){
                    str=str.substring(0,str.length()-1)+"÷";
                    et_input.setText(str);
                }
            }else {
                str="0";
                et_input.setText(str);
            }
        }



        if(v.getId()==R.id.btn_eq){
            if(str.contains("Error")){
                et_input.setText("");
            }
            getResult();
        }

    }



    private void getResult(){  //计算结果方法
        try {
            String exp=et_input.getText().toString();
            if(exp==null||exp.equals("")) return;
            if(!exp.contains("")) return;
            if(clr_flag){
                clr_flag=false;
                return;
            }
            clr_flag=true;
            String s1=exp.substring(0,exp.indexOf(" "));
            String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
            String s2=exp.substring(exp.indexOf(" ")+3);
            char test='.';
            char lastChar1=s1.charAt(s1.length()-1);
            char lastChar2=s2.charAt(s2.length()-1);
            if(lastChar1==test){
                s1=s1.substring(0,s1.length()-1);
            }
            if (lastChar2==test){
                s2=s2.substring(0,s2.length()-1);
            }



            double cnt=0;
            if(!s1.equals("")&&!s2.equals("")){
                double d1=Double.parseDouble(s1);
                double d2=Double.parseDouble(s2);
                if(op.equals("+"))  cnt=d1+d2;
                if(op.equals("×"))  cnt=d1*d2;
                if(op.equals("÷")){
                    cnt=d1/d2;
                }
                if(op.equals("-"))  cnt=d1-d2;

                if(cnt%1==0) {
                    int res = (int) cnt;
                    et_input.setText(res+"");
                }else {
                    et_input.setText(cnt+"");}
            }
            else if(s1.equals("")&&!s2.equals("")){
                double d2=Double.parseDouble(s2);
                if(op.equals("+")){
                    cnt=d2;
                }
                if(op.equals("-")){
                    cnt=0-d2;
                }
                if(op.equals("×")){
                    cnt=0;
                }
                if(op.equals("÷")){
                    cnt=0;
                }
                if(!s2.contains(".")) {
                    int res = (int) cnt;
                    et_input.setText(res+"");
                }else {
                    et_input.setText(cnt+"");}
            }else{
                et_input.setText("");
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }






}

