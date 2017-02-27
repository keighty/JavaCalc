package com.keighty;

/**
 * Created by kleonard on 2/27/17.
 */
public class InvalidOperatorException extends Throwable {
    private String msg;

    public InvalidOperatorException(String msg) {
        this.msg = msg;
    }

    public String toString() {
        return msg;
    }
}
