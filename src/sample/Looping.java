package sample;

import javafx.application.Platform;

public class Looping extends Thread {

    private Main m;
    public boolean running=true;

    public Looping(Main m) {
        this.m = m;
    }

    public void run(){
        while(running){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    m.gogo();
                }
            });

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
