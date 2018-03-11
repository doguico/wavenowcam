package com.wavenowcam.exceptions;

/**
 *
 * @author guidocorazza
 */
public class SocketException extends Exception {

    private static final String MSG_ERROR = "Error al conectarse con el socket";
    public static final String CONNECTION_ERROR = "Error al conectarse con el socket";
    public static final String READ_ERROR = "Error al los datos del socket";
    
    public SocketException() {
        super(MSG_ERROR);
    }

    public SocketException(String msg) {
        super(msg);
    }
}
