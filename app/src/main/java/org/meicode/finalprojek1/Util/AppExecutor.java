package org.meicode.finalprojek1.Util;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutor {

    private static final Object LOCK = new Object();
    private static AppExecutor sInstance;
    private final Executor diskIO;
    private final Executor mainThread;
    private final Executor networkIO;

    public AppExecutor(Executor diskIO, Executor mainThread, Executor networkIO) {
        this.diskIO = diskIO;
        this.mainThread = mainThread;
        this.networkIO = networkIO;
    }

    public static AppExecutor getInstance(){
        if(sInstance == null){
            synchronized (LOCK) {
                sInstance = new AppExecutor(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor());
            }
        }
        return sInstance;
    }

    public Executor DiskIO() {
        return diskIO;
    }

    public Executor mainThread(){
        return mainThread;
    }

    public Executor networkIO(){
        return networkIO;
    }

    public static class MainThreadExecutor implements Executor{
        private Handler mainThreadExecutor = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mainThreadExecutor.post(command);
        }
    }
}
