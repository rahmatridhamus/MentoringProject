package com.example.rahmatridham.mentoringproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rahmatridham.mentoringproject.Adapter.ListMahasiwaAdapter;
import com.example.rahmatridham.mentoringproject.Model.Mahasiswa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MahasiswaView extends AppCompatActivity {
    ListView daftarMahasiswa;
    FloatingActionButton fab;
    ListMahasiwaAdapter adapter;
    ArrayList<Mahasiswa> mahasiswas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.grey));

        fab = (FloatingActionButton) findViewById(R.id.fab);
        daftarMahasiswa = (ListView) findViewById(R.id.listMahasiswa);

//        addMhs();
        getAllMahasiswa("http://api-android.herokuapp.com/mahasiswa");

        adapter = new ListMahasiwaAdapter(mahasiswas, getApplicationContext());

        daftarMahasiswa.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        daftarMahasiswa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mahasiswa mahasiswa = mahasiswas.get(position);
                Intent intent = new Intent(MahasiswaView.this,DetailProfil.class);
                intent.putExtra("NamaLengkap",mahasiswa.getNama());
                intent.putExtra("Nim",mahasiswa.getNIM());
                intent.putExtra("JenisKelamin",mahasiswa.getJenisKelamin());
                intent.putExtra("NoTelp",mahasiswa.getNoTelpon());
                intent.putExtra("Alamat",mahasiswa.getAlamat());
                startActivity(intent);

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MahasiswaView.this,FormPage.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void addMhs() {
        mahasiswas.add(new Mahasiswa("Ido ganteng", "sukabirus", "082145125", "1103133423", "L"));
        mahasiswas.add(new Mahasiswa("Indah cantik", "sukabirus", "082145125", "1103133423", "P"));
        mahasiswas.add(new Mahasiswa("Rizal ganteng", "sukabirus", "082145125", "1103133423", "L"));
        mahasiswas.add(new Mahasiswa("Wildan ganteng", "sukabirus", "082145125", "1103133423", "L"));
        mahasiswas.add(new Mahasiswa("Adis cantik", "sukabirus", "082145125", "1103133423", "P"));
    }

    private void getAllMahasiswa(String url) {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray array = object.getJSONArray("data");
                            Mahasiswa mahasiswa;

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object1 = array.getJSONObject(i);
                                mahasiswa = new Mahasiswa(object1.getString("nama"),object1.getString("alamat"),object1.getString("noTelpon"),object1.getString("nim"),object1.getString("jenisKelamin"));
                                mahasiswas.add(mahasiswa);
                            }
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        error.printStackTrace();
                        Toast.makeText(MahasiswaView.this, "erroring: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                try {
                    //Adding parameters to request


                    //returning parameter
                    return params;
                } catch (Exception e) {
                    Toast.makeText(MahasiswaView.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    return params;
                }
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
