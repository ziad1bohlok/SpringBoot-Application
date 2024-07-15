package com.example.advanceRestApi.FirstController;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {
   private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path = "/Hello-world")
    public  HelloWorldBean helloWorld(){
      return new HelloWorldBean("ziad was here");
    }

    @GetMapping(path = "/Hello-world-in")
    public   String HelloWorldInternationalized (){
        Locale locale= LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default message",locale);
    }


    @GetMapping(path = "/Hello-world-variable/path-variable/{name}")
    public  HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello world, %s",name));
    }
}

