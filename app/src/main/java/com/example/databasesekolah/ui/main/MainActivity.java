package com.example.databasesekolah.ui.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databasesekolah.Entity.AppDatabase;
import com.example.databasesekolah.Entity.DataSekolah;
import com.example.databasesekolah.R;

public class MainActivity extends AppCompatActivity {
    AppDatabase appDatabase;
    private EditText editText_1, editText_2, editText_3, editText_4;
    private Button btn_1, btn_2;
    private String data_1, data_2, data_3, data_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_1 = findViewById(R.id.et_jml_siswa);
        editText_2 = findViewById(R.id.et_jml_guru);
        editText_3 = findViewById(R.id.et_nama_sekolah);
        editText_4 = findViewById(R.id.et_alamat_sekolah);
        btn_1 = findViewById(R.id.btn_submit);
        btn_2 = findViewById(R.id.btn_lihat);
        appDatabase = AppDatabase.iniDb(getApplicationContext());
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input();
                Intent x = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(x);
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(x);
            }
        });
    }

    public void input() {
        data_1 = editText_1.getText().toString();
        data_2 = editText_2.getText().toString();
        data_3 = editText_3.getText().toString();
        data_4 = editText_4.getText().toString();
        final DataSekolah dataSekolah = new DataSekolah();
        dataSekolah.setJml_siswa(data_1);
        dataSekolah.setJml_guru(data_2);
        dataSekolah.setNama_sekolah(data_3);
        dataSekolah.setAlamat(data_4);
        new InsertData(appDatabase, dataSekolah).execute();
    }

    class InsertData extends AsyncTask<Void, Void, Long> {
        private AppDatabase database;
        private DataSekolah dataSekolah;

        public InsertData(AppDatabase database, DataSekolah dataSekolah) {
            this.database = database;
            this.dataSekolah = dataSekolah;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return database.dao().insertData(dataSekolah);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();

        }

    }

}
