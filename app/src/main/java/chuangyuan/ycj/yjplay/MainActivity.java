package chuangyuan.ycj.yjplay;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import chuangyuan.ycj.yjplay.barrage.MainBarrageLayoutActivity;
import chuangyuan.ycj.yjplay.custom.MainCustomLayoutActivity;
import chuangyuan.ycj.yjplay.defaults.GuangGaoPlayerdActivity;
import chuangyuan.ycj.yjplay.defaults.MainDetailedActivity;
import chuangyuan.ycj.yjplay.fragment.ListFragmentActivity;
import chuangyuan.ycj.yjplay.fragment.ViewPagerActivity;
import chuangyuan.ycj.yjplay.ima.ImaPlayerActivity;
import chuangyuan.ycj.yjplay.media.MainCustomMediaActivity;
import chuangyuan.ycj.yjplay.offline.OfficeDetailedActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("sds", Environment.getExternalStorageDirectory().getAbsolutePath() + "/4k1.mp4");
        findViewById(R.id.button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, MainDetailedActivity.class);
                        startActivity(intent);
                    }
                });
        findViewById(R.id.button2)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, MainListActivity.class);
                        startActivity(intent);
                    }
                });
        findViewById(R.id.button3)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, MainCustomLayoutActivity.class);
                        String uri;
                        if (Build.VERSION.SDK_INT < 23) {//低版本不支持高分辨视频
                            uri = getString(R.string.uri_test);
                        } else {
                            //1080 视频
                            uri = getString(R.string.uri_test);
                        }
                        intent.putExtra("uri", uri);
                        startActivity(intent);
                    }
                });
        findViewById(R.id.button4)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, MainCustomLayoutActivity.class);
                        String uri;
                        if (Build.VERSION.SDK_INT < 23) {//低版本不支持高分辨视频
                            uri = getString(R.string.uri_test_3);
                        } else {
                            //1080 视频
                            uri = getString(R.string.uri_test_h);
                        }
                        intent.putExtra("uri", uri);
                        intent.putExtra("isOnclick", true);
                        startActivity(intent);
                    }
                });
        findViewById(R.id.button5)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, GuangGaoPlayerdActivity.class);
                        intent.putExtra("isOnclick", true);
                        startActivity(intent);
                    }
                });
        findViewById(R.id.button6)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, MainCustomMediaActivity.class);
                        String uri;
                        if (Build.VERSION.SDK_INT < 23) {//低版本不支持高分辨视频
                            uri = getString(R.string.uri_test_3);
                        } else {
                            //1080 视频
                            uri = getString(R.string.uri_test_h);
                        }
                        intent.putExtra("uri", uri);
                        intent.putExtra("isOnclick", true);
                        startActivity(intent);
                    }
                });
        findViewById(R.id.button9)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, OfficeDetailedActivity.class);
                        startActivity(intent);
                    }
                });
        findViewById(R.id.button10)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, ImaPlayerActivity.class);
                        startActivity(intent);
                    }
                });
        findViewById(R.id.button11)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, ListFragmentActivity.class);
                        startActivity(intent);
                    }
                });
        findViewById(R.id.button12)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                        startActivity(intent);
                    }
                });
        findViewById(R.id.button13)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, MainBarrageLayoutActivity.class);
                        startActivity(intent);
                    }
                });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

            if (checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, android.os.Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }


        }
    }
}
