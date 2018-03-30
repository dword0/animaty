package com.codeoxys.animatey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int x=0;
    int[] state={3,3,3,3,3,3,3,3,3};
    int[][] win={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean active=true;
    //ImageView winner=(ImageView)findViewById(R.id.imageView10);
   // LinearLayout l1=(LinearLayout)findViewById(R.id.winLayout);

    public void dropin(View view){

        ImageView counter = (ImageView) view;
        if(state[Integer.parseInt(counter.getTag().toString())]==3 && active) {
            state[Integer.parseInt(counter.getTag().toString())] = x;
            counter.setTranslationY(-1000f);
            if (x == 0) {
                counter.setImageResource(R.drawable.yellow);
                x = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                x = 0;
            }
            counter.animate().translationY(0f).setDuration(300);
            for(int[] w:win) {
                if(state[w[0]]==state[w[1]]&&state[w[1]]==state[w[2]]&&state[w[1]]!=3){
                    if(state[w[0]]==0) {
                        active=false;
                        Toast.makeText(this, "YELLOW WON", Toast.LENGTH_SHORT).show();
                        LinearLayout line=(LinearLayout)findViewById(R.id.winLayout);
                        line.setVisibility(view.VISIBLE);
                    }
                    else if (state[w[0]]==1) {
                        active = false;
                        Toast.makeText(this, "RED WON", Toast.LENGTH_SHORT).show();
                        LinearLayout line = (LinearLayout) findViewById(R.id.winLayout);
                        line.setVisibility(view.VISIBLE);
                    }
                    else{
                        boolean gameOver=true;
                        for(int countState:state){
                            if(countState==3)
                                gameOver=false;
                        }
                        if(gameOver){
                            active = false;
                            Toast.makeText(this, "IT'S A DRAW", Toast.LENGTH_SHORT).show();
                            LinearLayout line = (LinearLayout) findViewById(R.id.winLayout);
                            line.setVisibility(view.VISIBLE);
                        }
                    }
                }
            }
        }
    }
    public void clickwin(View view){
        LinearLayout line=(LinearLayout)findViewById(R.id.winLayout);
        line.setVisibility(view.INVISIBLE);
        x=0;
        for(int i=0;i<9;i++)
            state[i]=3;
        GridLayout g1=(GridLayout)findViewById(R.id.gridlayout);
        for(int i=0;i<9;i++)
            ((ImageView)g1.getChildAt(i)).setImageResource(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
