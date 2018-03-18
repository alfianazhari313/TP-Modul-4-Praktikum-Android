package com.alfian.asynctask;

import android.os.AsyncTask;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by M. Alfian Azhari on 3/17/2018.
 */

class SimpleAsyncTask extends AsyncTask<Void, Integer, List<Mahasiswa>> {

    interface Listener {
        void onDone(List<Mahasiswa> items);
        void receieveProgress(String progress);
    }

    // The TextView where we will show results
    Listener mListener;
    private List<Mahasiswa> mMahasiswa;

    // Constructor that provides a reference to the TextView from the MainActivity
    public SimpleAsyncTask(Listener mListener){
        this.mListener = mListener;
    }

    @Override
    protected List<Mahasiswa> doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        int s = n * 20;

        // Sleep for the random amount of time
        int size = 78;

        List<Mahasiswa> items = new ArrayList<>(size);
        for(int i=0; i<size; i++){
            items.add(new Mahasiswa("fiddin"+i));
            publishProgress(i, size);
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        items.add(new Mahasiswa("andre"));

        return items;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        int current = progress[0];
        int total = progress[1];

        float percentage = 100 * (float)current / (float)total;
        mListener.receieveProgress(precision(1, percentage)+"");
    }

    @Override
    protected void onPostExecute(List<Mahasiswa> mahasiswas) {
        super.onPostExecute(mahasiswas);
        mListener.onDone(mahasiswas);
    }
    public static Float precision(int decimalPlace, Float d) {

        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_UP);
        return bd.floatValue();
    }
}
