package com.cooperativism.voting.exception;

public class ScheduleNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ScheduleNotFoundException(String msg) {
        super(msg);
    }

    public ScheduleNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }


}
