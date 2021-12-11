package org.example;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;

public class EmailMessage {
    SMTPTestServer testServer;
    byte[] messageData;
    String envelopeSender;
    String envelopeReceiver;

    public EmailMessage(SMTPTestServer testServer, String sender, String receiver, byte[] messageData) {
        this.testServer = testServer;
        this.envelopeSender = sender;
        this.envelopeReceiver = receiver;
        this.messageData = messageData;
    }

    public MimeMessage getMimeMessage() throws MessagingException {
        return new MimeMessage(this.testServer.getSession(), new ByteArrayInputStream(this.messageData));
    }

    /**
     * Get's the raw message DATA.
     */
    public byte[] getData() {
        return this.messageData;
    }

    /**
     * Get's the RCPT TO:
     */
    public String getEnvelopeReceiver() {
        return this.envelopeReceiver;
    }

    /**
     * Get's the MAIL FROM:
     */
    public String getEnvelopeSender() {
        return this.envelopeSender;
    }

    /**
     * Dumps the rough contents of the message for debugging purposes
     */
    public void dumpMessage(PrintStream out) throws MessagingException {
        out.println("===== Dumping message =====");

        out.println("Envelope sender: " + this.getEnvelopeSender());
        out.println("Envelope recipient: " + this.getEnvelopeReceiver());

        // It should all be convertible with ascii or utf8
        String content = new String(this.getData());
        out.println(content);

        out.println("===== End message dump =====");
    }

    /**
     * Implementation of toString()
     *
     * @return getData() as a string or an empty string if getData is null
     */
    @Override
    public String toString() {
        if (this.getData() == null)
            return "";

        return new String(this.getData());
    }
}
