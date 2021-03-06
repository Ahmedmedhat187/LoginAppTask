package com.example.ahmed.loginapptask;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Locked extends AppCompatActivity {
    ProgressBar pb;
    TextView tv , tvh ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locked);

        pb = (ProgressBar)findViewById(R.id.progressBar);
        tv = (TextView)findViewById(R.id.progressBartext);
        tvh = (TextView)findViewById(R.id.tv_hack);
        tvh.setVisibility(View.INVISIBLE);


        new locked().execute();
        Intent i = getIntent();
        String hack = i.getStringExtra("key");
        if (hack.contains("hack"))
        {
            tvh.setVisibility(View.VISIBLE);
            tvh.setText("You are locked , Dont try to hack again");
        }
        else
        {
            tvh.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void finish() {
        String s = tv.getText().toString();
        int x = Integer.parseInt(s);
        Toast.makeText(getBaseContext() ,"Wait to "+ (5-x) + " to unlock" , Toast.LENGTH_SHORT).show();
    }

    public class locked extends AsyncTask<Void , Integer , Integer>
    {
        @Override
        protected void onPreExecute() {
            pb.setMax(5);
        }
        @Override
        protected Integer doInBackground(Void... params) {
            for (int i=0;i<5;i++)
            {
                publishProgress(i);
                try { Thread.sleep(1000);}
                catch (InterruptedException e) {e.printStackTrace();}
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            pb.setProgress(values[0]);
            tv.setText(values[0].toString());

        }
        @Override

        protected void onPostExecute(Integer Void) {
            Toast.makeText(getBaseContext() , "Try again" , Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getBaseContext() , MainActivity.class);
            startActivity(i);
        }
    }

}
