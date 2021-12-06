package com.cooperativism.voting.exception;

public class ScheduleNotFound extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ScheduleNotFound(String msg) {
        super(msg);
    }

    public ScheduleNotFound(String msg, Throwable cause) {
        super(msg, cause);
    }


}
