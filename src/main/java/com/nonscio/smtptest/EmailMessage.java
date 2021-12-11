package com.nonscio.smtptest;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;

public class EmailMessage {

    //=========================================================================
    // VARIABLES
    //=========================================================================

    private SMTPTestServer testServer;
    private String envelopeSender;
    private String envelopeReceiver;
    private byte[] messageData;

    //=========================================================================
    // CONSTRUCTORS
    //=========================================================================

    /**
     * EmailMessage constructor
     * @param testServer the SMTPTestServer that is delivering this message
     * @param envelopeSender the sender address
     * @param envelopeReceiver the receiver address
     * @param messageData the message data
     */
    public EmailMessage(SMTPTestServer testServer, String envelopeSender, String envelopeReceiver, byte[] messageData) {
        this.testServer = testServer;
        this.envelopeSender = envelopeSender;
        this.envelopeReceiver = envelopeReceiver;
        this.messageData = messageData;
    }

    //=========================================================================
    // PUBLIC METHODS
    //=========================================================================

    /**
     * Generates a JavaMail MimeMessage
     * @return a JavaMail MimeMessage
     * @throws MessagingException
     */
    public MimeMessage getMimeMessage() throws MessagingException {
        return new MimeMessage(this.testServer.getSession(), new ByteArrayInputStream(this.messageData));
    }

    /**
     * Gets the raw message data
     * @return messageData byte array
     */
    public byte[] getData() {
        return this.messageData;
    }

    /**
     * Gets the receiver address
     * @return envelopeReceiver address
     */
    public String getEnvelopeReceiver() {
        return this.envelopeReceiver;
    }

    /**
     * Gets the sender address
     * @return envolopeSender address
     */
    public String getEnvelopeSender() {
        return this.envelopeSender;
    }

    /**
     * Dumps the message data out in a formatted manner
     * @param out the PrintStream to dump the data
     * @throws MessagingException
     */
    public void dumpMessage(PrintStream out) throws MessagingException {
        out.println("===== Dumping message =====");
        out.println("Envelope sender: " + this.getEnvelopeSender());
        out.println("Envelope recipient: " + this.getEnvelopeReceiver());
        String content = new String(this.getData());
        out.println(content);
        out.println("===== End message dump =====");
    }
}
