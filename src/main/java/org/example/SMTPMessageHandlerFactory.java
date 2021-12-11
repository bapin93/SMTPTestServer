package org.example;

import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;

public class SMTPMessageHandlerFactory implements MessageHandlerFactory {
    @Override
    public MessageHandler create(MessageContext ctx) {
        return null;
    }
}
