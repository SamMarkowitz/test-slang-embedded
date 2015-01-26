package com.example;

import org.openscore.events.ScoreEvent;
import org.openscore.events.ScoreEventListener;
import org.openscore.lang.api.Slang;
import org.openscore.lang.compiler.SlangSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MyExample {

    public static void main(String[] args) throws URISyntaxException {

        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("/spring/context.xml");

        Slang slang = applicationContext.getBean(Slang.class);

        slang.subscribeOnAllEvents(new ScoreEventListener() {
            @Override
            public void onEvent(ScoreEvent event) {
                System.out.println(event.getEventType() + " : " + event.getData());
            }
        });

        File operationFile = getFile("/content/user/operations/utils/print.sl");
        File flowFile = getFile("/content/user/flows/hello_world/hello_world.sl");

        Set<SlangSource> dependencies = new HashSet<>();
        dependencies.add(SlangSource.fromFile(operationFile));

        slang.compileAndRun(SlangSource.fromFile(flowFile), dependencies,
                            new HashMap<String, Serializable>(),
                            new HashMap<String, Serializable>());
    }

    private static File getFile(String path) throws URISyntaxException {
        return new File(MyExample.class.getResource(path).toURI());
    }

}
