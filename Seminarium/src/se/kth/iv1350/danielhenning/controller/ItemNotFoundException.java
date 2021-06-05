package se.kth.iv1350.danielhenning.controller;

public class ItemNotFoundException extends Exception{
     /**
     * This exception is thrown when an item is not found.
     * @param msg the exception message.
     */
    ItemNotFoundException(String msg){
        super(msg);
    }

    /**
     * This exception is thrown when an item is not found.
     * @param msg the exception message. 
     * @param cause the exception cause.
     */
    ItemNotFoundException(String msg, Exception cause){
        super(msg, cause);
    }
}
