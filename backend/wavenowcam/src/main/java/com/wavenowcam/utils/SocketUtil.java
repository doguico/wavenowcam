package com.wavenowcam.utils;

import com.wavenowcam.exceptions.SocketException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author guidocorazza
 */
public class SocketUtil {

    public static InputStream sendRequestToSocket(String host, int port, String endpoint) throws SocketException {
        try {
            Socket socket = new Socket(host, port);

            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.print("GET " + endpoint + " HTTP/1.1\r\n");
            pw.print("Host: " + host + "\r\n\r\n");
            pw.println("");
            pw.flush();

            return socket.getInputStream();
        } catch (IOException ex) {
            throw new SocketException(SocketException.CONNECTION_ERROR);
        }
    }

    public static void socketResponseToFile(InputStream is, String path, String type) throws SocketException {
        try {
            OutputStream os = new FileOutputStream(path + "." + type);
            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        } catch (IOException ex) {
            throw new SocketException(SocketException.READ_ERROR);
        }
    }
}
