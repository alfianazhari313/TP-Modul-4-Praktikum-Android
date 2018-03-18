package com.alfian.asynctask;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.List;

public class ListNamaMahasiswa extends AppCompatActivity implements

    SimpleAsyncTask.Listener,//buat pasang listener dari class simple asynctask
        View.OnClickListener{  //buat listener on clik
    private static final String TEXT_STATE = "currentText";
    private RecyclerView mRvMahasiswa;
    private List<Mahasiswa> mMahasiswas;
    private Button mButton;
    private ProgressDialog progressDialog;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRvMahasiswa = (RecyclerView) findViewById(R.id.rvListMhs);
        mButton = (Button) findViewById(R.id.btn_mulai);
        mButton.setOnClickListener((View.OnClickListener) this);
        progressDialog = new ProgressDialog(this);

        if(savedInstanceState != null){
            startTask();
        }


    }
// bikin prosedur dengan nama starttask
    public void startTask () {
        final SimpleAsyncTask simpleAsyncTask = new SimpleAsyncTask(this);
        simpleAsyncTask.execute();
        progressDialog.setTitle("Loading data..."); //membuat judul progress bar
        progressDialog.setCancelable(true);
        progressDialog.setButton("Cancel loading", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                simpleAsyncTask.cancel(true);
                return;
            }
        });
        progressDialog.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
       outState.putString(TEXT_STATE, "a");
    }

    @Override
    public void onDone(List<Mahasiswa> items) {
        mMahasiswas = items;
        MenuAdapter menuAdapter = new MenuAdapter(mMahasiswas);

        mRvMahasiswa.setLayoutManager(new GridLayoutManager(this,1));
        mRvMahasiswa.setAdapter(menuAdapter);
        progressDialog.hide();
        Log.e("JML", mMahasiswas.size()+"");
    }
//dipanngil ketika fungsi publishProgress di paanggil
    @Override
    public void receieveProgress(String progress) {
        progressDialog.setMessage(progress+"%");
        Log.e("PROGRESS", progress);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_mulai:
                startTask();
                break;


        }

    }
}
