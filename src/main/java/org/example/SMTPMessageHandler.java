package org.example;

import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.RejectException;
import org.subethamail.smtp.TooMuchDataException;

import java.io.IOException;
import java.io.InputStream;

public class SMTPMessageHandler implements MessageHandler {
    @Override
    public void from(String from) throws RejectException {

    }

    @Override
    public void recipient(String recipient) throws RejectException {

    }

    @Override
    public void data(InputStream data) throws RejectException, TooMuchDataException, IOException {

    }

    @Override
    public void done() {

    }
}
