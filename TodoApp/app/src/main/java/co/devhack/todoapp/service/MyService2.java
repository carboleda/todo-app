package co.devhack.todoapp.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import co.devhack.todoapp.presentation.view.fragment.TodoListFragment;

public class MyService2 extends IntentService {

    public MyService2() {
        super("MyService2");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int count = 1;
        while (count <= 50) {
            try {
                count++;
                Intent intent2 = new Intent(TodoListFragment.RECEIVER_COUNTER);
                intent2.putExtra("count", count);
                sendBroadcast(intent2);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
