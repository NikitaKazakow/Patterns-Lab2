package facade;

import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TrafficLight {

    @Getter
    private SVGPath path;

    @Getter
    private final SVGPath [] lamps = new SVGPath[3];

    @Getter
    private final int interval = 10000;

    public TrafficLight() {

        Scale scale = new Scale();
        scale.setX(0.75);
        scale.setY(0.75);
        scale.setPivotX(0);
        scale.setPivotY(0);

        Translate translate = new Translate();
        translate.setX(1500);
        translate.setY(120);

        try {
            path = new SVGPath();
            path.setContent(Files.readString(Path.of("src/resources/traffic light.txt")));
            path.setFill(Color.BLACK);
            path.getTransforms().addAll(scale, translate);

            Color[] colors = new Color[]{
                    Color.web("#912828"),
                    Color.web("#C1BE30"),
                    Color.web("#28821B")
            };

            for (int i = 0; i < 3; i++) {
                SVGPath lampPath = new SVGPath();
                lampPath.setContent(Files.readString(Path.of("src/resources/lamps/" + (i + 1) + ".txt")));
                lampPath.setFill(colors[i]);
                lampPath.getTransforms().addAll(scale, translate);

                lamps[i] = lampPath;
            }
            path.getLayoutX();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fromRedToGreen() throws InterruptedException {
        turnLampOn(Lamps.YELLOW);
        Thread.sleep(3000);
        turnLampOff(Lamps.RED);
        turnLampOff(Lamps.YELLOW);
        turnLampOn(Lamps.GREEN);
    }

    public void fromGreenToRed() throws InterruptedException {
        turnLampOff(Lamps.GREEN);
        Thread.sleep(500);
        turnLampOn(Lamps.GREEN);
        Thread.sleep(500);
        turnLampOff(Lamps.GREEN);
        Thread.sleep(500);
        turnLampOn(Lamps.GREEN);
        Thread.sleep(500);
        turnLampOff(Lamps.GREEN);
        turnLampOn(Lamps.YELLOW);
        Thread.sleep(2000);
        turnLampOff(Lamps.YELLOW);
        turnLampOn(Lamps.RED);
    }

    private void turnLampOn(Lamps lamp) {
        lamps[lamp.ordinal()].setEffect(new Glow(0.8));
    }

    private void turnLampOff(Lamps lamp) {
        lamps[lamp.ordinal()].setEffect(new Glow(0));
    }
}
