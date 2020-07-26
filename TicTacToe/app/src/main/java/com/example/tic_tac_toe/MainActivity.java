package com.example.tic_tac_toe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
Button[][] buttons=new Button[3][3];
boolean player1turn=true;
int roundCount;
int player1Points,player2Points;
TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.text_view_t1);
        t2=findViewById(R.id.text_view_t2);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String buttonId="button_"+i+j;
                int resId=getResources().getIdentifier(buttonId,"id",getPackageName());
                buttons[i][j]=findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button bt_reset=findViewById(R.id.button_reset);
        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }
        if(player1turn){
            ((Button)v).setText("X");
        }else{
            ((Button)v).setText("o");
        }
        roundCount++;
        if(check()){
            if(player1turn){
                player1Points++;
                t1.setText("Player 1: "+player1Points);
                t2.setText("Player 2: "+player2Points);
                AlertDialog.Builder a=new AlertDialog.Builder(MainActivity.this);
                a.setTitle("Player 1 wins!");
                a.setMessage("Would You like to play again");
                a.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetBoard();
                    }
                });
                a.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finish();
                    }
                });
                a.show();
            }else{
                player2Points++;
                t1.setText("Player 1: "+player1Points);
                t2.setText("Player 2: "+player2Points);
                AlertDialog.Builder a=new AlertDialog.Builder(MainActivity.this);
                a.setTitle("Player 2 wins!");
                a.setMessage("Would You like to play again");
                a.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetBoard();
                    }
                });
                a.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finish();
                    }
                });
                a.show();
            }
        }else if(roundCount==9){
            AlertDialog.Builder a=new AlertDialog.Builder(MainActivity.this);
            a.setTitle("It's a draw!");
            a.setMessage("Would You like to play again");
            a.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    resetBoard();
                }
            });
            a.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.this.finish();
                }
            });
            a.show();
        }else{
            player1turn=!player1turn;
        }
    }
    boolean check(){
        String[][] field=new String[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                field[i][j]=buttons[i][j].getText().toString();
            }
        }

        for(int i=0;i<3;i++){
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")){
                return true;
            }
        }
        for(int i=0;i<3;i++){
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){
                return true;
            }
        }

        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }
        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }
    void resetBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        player1turn=true;
        roundCount=0;
    }
    void resetGame(){
        resetBoard();
        player1Points=0;
        player2Points=0;
        t1.setText("Player 1: "+player1Points);
        t2.setText("Player 2: "+player2Points);
    }
}
