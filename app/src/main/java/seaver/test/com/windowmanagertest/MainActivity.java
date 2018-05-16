package seaver.test.com.windowmanagertest;

import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button test1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test1=(Button)findViewById(R.id.test1);
        test1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.test1:
                Log.d("MainActivity","版本号："+Build.VERSION.SDK_INT);
                if (Build.VERSION.SDK_INT >= 23) {
                    if(!Settings.canDrawOverlays(MainActivity.this)) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                        Log.e("FirstService","00000000000000000000000000000000000");
                        startActivity(intent);
                        return;
                    }
                }
                Intent intent=new Intent(MainActivity.this,MyService.class);
                startService(intent);
                break;
            default:
                break;
        }
    }

}
