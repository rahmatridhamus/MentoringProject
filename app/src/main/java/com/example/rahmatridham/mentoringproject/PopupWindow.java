package com.example.rahmatridham.mentoringproject;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rahmatridham.mentoringproject.Model.Mahasiswa;

/**
 * Created by rahmatridham on 10/23/2016.
 */

public class PopupWindow extends android.widget.PopupWindow {
    Context ctx;
    ImageView gambarJK, call, message;
    TextView nama, nim;
    Mahasiswa mahasiswa;
    View popupView;

    public PopupWindow(Context context)
    {
        super(context);

        ctx = context;
        popupView = LayoutInflater.from(context).inflate(R.layout.activity_detail_profil, null);
        setContentView(popupView);

        gambarJK = (ImageView) popupView.findViewById(R.id.imageViewGambar);
        nama = (TextView) popupView.findViewById(R.id.textViewNamaLengkap);
        nim = (TextView) popupView.findViewById(R.id.textViewNim);
        call = (ImageView) popupView.findViewById(R.id.imageViewCall);
        message = (ImageView) popupView.findViewById(R.id.imageViewMessage);

        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);

        // Closes the popup window when touch outside of it - when looses focus
        setOutsideTouchable(true);
        setFocusable(true);

        // Removes default black background
        setBackgroundDrawable(new BitmapDrawable());

//        Intent iin = popupView.getgetIntent();
//        Bundle b = iin.getExtras();
//
//        if (b != null) {
//            mahasiswa = new Mahasiswa((String) b.get("NamaLengkap"),
//                    (String) b.get("Alamat"),
//                    (String) b.get("NoTelp"),
//                    (String) b.get("Nim"),
//                    (String) b.get("JenisKelamin"));
//        }
//
//        if (mahasiswa.getJenisKelamin().equals("L")) {
//            gambarJK.setImageResource(R.drawable.man);
//        } else {
//            gambarJK.setImageResource(R.drawable.woman);
//        }
//
//        nama.setText(mahasiswa.getNama());
//        nim.setText(mahasiswa.getNIM());

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
                popupView.getContext().startActivity(callIntent);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", mahasiswa.getNoTelpon());
                smsIntent.putExtra("sms_body","Halo coooooy!!!");
                popupView.getContext().startActivity(smsIntent);
            }
        });

        // Closes the popup window when touch it
/*     this.setTouchInterceptor(new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                dismiss();
            }
            return true;
        }
    }); */
    } // End constructor

    // Attaches the view to its parent anchor-view at position x and y
    public void show(View anchor, int x, int y)
    {
        showAtLocation(anchor, Gravity.CENTER, x, y);
    }
}
