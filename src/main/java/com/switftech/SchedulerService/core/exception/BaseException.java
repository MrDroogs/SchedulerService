package com.switftech.SchedulerService.core.exception;

import org.springframework.http.HttpStatus;

public interface BaseException {

    /**
     * Gets code.
     *
     * @return the code
     */
    String getCode();

    /**
     * Gets http status.
     *
     * @return the http status
     */
    HttpStatus getHttpStatus();
}

