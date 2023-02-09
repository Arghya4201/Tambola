package com.domino.tambola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import  java.util.*;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("UseCompatLoadingForDrawables")
    Set<Integer> s1,s2,sf;
    int c1,c2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();
    }
    public void start()
    {
        sf=new HashSet<>(10);
        int i,j,c=0;
        c1=0;
        c2=0;
        LinearLayout l1= findViewById(R.id.lm1);
        Integer ar[]=new Integer[10];
        ar=randomly(1);
        for(i=1;i<=2;i++)
        {
            LinearLayout l2=new LinearLayout(this);
            l1.addView(l2);
            for(j=1;j<=5;j++)
            {
                TextView t1=new TextView(this);
                if(ar[c]>0){
                    t1.setId(ar[c]);
                    t1.setText(String.valueOf(ar[c++]));
                    t1.setTextColor(Color.rgb(0,0,0));}
                else{
                    t1.setText(" ");
                    c++;}
                t1.setBackground(getDrawable(R.drawable.layout));
                t1.setWidth(70);
                t1.setHeight(70);

                t1.setGravity(Gravity.CENTER);
                l2.addView(t1);
            }

        }
        ar=randomly(2);
        c=0;
        l1=(LinearLayout) findViewById(R.id.lm2);
        for(i=1;i<=2;i++)
        {
            LinearLayout l2=new LinearLayout(this);
            l1.addView(l2);
            for(j=1;j<=5;j++)
            {
                TextView t1=new TextView(this);
                if(ar[c]>0){
                    t1.setId(ar[c]);
                    t1.setText(String.valueOf(ar[c++]));
                    t1.setTextColor(Color.rgb(0,0,0));}
                else{
                    t1.setText(" ");
                    c++;}
                t1.setBackground(getDrawable(R.drawable.layout));
                t1.setWidth(70);
                t1.setHeight(70);
                t1.setGravity(Gravity.CENTER);
                l2.addView(t1);
            }

        }
        grid();
    }
    public Integer[] randomly(int n) {
        Set<Integer> ar = new HashSet<Integer>();
        boolean b = false;
        int i, rand, odd_even_counter = 0, ce = 0, co = 0,c=-1;
        Random r = new Random();
        for (i = 0; ;i++) {
            odd_even_counter = r.nextInt((100-0)+1)+0;
            //System.out.print(odd_even_counter+","+ce+","+co+",");
            if (co < 5 && ce < 5) {
                if (odd_even_counter%2 == 1) {
                    co++;
                    rand = r.nextInt((91-1)+1)+1;
                    ar.add(rand);
                    //System.out.println();
                }
                else {
                    ce++;
                    c--;
                    //System.out.println("even ");
                    ar.add(c);
                }
            }
            else {
                co++;
                rand = r.nextInt((91-1)-1)+1;
                ar.add(rand);
            }

            if (co == 5)
                break;
        }

        if(ar.size()<10)
        {
            while(ar.size()<10)
            {
                ar.add(c--);

            }
        }
        System.out.println(ar);
        Integer arr[] = new Integer[ar.size()];
        ar.toArray(arr);
        if(n==1)
            s1=new HashSet<Integer>(ar);
        else
            s2=new HashSet<Integer>(ar);
        return arr;
    }
    public void grid()
    {
        int i,j,c=0,id=0;
        LinearLayout mainlayout=(LinearLayout) findViewById(R.id.mainlayout);
        for(i=1;i<=9;i++)
        {
            LinearLayout l=new LinearLayout(this);
            mainlayout.addView(l);
            id++;
            //l.generateViewId();
            l.setId(id+90);
            //LinearLayout lid=(LinearLayout) findViewById(id);
            for(j=1;j<=10;j++)
            {
                c++;
                TextView t=new TextView(this);
                t.setText(String.valueOf(c));
                t.setId(c);
                t.setWidth(100);
                t.setHeight(100);
                t.setGravity(Gravity.CENTER);
                t.setTextColor(Color.rgb(0,0,0));
                //t.setTextColor(Color.rgb(200,0,0));
                t.setBackground(getDrawable(R.drawable.layout));
                l.addView(t);
            }
        }
    }
    public void play(View view)
    {

        TextView t1= (TextView) findViewById(R.id.count);
        TextView t2= (TextView) findViewById(R.id.count1);
        t1.setTextColor(Color.rgb(0,0,0));
        t2.setTextColor(Color.rgb(0,0,0));
        TextView tf=(TextView) findViewById(R.id.lasttext);
        if(c1!=5 && c2!=5)
        {
            Random r=new Random();
            //int rand = r.nextInt((90-1)+1)+1;
            int max = 90; //Upper range value
            int min = 1; //Lower range value
            int range = max-min+1;
            int rand1 = (int) (Math.random()*range)+min;
            while(sf.add(rand1)==false)
            {
                rand1 = (int)(Math.random()*range)+min;
                //rand = r.nextInt((90-1)+1)+1;
            }
            TextView id=(TextView) findViewById(rand1);
            id.setTextColor(Color.rgb(200,0,0));
            if(s1.add(rand1)==false)
                c1++;
            if(s2.add(rand1)==false)
                c2++;

            t1.setText(String.valueOf(c1));
            t1.setGravity(Gravity.CENTER);
            t2.setText(String.valueOf(c2));
            t2.setGravity(Gravity.CENTER);
        }
        if(c1==5)
            tf.setText("PLAYER 1 WINS");
        else if(c2==5)
            tf.setText("PLAYER 2 WINS");
//        if(c1==5 || c2==5)
//        {
//            Button b=(Button) findViewById(R.id.button);
//            b.setText("RESET");
//            if(c1==-1)
//                start();
//            c1=-1;
//        }
    }

}