package com.example.rahmatridham.mentoringproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rahmatridham.mentoringproject.Model.Mahasiswa;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormPage extends AppCompatActivity {

    EditText nama,alamat,nim,noTelp;
    RadioButton laki,cewe;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_page);
        nama = (EditText) findViewById(R.id.editText3Nama);
        alamat = (EditText) findViewById(R.id.editText2Alamat);
        nim = (EditText) findViewById(R.id.editText4Nim);
        noTelp = (EditText) findViewById(R.id.editText5NoTelp);
        laki = (RadioButton) findViewById(R.id.radioButton2Laki);
        cewe = (RadioButton) findViewById(R.id.radioButtonCewe);
        save = (Button) findViewById(R.id.buttonSave);

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Mahasiswa mahasiswa;
                if(nim.length()==10 && alamat.length()>=4 && nama.length()>=5) {
                    if (laki.isChecked()) {
                        mahasiswa = new Mahasiswa(nama.getText().toString(), alamat.getText().toString(), noTelp.getText().toString(), nim.getText().toString(), "L");
                        postData("http://api-android.herokuapp.com/mahasiswa", mahasiswa);
                        Toast.makeText(FormPage.this, "" + mahasiswa.toString(), Toast.LENGTH_SHORT).show();

                    } else if (cewe.isChecked()) {
                        mahasiswa = new Mahasiswa(nama.getText().toString(), alamat.getText().toString(), noTelp.getText().toString(), nim.getText().toString(), "P");
                        postData("http://api-android.herokuapp.com/mahasiswa", mahasiswa);
                        Toast.makeText(FormPage.this, "" + mahasiswa.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FormPage.this, "Lengkapi Form", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(FormPage.this, "Harus pas 10 bos", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(FormPage.this,MahasiswaView.class));
        finish();
    }

    private void postData(String url, final Mahasiswa mahasiswa) {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(FormPage.this, ""+response, Toast.LENGTH_SHORT).show();
                        String status;
                        try {
                            JSONObject object = new JSONObject(response);
                            status = object.getString("status");
                            Toast.makeText(FormPage.this, status, Toast.LENGTH_SHORT).show();

                            if(status.equals("success")) {
                                nama.setText("");
                                alamat.setText("");
                                nim.setText("");
                                noTelp.setText("");
                                cewe.setChecked(false);
                                laki.setChecked(false);
                                Toast.makeText(FormPage.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(FormPage.this,MahasiswaView.class));
                                finish();
                            }else{
                                Toast.makeText(FormPage.this, "Gagal Om", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(FormPage.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        error.printStackTrace();
                        Toast.makeText(FormPage.this, "erroring: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                try {
                    //Adding parameters to request
                    params.put("nama", mahasiswa.getNama());
                    params.put("nim", mahasiswa.getNIM());
                    params.put("jenisKelamin", mahasiswa.getJenisKelamin());
                    params.put("alamat", mahasiswa.getAlamat());
                    params.put("noTelpon", mahasiswa.getNoTelpon());
                    //returning parameter
                    return params;
                } catch (Exception e) {
                    Toast.makeText(FormPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    return params;
                }
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
