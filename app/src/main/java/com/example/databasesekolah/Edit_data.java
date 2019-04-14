package com.example.databasesekolah;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databasesekolah.Entity.AppDatabase;
import com.example.databasesekolah.Entity.DataSekolah;
import com.example.databasesekolah.ui.main.Main2Activity;
import com.example.databasesekolah.ui.main.MainContact;
import com.example.databasesekolah.ui.main.Presenter;
import com.example.databasesekolah.ui.main.holder.Adapter;

public class Edit_data extends AppCompatActivity implements MainContact.view {
    private AppDatabase appDatabase;
    private Presenter presenter;
    private Adapter adapter;
    private EditText editText1, editText2, editText3, editText4;
    private Button btn1;
    private String data_1, data_2, data_3, data_4;
    private boolean edit = false;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        editText1 = findViewById(R.id.et_jml_siswa);
        editText2 = findViewById(R.id.et_jml_guru);
        editText3 = findViewById(R.id.et_nama_sekolah);
        editText4 = findViewById(R.id.et_alamat_sekolah);
        btn1 = findViewById(R.id.btn_submit);
        presenter = new Presenter(this);
        appDatabase = AppDatabase.iniDb(getApplicationContext());
        data_1 = getIntent().getStringExtra("jml_siswa");
        data_2 = getIntent().getStringExtra("jml_guru");
        data_3 = getIntent().getStringExtra("nama");
        data_4 = getIntent().getStringExtra("alamat");
        id = getIntent().getIntExtra("id", 99);
        editText1.setText(data_1);
        editText2.setText(data_2);
        editText3.setText(data_3);
        editText4.setText(data_4);
        btn1.setOnClickListener(this);
    }

    @Override
    public void resetForm() {
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        btn1.setText("Submit");
    }

    @Override
    public void sukses() {
        Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), Main2Activity.class));
    }

    @Override
    public void editData(DataSekolah item) {
        editText1.setText(item.getJml_siswa());
        editText2.setText(item.getJml_guru());
        editText3.setText(item.getNama_sekolah());
        editText4.setText(item.getAlamat());
        edit = true;
        btn1.setText("Update");
    }

    @Override
    public void onClick(View v) {
        String jml_siswa, jml_guru, nama_sekolah, Alamat;
        jml_siswa = editText1.getText().toString();
        jml_guru = editText2.getText().toString();
        nama_sekolah = editText3.getText().toString();
        Alamat = editText4.getText().toString();
        if (v == btn1) {
            if (jml_siswa.equals("") || jml_guru.equals("") || nama_sekolah.equals("") || Alamat.equals("")) {
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
            } else {

                presenter.editData(jml_siswa, jml_guru, nama_sekolah, Alamat, id, appDatabase);
                edit = false;
            }
            resetForm();
        }
    }
}

