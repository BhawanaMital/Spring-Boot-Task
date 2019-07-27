package com.stackroute.Muzixapp;

import com.stackroute.Muzixapp.repository.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;



@Component
@PropertySource("classpath:application.properties")
public class ApplicationStart implements ApplicationListener<ApplicationReadyEvent> , CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationStart.class);
    @Autowired
    private TrackRepository trackRepository;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        //trackRepository.save(new Track(1, "bhaw", "AVERAGE"));
        System.out.printf("ApplicationEvent");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Comment line listener");
        //trackRepository.save(new Track(2, "moup", "good"));
        trackRepository.findAll().forEach((track) -> {
            logger.info("{}", track);
        });
    }
}


