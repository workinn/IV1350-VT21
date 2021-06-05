package se.kth.iv1350.danielhenning.integration;
public class ItemDoesNotExistException extends Exception {
    /**
     * This exception is thrown when an item is not found in a database.
     * @param msg the exception message.
     */
    ItemDoesNotExistException(String msg){
        super(msg);
    }

    /**
     * This exception is thrown when an item is not found in a database.
     * @param msg the exception message. 
     * @param cause the exception cause.
     */
    ItemDoesNotExistException(String msg, Exception cause){
        super(msg, cause);
    }
}
