package decorator.exception;

public class ModelPriceOutOfBoundsException extends RuntimeException {
    public ModelPriceOutOfBoundsException(String errorMessage) {
        super(errorMessage);
    }
}
