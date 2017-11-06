package br.ifes.les.application;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class App {

    private static Log logger = LogFactory.getLog(App.class);

    public static void main (String[] args){

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("/HelloWorldDemo.xml",App.class);

        MessageChannel inputChannel = context.getBean("inputChannel", MessageChannel.class);

        PollableChannel outputChannel = context.getBean("outputChannel", PollableChannel.class);

        inputChannel.send(new GenericMessage<String>("World Mundo da Integração"));

        logger.info("==> HelloWorldDemo: " + outputChannel.receive(0).getPayload());

        context.close();
    }
}
