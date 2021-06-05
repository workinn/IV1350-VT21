package se.kth.iv1350.danielhenning.integration;
/**
 * Thrown when program fails to connect to inventory database.
 */
public class CouldNotConnectToServerException extends RuntimeException{

    /**
     * This exception is thrown when a database could not be called.
     * @param msg the exception message.
     */
    CouldNotConnectToServerException(String msg){
        super(msg);
    }
    /**
     * This exception is thrown when a database could not be called.
     * @param msg the exception message. 
     * @param cause the exception cause.
     */
    CouldNotConnectToServerException(String msg, Exception cause){
        super(msg, cause);
    }
}
