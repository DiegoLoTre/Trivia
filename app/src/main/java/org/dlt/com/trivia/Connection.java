package org.dlt.com.trivia;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Diego
 */
class Connection {

    private Socket socket;
    private BufferedReader bufferIn;
    private PrintWriter bufferOut;

    Connection() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    void setUpConnection() {
        try {
            socket = new Socket("192.168.1.109",5005);
            bufferIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String receiveData() {
        String data = "";
        try {
            data = bufferIn.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return data;
    }

    void sendData(String data) {
        bufferOut.println(data);
    }

    void closeConnection() {
        try {
            socket.close();
            bufferOut.close();
            bufferIn.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
