package se.kth.iv1350.danielhenning.integration;
/**
 * Thrown when program fails to connect to inventory database.
 */
public class CouldNotConnectToServerException extends RuntimeException{

    /**
     * Create a new instance of throw with secified message.
     * @param msg the exception message.
     */
    CouldNotConnectToServerException(String msg){
        super(msg);
    }
    /**
     * Create a new instance of throw with secified message and cause.
     * @param msg the exception message. 
     * @param cause the exception cause.
     */
    CouldNotConnectToServerException(String msg, Exception cause){
        super(msg, cause);
    }
}
