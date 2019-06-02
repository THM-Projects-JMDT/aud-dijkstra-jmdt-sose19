package visualization;

import javafx.application.Platform;
import sample.Main;

public class Looping extends Thread {

    public boolean running = true;

    public Looping() {}

    public void run(){
        while(running){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    App.getGraphController().next();
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
