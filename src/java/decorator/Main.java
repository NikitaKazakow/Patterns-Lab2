package decorator;

import decorator.exception.DuplicateModelNameException;
import decorator.impl.CarFactory;

public class Main {
    public static void main(String[] args) {
        try {
            TransportManager.setFactory(new CarFactory());
            Transport transport = TransportManager.createInstance("Mitsubishi", 1);

            SynchronizedTransport synchronizedTransport = new SynchronizedTransport(TransportManager.synchronizedTransport(transport));
            synchronizedTransport.addModelNameAndPrice("DecoratorTestModel", 1250000);

            TransportManager.printModels(transport);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }
    }
}
