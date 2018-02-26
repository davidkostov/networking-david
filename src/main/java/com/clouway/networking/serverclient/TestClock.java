package com.clouway.networking.serverclient;

import java.util.Calendar;
import java.util.Date;

/**
 * @author David Kostov (david.kostov.cw@gmail.com)
 */
public class TestClock implements Clock{
    public Date now(){
        return Calendar.getInstance().getTime();
    }
}
