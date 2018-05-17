package seaver.test.com.windowmanagertest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private View view;
    private Timer timer;
    //public Button test2;
    static int i=0;
    boolean is;
    public MyService() {
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Drawable background=view.getBackground();
                    if (background instanceof ColorDrawable) {
                        ColorDrawable colordDrawable = (ColorDrawable) background;
                        int color = colordDrawable.getColor();
                        if(color==Color.BLUE){
                            view.setBackgroundColor(Color.YELLOW);
                        }else if(color==Color.YELLOW) {
                            view.setBackgroundColor(Color.GREEN);
                        }else if(color==Color.GREEN){
                            view.setBackgroundColor(Color.WHITE);
                        }else if(color==Color.WHITE){
                            view.setBackgroundColor(Color.RED);
                        }else if(color==Color.RED){
                            view.setBackgroundColor(Color.BLACK);
                        }else if(color==Color.BLACK){
                            view.setBackgroundColor(Color.GRAY);
                        }else if(color==Color.GRAY){
                            view.setBackgroundColor(Color.DKGRAY);
                        }else {
                            view.setBackgroundColor(Color.BLUE);
                        }
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };
    TimerTask task=new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        windowManager=(WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        layoutParams=new WindowManager.LayoutParams();
        layoutParams.type=WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        //layoutParams.flags=WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        layoutParams.height=500;
        layoutParams.width=600;
        view= LayoutInflater.from(getBaseContext()).inflate(R.layout.test_layout,null);
        Log.d("MainActivity","在onCreate的View的id"+view.getId());
        windowManager.addView(view,layoutParams);
        is=true;
        timer = new Timer();
        timer.schedule(task,500, 500);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        if(is){
                            Log.d("MainActivity","确认来过暂停这里啊");
                            is=false;
                            timer.cancel();

                        }else {
                            Log.d("MainActivity","确认来过开始这里啊");
                            task=new TimerTask() {
                                @Override
                                public void run() {
                                    Message message = new Message();
                                    message.what = 1;
                                    handler.sendMessage(message);
                                }
                            };
                            is=true;
                            timer=new Timer();
                            timer.schedule(task,500, 500);
                        }
                        break;
                    case MotionEvent.ACTION_MOVE: //手指移动（从手指按下到抬起 move多次执行）
                        break;
                    case MotionEvent.ACTION_UP: //手指抬起
                        break;
                }
                return false;
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }
    /*    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN: //手指按下
                        Drawable background=view.getBackground();
                        if (background instanceof ColorDrawable) {
                            ColorDrawable colordDrawable = (ColorDrawable) background;
                            int color = colordDrawable.getColor();
                            if(color==Color.BLUE){
                                view.setBackgroundColor(Color.YELLOW);
                            }else if(color==Color.YELLOW) {
                                view.setBackgroundColor(Color.GREEN);
                            }else if(color==Color.GREEN){
                                view.setBackgroundColor(Color.WHITE);
                            }else {
                                view.setBackgroundColor(Color.BLUE);
                            }
                        }
                        break;
                    case MotionEvent.ACTION_MOVE: //手指移动（从手指按下到抬起 move多次执行）
                        break;
                    case MotionEvent.ACTION_UP: //手指抬起
                        break;
                }
                *//*i++;
                Log.d("MainActivity","在Touch的次数"+i);
                if(i%2==0){
                    view.setBackgroundColor(Color.YELLOW);
                }else {
                    view.setBackgroundColor(Color.BLUE);
                }*//*

                //Log.d("MainActivity","在Touch的View的id"+view.getId());

                *//*view.setVisibility(View.GONE);
                stopSelf();*//*
                return true;
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","服务被销毁了");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
