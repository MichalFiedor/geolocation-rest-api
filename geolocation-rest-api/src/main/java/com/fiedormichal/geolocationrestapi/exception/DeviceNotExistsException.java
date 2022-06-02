package com.fiedormichal.geolocationrestapi.exception;

public class DeviceNotExistsException extends RuntimeException{
    public DeviceNotExistsException(String message) {
        super(message);
    }
}
