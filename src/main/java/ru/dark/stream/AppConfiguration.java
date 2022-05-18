package ru.dark.stream;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.dark.stream.rabbit.RabbitConfiguration;

@Configuration
@Import(RabbitConfiguration.class)
@ComponentScan("ru.dark.stream")
public class AppConfiguration {

}
