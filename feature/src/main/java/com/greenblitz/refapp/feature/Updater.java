package com.greenblitz.refapp.feature;

public class Updater extends Thread {
    private String name;
    private Thread thread;
    private MainActivity ma;

    public Updater(MainActivity ma, String name){
        this.ma = ma;
        this.name = name;
        System.out.println("updater have been created");
    }

    @Override
    public void run() {
        while (ma.getUpdating()) {
            try {
                System.out.println("updater is working");
                ma.main();
                Thread.sleep(1000);
            } catch (InterruptedException x) {
                x.printStackTrace();
            }
        }
    }

    public void start(){
        if(thread == null){
            thread = new Thread(this, name);
            thread.start();
            System.out.println("updater start working");
        }
    }

}
