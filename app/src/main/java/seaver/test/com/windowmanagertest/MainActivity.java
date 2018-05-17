package seaver.test.com.windowmanagertest;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button test1;
    private Button test2,test3,test4,test5,test6,test7,test8,test9;
    static int i=0;
    ArrayList<View> list;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.d("MainActivity","i的值："+i);
                    switch (i){
                        case 0:
                            list.get(i).setBackgroundColor(Color.BLUE);
                            break;
                        case 1:
                            list.get(i).setBackgroundColor(Color.GRAY);
                            break;
                        case 2:
                            list.get(i).setBackgroundColor(Color.RED);
                            break;
                        case 3:
                            list.get(i).setBackgroundColor(Color.YELLOW);
                            break;
                        case 4:
                            list.get(i).setBackgroundColor(Color.BLACK);
                            break;
                        case 5:
                            list.get(i).setBackgroundColor(-12627531);
                            break;
                        case 6:
                            list.get(i).setBackgroundColor(Color.DKGRAY);
                            break;
                        default:
                            break;
                    }
                    if(i>=7&&i<14){
                        list.get(13-i).setBackgroundColor(Color.WHITE);
                        if(i==13){
                            i=0;
                            break;
                        }
                    }
                    i++;
                    break;
                default:
                    break;
            }

        }
    };
    TimerTask task=new TimerTask() {
        @Override
        public void run() {
            Message message=new Message();
            message.what=1;
            handler.sendMessage(message);
        }
    };
    boolean is;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test1=(Button)findViewById(R.id.test1);
        test2=(Button)findViewById(R.id.test2);
        test3=(Button)findViewById(R.id.test3);
        test4=(Button)findViewById(R.id.test4);
        test5=(Button)findViewById(R.id.test5);
        test6=(Button)findViewById(R.id.test6);
        test7=(Button)findViewById(R.id.test7);
        test8=(Button)findViewById(R.id.test8);
        test9=(Button)findViewById(R.id.test9);
        list=new ArrayList<>();
        list.add(test3);
        list.add(test4);
        list.add(test5);
        list.add(test6);
        list.add(test7);
        list.add(test8);
        list.add(test9);
        is=true;
        test1.setOnClickListener(this);
        test2.setOnClickListener(this);
    }
    public int getColor(View view){
        int color=0;
        Drawable background=view.getBackground();
        if (background instanceof ColorDrawable) {
            ColorDrawable colordDrawable = (ColorDrawable) background;
            color = colordDrawable.getColor();
        }
        return color;
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
            case R.id.test2:
                if(is){
                    is=false;
                    Log.d("MainActivity","开始了跑马灯");
                    timer=new Timer();
                    task=new TimerTask() {
                        @Override
                        public void run() {
                            Message message=new Message();
                            message.what=1;
                            handler.sendMessage(message);
                        }
                    };
                    timer.schedule(task,200,200);
                }else {
                    is=true;
                    timer.cancel();
                }
                break;
            default:
                break;
        }
    }

}
