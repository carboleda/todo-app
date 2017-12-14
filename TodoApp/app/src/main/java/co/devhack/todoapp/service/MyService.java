package co.devhack.todoapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;

import co.devhack.todoapp.presentation.view.fragment.TodoListFragment;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        execute();
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 1;
                while (count <= 50) {
                    try {
                        count++;
                        Intent intent = new Intent(TodoListFragment.RECEIVER_COUNTER);
                        intent.putExtra("count", count);
                        sendBroadcast(intent);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                stopSelf();
            }
        }).start();
    }
}
