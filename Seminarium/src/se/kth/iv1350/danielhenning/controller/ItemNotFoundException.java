package se.kth.iv1350.danielhenning.controller;

public class ItemNotFoundException extends RuntimeException{
      /**
     * Create a new instance of throw with secified message.
     * @param msg the exception message.
     */
    ItemNotFoundException(String msg){
        super(msg);
    }

    /**
     * Create a new instance of throw with secified message and cause.
     * @param msg the exception message. 
     * @param cause the exception cause.
     */
    ItemNotFoundException(String msg, Exception cause){
        super(msg, cause);
    }
}
