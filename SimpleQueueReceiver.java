package jms;
import javax.jms.*;
import javax.naming.*;
public class SimpleQueueReceiver {

public static void main(String[] args) {
    String                  queueName = null;
    Context                 jndiContext = null;
    QueueConnectionFactory  queueConnectionFactory = null;
    QueueConnection         queueConnection = null;
    QueueSession            queueSession = null;
    Queue                   queue = null;
    QueueSender             queueSender = null;
    TextMessage             message = null;
   

/*
         * Read queue name from command line and display it.
         */
        if (args.length != 1) {
            System.out.println("Usage: java " +
                "SimpleQueueReceiver <queue-name>");
            System.exit(1);
        }
        queueName = new String(args[0]);
        System.out.println("Queue name is " + queueName);

/* 
         * Create a JNDI API InitialContext object if none exists
         * yet.
         */
/*
         * Read queue name from command line and display it.
         */
        if (args.length != 1) {
            System.out.println("Usage: java " +
                "SimpleQueueReceiver <queue-name>");
            System.exit(1);
        }
        queueName = new String(args[0]);
        System.out.println("Queue name is " + queueName);

/*
         * Create connection.
         * Create session from connection; false means session is
         * not transacted.
         * Create receiver, then start message delivery.
         * Receive all text messages from queue until
         * a non-text message is received indicating end of
         * message stream.
         * Close connection.
         */
        try {
            queueConnection = 
                queueConnectionFactory.createQueueConnection();
            queueSession = queueConnection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
            queueReceiver = queueSession.createReceiver(queue);
            queueConnection.start();
while (true) {
                Message m = queueReceiver.receive(1);
                if (m != null) {
                    if (m instanceof TextMessage) {
                        message = (TextMessage) m;
                        System.out.println("Reading message: " +
                            message.getText());
                    } else {
                        break;
                    }
                }
            }

