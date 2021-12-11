package com.nonscio.smtptest;

import org.subethamail.smtp.TooMuchDataException;
import org.subethamail.smtp.helper.SimpleMessageListener;
import org.subethamail.smtp.helper.SimpleMessageListenerAdapter;
import org.subethamail.smtp.server.SMTPServer;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SMTPTestServer implements SimpleMessageListener {

    //=========================================================================
    // VARIABLES
    //=========================================================================

    private SMTPServer smtpServer;

    //=========================================================================
    // CONSTRUCTORS
    //=========================================================================

    /**
     * SMTPTestServer constructor
     */
    public SMTPTestServer() {
        smtpServer = new SMTPServer(new SimpleMessageListenerAdapter(this));
    }

    //=========================================================================
    // PUBLIC METHODS
    //=========================================================================

    /**
     * Starts the SMTPServer
     */
    public void start() {
        smtpServer.start();
    }

    @Override
    public boolean accept(String from, String recipient) {
        return true;
    }

    @Override
    public void deliver(String from, String recipient, InputStream data) throws TooMuchDataException, IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        data = new BufferedInputStream(data);

        int current;
        while ((current = data.read()) >= 0) {
            out.write(current);
        }

        byte[] bytes = out.toByteArray();

        EmailMessage message = new EmailMessage(this, from, recipient, bytes);

        try {
            message.dumpMessage(System.out);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    //=========================================================================
    // PROTECTED METHODS
    //=========================================================================

    /**
     * Generates a JavaMail Session
     * @return the JavaMail Session
     */
    protected Session getSession() {
        return Session.getDefaultInstance(new Properties());
    }
}
