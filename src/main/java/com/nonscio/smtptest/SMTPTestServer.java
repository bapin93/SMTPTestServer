package org.example;

import org.subethamail.smtp.TooMuchDataException;
import org.subethamail.smtp.helper.SimpleMessageListener;
import org.subethamail.smtp.helper.SimpleMessageListenerAdapter;
import org.subethamail.smtp.server.SMTPServer;

import javax.mail.Session;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SMTPTestServer implements SimpleMessageListener {

    private SMTPServer smtpServer;

    public SMTPTestServer() {
        smtpServer = new SMTPServer(new SimpleMessageListenerAdapter(this));
    }

    public void start() {
        smtpServer.start();
    }

    protected Session getSession() {
        return Session.getDefaultInstance(new Properties());
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
        while ((current = data.read()) >= 0)
        {
            out.write(current);
        }

        byte[] bytes = out.toByteArray();

        EmailMessage message = new EmailMessage(this, from, recipient, bytes);

        System.out.println(message.toString());
    }
}
