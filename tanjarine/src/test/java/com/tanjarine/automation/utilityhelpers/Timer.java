package com.tanjarine.automation.utilityhelpers;

/**
 * Created by Eugene Polschikov on 19.05.2014.
 * Timer.java class  is purpose to time  activities  being performed by a selenium tets
 */

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
//import java.util.logging.Logger;

public class Timer {
    // assumes the current class is called logger
    //    private final static Logger logger = Logger.getLogger(Timer.class.getName());
    private long startTime = 0;
    private long endTime = 0;
    //    private String LOG_FILE;
    //    private final static Logger logger = LoggerFactory.getLogger(Timer.class);
    /*
        public Timer(String aLOG_FILE) {
            LOG_FILE = aLOG_FILE;
        }
    */
    public Timer() {}

    public void start() throws IOException {
        this.startTime = System.currentTimeMillis();
        // logger.Log(LOG_FILE, "TIMER START -> "+this.dateParser(this.startTime));
    }

    public void end() throws IOException {
        this.endTime = System.currentTimeMillis();
        // logger.Log(LOG_FILE, "TIMER END -> "+this.dateParser(this.endTime));
    }

    public Date getStartTime() throws IOException {
        // logger.Log(LOG_FILE, "GET START -> "+this.dateParser(this.startTime));
        return this.dateParser(this.startTime);
    }

    public Date getEndTime() throws IOException {
        // logger.Log(LOG_FILE, "GET END -> "+this.dateParser(this.endTime));
        return this.dateParser(this.endTime);
    }

    public long getTotalTime() throws IOException {
        long deltaTime = this.endTime - this.startTime;
        // logger.Log(LOG_FILE, "GET TOTAL -> "+deltaTime+" ms");
        return deltaTime;
    }

    public Date dateParser(long unixTime) {
        Date date = new Date ();
        date.setTime(unixTime);
        return date;
    }



    /**
     * method generating timeStamp
     *
     * @return
     */
    public static String getCurrentTimestamp() {
        java.util.Date date = new java.util.Date();
        return (new Timestamp(date.getTime())).toString();

    }
}

