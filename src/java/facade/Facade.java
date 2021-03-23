package facade;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class Facade implements CarListener {

    private final Car car;
    private final TrafficLight trafficLight;

    private final SwitchState switchState;

    private final PathTransition pathTransition;

    public Facade() {

        trafficLight = new TrafficLight();

        car = new Car(1000);
        car.addCarListener(this);

        switchState = new SwitchState();

        Path path = new Path();
        path.getElements().add(new MoveTo(-100, 250));
        path.getElements().add(new LineTo(2100, 250));

        pathTransition = new PathTransition();
        pathTransition.setDuration(new Duration(4000));
        pathTransition.setPath(path);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(false);
    }

    public void show(Stage primaryStage) {

        SVGPath carPath = car.getPath();
        SVGPath trafficLightPath = trafficLight.getPath();
        SVGPath[] lamps = trafficLight.getLamps();

        Pane root = new Pane(carPath, trafficLightPath);
        root.getChildren().addAll(lamps);
        root.setPrefWidth(1800);
        root.setPrefHeight(600);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void animate() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(switchState, 0, trafficLight.getInterval());

        pathTransition.setNode(car.getPath());
        pathTransition.play();
    }

    @Override
    public void beforeTrafficLight() {
        if (switchState.isStop) {
            pathTransition.pause();
        }
        else {
            pathTransition.play();
        }
    }

    private class SwitchState extends TimerTask {

        private boolean isStop = false;

        @Override
        public void run() {
            try {
                if (isStop) {
                    trafficLight.fromRedToGreen();
                }
                else {
                    trafficLight.fromGreenToRed();
                }
                isStop = !isStop;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
