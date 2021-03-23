package decorator;

import decorator.exception.DuplicateModelNameException;
import decorator.exception.NoSuchModelNameException;

public class SynchronizedTransport implements Transport {

    private final Transport transport;

    public SynchronizedTransport(Transport transport) {
        this.transport = transport;
    }

    @Override
    public synchronized String getBrand() {
        return transport.getBrand();
    }

    @Override
    public synchronized void setBrand(String brand) {
        transport.setBrand(brand);
    }

    @Override
    public synchronized void setModelName(String modelName, String newModelName) throws NoSuchModelNameException, DuplicateModelNameException {
        transport.setModelName(modelName, newModelName);
    }

    @Override
    public synchronized String[] getModelsArray() {
        return transport.getModelsArray();
    }

    @Override
    public synchronized double getPriceByModelName(String modelName) throws NoSuchModelNameException {
        return transport.getPriceByModelName(modelName);
    }

    @Override
    public synchronized void setPriceByModelName(double price, String modelName) throws NoSuchModelNameException {
        transport.setPriceByModelName(price, modelName);
    }

    @Override
    public synchronized double[] getPrices() {
        return transport.getPrices();
    }

    @Override
    public synchronized void addModelNameAndPrice(String modelName, double price) throws DuplicateModelNameException {
        transport.addModelNameAndPrice(modelName, price);
    }

    @Override
    public synchronized void deleteModel(String modelName) throws NoSuchModelNameException {
        transport.deleteModel(modelName);
    }

    @Override
    public synchronized int getModelsCount() {
        return transport.getModelsCount();
    }

    @Override
    public synchronized Transport clone() throws CloneNotSupportedException {
        return transport.clone();
    }
}
