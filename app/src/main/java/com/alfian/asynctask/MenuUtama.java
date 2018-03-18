package com.alfian.asynctask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuUtama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
//  Membuat inten= untuk melemparkan ke aktivitas lain
    }
    public void list(View view) {
        Intent x = new Intent(this, ListNamaMahasiswa.class);
        startActivity(x); // memulai inten
    }
    //  Membuat inten= untuk melemparkan ke aktivitas lain
    public void cariImage(View view) {
        Intent y = new Intent(this, PencariGambar.class);
        startActivity(y);   // memulai inten
    }
}
