package se.kth.iv1350.danielhenning.integration;


public class ItemDoesNotExistException extends RuntimeException {
        /**
     * Create a new instance of throw with secified message.
     * @param msg the exception message.
     */
    ItemDoesNotExistException(String msg){
        super(msg);
    }

    /**
     * Create a new instance of throw with secified message and cause.
     * @param msg the exception message. 
     * @param cause the exception cause.
     */
    ItemDoesNotExistException(String msg, Exception cause){
        super(msg, cause);
    }
}
