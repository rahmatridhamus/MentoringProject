package com.example.rahmatridham.mentoringproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rahmatridham.mentoringproject.Model.Mahasiswa;

public class DetailProfil extends AppCompatActivity {
    ImageView gambarJK, call, message;
    TextView nama, nim;
    Mahasiswa mahasiswa;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profil);

        gambarJK = (ImageView) findViewById(R.id.imageViewGambar);
        nama = (TextView) findViewById(R.id.textViewNamaLengkap);
        nim = (TextView) findViewById(R.id.textViewNim);
        call = (ImageView) findViewById(R.id.imageViewCall);
        message = (ImageView) findViewById(R.id.imageViewMessage);
        this.setFinishOnTouchOutside(true);

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            mahasiswa = new Mahasiswa((String) b.get("NamaLengkap"),
                    (String) b.get("Alamat"),
                    (String) b.get("NoTelp"),
                    (String) b.get("Nim"),
                    (String) b.get("JenisKelamin"));
        }

        if (mahasiswa.getJenisKelamin().equals("L")) {
            gambarJK.setImageResource(R.drawable.man);
        } else {
            gambarJK.setImageResource(R.drawable.woman);
        }

        nama.setText(mahasiswa.getNama());
        nim.setText(mahasiswa.getNIM());

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "tel:" + mahasiswa.getNoTelpon();
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
                if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", mahasiswa.getNoTelpon());
                smsIntent.putExtra("sms_body","Halo coooooy!!!");
                startActivity(smsIntent);
            }
        });


    }
}
