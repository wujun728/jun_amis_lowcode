package cn.kunter.generator.exception;

/**
 * Generator Exception
 * @author nature
 * @version 1.0 2021/7/20
 */
public class GeneratorException extends Exception {

    public GeneratorException(String message) {
        super(message);
    }

    public GeneratorException(Throwable cause) {
        super(cause);
    }

    public GeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

}
