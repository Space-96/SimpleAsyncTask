package edu.mssu.cis385.simpleasynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<String, Integer, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;


    SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }

    @Override
    protected void onPreExecute() {
        MainActivity.progressStart(0);
    }

    @Override
    protected String doInBackground(String... voids) {
        Random r = new Random();
        int n = r.nextInt(5);
        int s = n * 100;
        try {
            for (int i = 1; i < s; i++) {
                Thread.sleep(i);
                publishProgress(i);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    protected void onProgressUpdate(Integer... progress){
        int myValue = progress[0];
        MainActivity.progressStart(myValue);;
    }



    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
