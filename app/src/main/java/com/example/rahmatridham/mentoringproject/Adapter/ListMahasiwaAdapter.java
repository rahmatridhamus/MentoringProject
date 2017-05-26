package com.example.rahmatridham.mentoringproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rahmatridham.mentoringproject.Model.Mahasiswa;
import com.example.rahmatridham.mentoringproject.R;

import java.util.ArrayList;

/**
 * Created by rahmatridham on 10/21/2016.
 */

public class ListMahasiwaAdapter extends BaseAdapter {
    ArrayList<Mahasiswa> listMahasiswa;
    Context context;

    public ListMahasiwaAdapter(ArrayList<Mahasiswa> listMahasiswa, Context context) {
        this.listMahasiswa = listMahasiswa;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listMahasiswa.size();
    }

    @Override
    public Object getItem(int position) {
        return listMahasiswa.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_row_mahasiswa, parent, false);
        }

        ImageView gambarJK = (ImageView) v.findViewById(R.id.gambarProfil);
        TextView namaMhs = (TextView) v.findViewById(R.id.namaProfil);
        TextView nimMhs = (TextView) v.findViewById(R.id.nimProfil);

        Mahasiswa mahasiswa = listMahasiswa.get(position);

        namaMhs.setText(mahasiswa.getNama());
        nimMhs.setText(mahasiswa.getNIM());
        if(mahasiswa.getJenisKelamin().equals("L")){
            gambarJK.setImageResource(R.drawable.man);
        }else {
            gambarJK.setImageResource(R.drawable.woman);
        }

        return v;
    }
}
