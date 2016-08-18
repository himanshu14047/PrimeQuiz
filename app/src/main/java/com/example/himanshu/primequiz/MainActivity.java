package com.example.himanshu.primequiz;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    TextView Ques_Text,Correct_Text,InCorrect_Text,Correct_Text_Value,InCorrect_Text_Value;
    Button True_Button,False_Button,Next_Button;

    public int prime_check(int n)
    {
        int i;
        if(n==1)
        {
            return -1;
        }
        for(i=2;i<=Math.sqrt(n);i++)
        {
            if(n%i==0)
            {
                return -1;
            }
        }
        return 1;
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d("TAG","Pause");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("TAG","Resume");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState)
    {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.d("TAG","Restore");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG","CREATE");
        Ques_Text= (TextView)findViewById(R.id.question_text);
        Correct_Text = (TextView)findViewById(R.id.correct_text);
        InCorrect_Text = (TextView)findViewById(R.id.incorrect_text);
        Correct_Text_Value = (TextView)findViewById(R.id.correct_textvalue);
        InCorrect_Text_Value = (TextView)findViewById(R.id.incorrect_textvalue);
        True_Button = (Button) findViewById(R.id.true_button);
        True_Button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String temp_string[]=Ques_Text.getText().toString().split(" ");
                if(prime_check(Integer.parseInt(temp_string[1]))==1)
                {
                    int temp_value=Integer.parseInt(Correct_Text_Value.getText().toString());
                    temp_value++;
                    Toast.makeText(MainActivity.this, "Correct Answer",
                            Toast.LENGTH_LONG).show();
                    Correct_Text_Value.setText(Integer.toString(temp_value));

                }
                else
                {
                    int temp_value=Integer.parseInt(InCorrect_Text_Value.getText().toString());
                    temp_value++;
                    Toast.makeText(MainActivity.this, "Incorrect Answer",
                            Toast.LENGTH_LONG).show();
                    InCorrect_Text_Value.setText(Integer.toString(temp_value));
                }
                True_Button.setEnabled(false);
                False_Button.setEnabled(false);
            }
        });
        False_Button = (Button) findViewById(R.id.false_button);
        False_Button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String temp_string[]=Ques_Text.getText().toString().split(" ");
                if(prime_check(Integer.parseInt(temp_string[1]))==-1)
                {
                    int temp_value=Integer.parseInt(Correct_Text_Value.getText().toString());
                    temp_value++;
                    Toast.makeText(MainActivity.this, "Correct Answer",
                            Toast.LENGTH_LONG).show();
                    Correct_Text_Value.setText(Integer.toString(temp_value));
                }
                else
                {
                    int temp_value=Integer.parseInt(InCorrect_Text_Value.getText().toString());
                    temp_value++;
                    Toast.makeText(MainActivity.this, "Incorrect Answer",
                            Toast.LENGTH_LONG).show();
                    InCorrect_Text_Value.setText(Integer.toString(temp_value));
                }
                True_Button.setEnabled(false);
                False_Button.setEnabled(false);
            }
        });
        Next_Button = (Button) findViewById(R.id.next_button);
        Next_Button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Random Random_No=new Random();
                int Random_Int=Random_No.nextInt(1000)+1;
                Ques_Text.setText("Is "+Integer.toString(Random_Int)+" a Prime Number ?");

                True_Button.setEnabled(true);
                False_Button.setEnabled(true);
            }

        });
        if(savedInstanceState==null)
        {
            Random Random_No = new Random();
            int Random_Int = Random_No.nextInt(1000) + 1;
            Ques_Text.setText("Is " + Integer.toString(Random_Int) + " a Prime Number ?");
            Correct_Text_Value.setText("0");
            InCorrect_Text_Value.setText("0");

        }
        else
        {
            String Saved_Number=savedInstanceState.getString("Saved_Number");
            Ques_Text.setText("Is " + Saved_Number + " a Prime Number ?");
            Correct_Text_Value.setText(savedInstanceState.getString("Saved_Correct_Number"));
            InCorrect_Text_Value.setText(savedInstanceState.getString("Saved_InCorrect_Number"));
            if(savedInstanceState.getString("Saved_Button_State").compareTo("True")==0)
            {
                True_Button.setEnabled(true);
                False_Button.setEnabled(true);
            }
            else
            {
                True_Button.setEnabled(false);
                False_Button.setEnabled(false);
            }
        }

    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        Log.d("TAG","Save");
        String temp_string[]=Ques_Text.getText().toString().split(" ");
        String sno=temp_string[1];
        outState.putString("Saved_Number",sno);
        outState.putString("Saved_Correct_Number",Correct_Text_Value.getText().toString());
        outState.putString("Saved_InCorrect_Number",InCorrect_Text_Value.getText().toString());
        if(True_Button.isEnabled())
        {
            outState.putString("Saved_Button_State","True");
        }
        else
        {
            outState.putString("Saved_Button_State","False");
        }
    }
}
