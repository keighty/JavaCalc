package com.keighty;

/**
 * Created by kleonard on 2/27/17.
 */
public class InvalidInputException extends Throwable {
    private String msg;
    public InvalidInputException(String s) {
        this.msg = s;
    }

    public String toString() {
        return msg;
    }
}
