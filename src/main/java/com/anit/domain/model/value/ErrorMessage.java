package com.anit.domain.model.value;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author anit
 */
public class ErrorMessage extends StringValue {
    protected ErrorMessage(String value) {
        super(value);
    }

    public static ErrorMessage of(String value) {
        return new ErrorMessage(value);
    }

    public static ErrorMessage of(Throwable ex) {
        return new ErrorMessage(convertToStringFrom(ex));
    }

    public static String convertToStringFrom(Throwable ex) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
}
