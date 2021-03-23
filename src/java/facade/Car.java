package facade;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Getter
public class Car extends Region {

    private SVGPath path;

    private final List<CarListener> listeners = new ArrayList<>();

    public Car(double trafficLightX) {
        Scale scale = new Scale();
        scale.setX(0.5);
        scale.setY(0.5);
        scale.setPivotX(0);
        scale.setPivotY(0);

        Translate translate = new Translate();
        translate.setX(50);
        translate.setY(750);

        try {
            path = new SVGPath();
            path.setContent(Files.readString(Path.of("src/resources/car.txt")));
            path.setFill(Color.BLACK);
            path.getTransforms().addAll(scale, translate);
        } catch (IOException e) {
            e.printStackTrace();
        }

        runScheduleTask(trafficLightX);
    }

    private void runScheduleTask(double trafficLightX) {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new CheckTrafficLightState(trafficLightX), 100, 50);
    }

    public void addCarListener(CarListener listener) {
        this.listeners.add(listener);
    }

    private class CheckTrafficLightState extends TimerTask {

        private final double trafficLightX;

        public CheckTrafficLightState(double trafficLightX) {
            super();
            this.trafficLightX = trafficLightX;
        }

        @Override
        public void run() {
            double x = path.getTranslateX();
            if (x >= trafficLightX - 150 && x < trafficLightX - 100 ) {
                for (CarListener listener : listeners) {
                    listener.beforeTrafficLight();
                }
            }
        }
    }
}
