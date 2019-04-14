package com.example.databasesekolah.ui.main.holder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.databasesekolah.Edit_data;
import com.example.databasesekolah.Entity.DataSekolah;
import com.example.databasesekolah.R;
import com.example.databasesekolah.ui.main.MainContact;

import java.util.List;

public class Adapter extends  RecyclerView.Adapter<Adapter.ViewHolder>{
    Context context;
    List<DataSekolah> list;
    MainContact.hapus view;
    public Adapter(Context context, List<DataSekolah> list, MainContact.hapus view) {
        this.view = view;
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_data, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final DataSekolah data = list.get(i);
        viewHolder.textView_1.setText(data.getJml_siswa());
        viewHolder.textView_2.setText(data.getJml_guru());
        viewHolder.textView_3.setText(data.getNama_sekolah());
        viewHolder.textView_4.setText(data.getAlamat());
        viewHolder.btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.deleteData(data);
               // return true;
            }
        });
        viewHolder.btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Edit_data.class);
                intent.putExtra("jml_siswa", data.getJml_siswa());
                intent.putExtra("jml_guru", data.getJml_guru());
                intent.putExtra("nama", data.getNama_sekolah());
                intent.putExtra("alamat", data.getAlamat());
                intent.putExtra("id", data.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_1, textView_2, textView_3, textView_4;
        Button btn_1, btn_2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_1 = itemView.findViewById(R.id.tv_item_jml_siswa);
            textView_2 = itemView.findViewById(R.id.tv_item_jml_guru);
            textView_3 = itemView.findViewById(R.id.tv_item_nama_sekolah);
            textView_4 = itemView.findViewById(R.id.tv_item_alamat);
            btn_1 = itemView.findViewById(R.id.btn_hapus);
            btn_2 = itemView.findViewById(R.id.btn_edit);
        }
    }
}
