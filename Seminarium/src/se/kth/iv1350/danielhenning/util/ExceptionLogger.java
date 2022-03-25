package se.kth.iv1350.danielhenning.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionLogger {
    private PrintWriter exceptionLog;
/**
 * Create a class that logs the exception message and information to a file.
 */
    public ExceptionLogger(){
        try {
            exceptionLog = new PrintWriter(new FileWriter("exceptionlog.txt"), true);
        } catch (IOException ex) {
            System.out.println("Could not create logfile.");
            ex.printStackTrace();
        }
    }
    /**
     * Logs the exception to a file.
     * @param exc the exception that is going to be logged to file.
     */
    public void logException(Exception exc){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append(timeStamp);
        logMsgBuilder.append(" Exception Thrown: ");
        logMsgBuilder.append(exc.getMessage());
        exceptionLog.println(logMsgBuilder);
        exc.printStackTrace(exceptionLog);

    }
}
