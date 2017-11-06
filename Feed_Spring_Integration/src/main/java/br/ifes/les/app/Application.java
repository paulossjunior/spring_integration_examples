package br.ifes.les.app;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;

import com.rometools.rome.feed.synd.SyndEntry;

public class Application {
    @SuppressWarnings("unchecked")
    public static void main (String[] args){

        ConfigurableApplicationContext ac =
                new ClassPathXmlApplicationContext("/context.xml");
        PollableChannel feedChannel = ac.getBean("feedChannel", PollableChannel.class);

        for (int i = 0; i < 10; i++) {
            Message<SyndEntry> message = (Message<SyndEntry>) feedChannel.receive(1000);
            if (message != null){
                System.out.println("==========="+i+"===========");
                SyndEntry entry = message.getPayload();
                System.out.println(entry.getAuthor());
                System.out.println(entry.getPublishedDate());
                System.out.println(entry.getTitle());
                System.out.println(entry.getUri());
                System.out.println(entry.getLink());
                System.out.println("======================");

            }
        }
        ac.close();


    }
}
