package com.nonscio.smtptest;

/**
 * Main class
 */
public class App {
    public static void main( String[] args ) {
        SMTPTestServer testServer = new SMTPTestServer();
        testServer.start();
    }
}
