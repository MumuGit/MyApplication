package com.mu.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List list=new ArrayList();
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add(Environment.getExternalStorageDirectory()+"/DCIM/IMG_169258176.jpg");
        list.add(Environment.getExternalStorageDirectory()+"/DCIM/IMG_1062289844.jpg");

        button= (Button) findViewById(R.id.bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestTakePhotoPermission();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImagPagerUtil imagPagerUtil= new ImagPagerUtil(MainActivity.this,list);
        Log.i("muqi"," onResume");
        imagPagerUtil.show();
    }

    public  void requestTakePhotoPermission(){
        String[] ps = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED)) {
            Log.i("muqi"," requestTakePhotoPermission"+(ActivityCompat.
                    checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED ));
            Log.i("muqi"," requestTakePhotoPermission"+(ActivityCompat.
                    checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED ));

            requestPermissions(ps, 0);
            Log.i("muqi"," requestTakePhotoPermission");


        }
    }
}
