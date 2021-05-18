package se.kth.iv1350.danielhenning.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExceptionLoggerTest {
    private ExceptionLogger logger;


    @BeforeEach
    public void startUp(){
        logger = new ExceptionLogger();
    }

    @AfterEach
    public void tearDown(){
        logger=null;
    }

/**
 * Har inte kommit på några meningsfulla tester till denna klass men av allt att dömma loggar den det den skall.
 */
    
}
