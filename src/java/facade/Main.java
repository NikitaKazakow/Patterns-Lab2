package facade;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Facade facade = new Facade();
        facade.show(primaryStage);
        facade.animate();
    }
}
