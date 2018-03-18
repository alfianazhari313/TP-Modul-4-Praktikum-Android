package com.alfian.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PencariGambar extends AppCompatActivity {
    private Button mCari;
    private ImageView mGambar;
    private String imageLink;
    private EditText mUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencari_gambar);
//inisialisasi
        mCari = findViewById(R.id.btn_cari);
        mGambar = findViewById(R.id.imageResult);
        mUrl=findViewById(R.id.edt_url);
//Pemutaran layar
        if (savedInstanceState != null) {
            String url = savedInstanceState.getString("URL");
            mUrl.setText(url);
            Picasso.get().load(url).into(mGambar);

        }
    }
//ketika button di clik
    public void searchPic(View view) {

        imageLink = mUrl.getText().toString();
        Log.e("URL", imageLink);
        ImageDownloader  imageDownloader = new ImageDownloader();
        imageDownloader.execute(imageLink);
    }
//untuk meyimpan url ketika terjadi rotate screen
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("URL", mUrl.getText().toString());


    }
    //untuk meyimpan gambar ketika terjadi rotate screen
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String url = savedInstanceState.getString("URL");
        Picasso.get().load(url).into(mGambar); //menampilkan gambar
    }
//class async task untuk mendownlood gambar
    public class ImageDownloader extends AsyncTask<String, Void, String> {

//onPreExecute()
// Metode ini berjalan di thread UI, dan digunakan untuk menyiapkan tugas Anda (seperti menampilkan progress bar).
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("IMAGE", "LOADING");
        }
//doInBackground()
// Di sinilah Anda menerapkan kode untuk menjalankan pekerjaan yang akan dilakukan pada thread terpisah.
        @Override
        protected String doInBackground(String... params) {
            return params[0];
        }
//onPostExecute()
// Sekali lagi di thread UI, ini digunakan untuk memperbarui hasilnya ke UI setelah AsyncTask selesai loading.
        @Override
        protected void onPostExecute(String Link) {
            super.onPostExecute(imageLink);

            Picasso.get().load(imageLink).into(mGambar);
            Log.e("IMAGE", "LOADED");
        }

    }

}
