package ru.dark.stream.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dark.stream.model.MusicRequest;
import ru.dark.stream.service.ServiceLayer;

@EnableRabbit
@Service
public class Listener {

    @Autowired
    ServiceLayer serviceLayer;

    @RabbitListener(queues = "dark-stream-queue-server")
    public void processQueue1(String message) throws JsonProcessingException {
        System.out.println(("Received from queue: " + message));
        interpritate(message);


    }

    void interpritate(String message) throws JsonProcessingException {
        Object object = null;
        if (message.startsWith("ADD_")) {
            message = message.substring(4);
            ObjectMapper objectMapper = new ObjectMapper();
            object = objectMapper.readValue(message, MusicRequest.class);
            MusicRequest request = (MusicRequest) object;
            serviceLayer.addTrackToPlayList(request);
        }
        System.out.println(object.toString());
    }
}
