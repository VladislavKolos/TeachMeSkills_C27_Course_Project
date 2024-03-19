package com.teachmeskills.course_project.session;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Class contains two private fields: "accessToken" and "expDate"
 * contains constructor
 * boolean method "isSessionAlive" - checks if the session is active
 * methods "setAccessToken" and "setExpDate" - set the access token and session end date, which determine whether the session is active.
 */
public class Session {
    private String accessToken;
    private Date expDate;

    public Session() {
        setAccessToken();
        setExpDate();
    }

    public boolean isSessionAlive() {
        return this.accessToken.length() == 10 && this.expDate.after(new Date());
    }

    private void setAccessToken() {
        String symbols = "abcdefghijklmnopqrstuvwxyz0123456789";

        this.accessToken = new Random().ints(10, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private void setExpDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 1);

        this.expDate = calendar.getTime();
    }
}
