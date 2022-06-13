package ru.dark.stream.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dark.stream.model.MusicRequest;
import ru.dark.stream.model.Separator;
import ru.dark.stream.service.BaseSearchService;
import ru.dark.stream.service.ServiceLayer;

@EnableRabbit
@Service
public class Listener {

    @Autowired
    ServiceLayer serviceLayer;

    @Autowired
    RabbitController rabbitController;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BaseSearchService baseSearchService;

    @RabbitListener(queues = "dark-stream-queue-server")
    public void processQueue1(String message) throws JsonProcessingException {
        System.out.println(("Received from queue: " + message));
        interpritate(message);
    }

    void interpritate(String message) throws JsonProcessingException {
        Object object = null;
        System.out.println("ПЕРЕДАНО В ИНТЕРПРЕТАТОР:\n" + message);
        if (message.startsWith("ADD_")) {
            message = message.substring(4);
            ObjectMapper objectMapper = new ObjectMapper();
            object = objectMapper.readValue(message, MusicRequest.class);
            MusicRequest musicRequest = (MusicRequest) object;
            serviceLayer.addTrackToPlayList(musicRequest);
        } else if(message.startsWith("DEL_")) {
            message = message.substring(4);
            serviceLayer.deletePlaylistTrackByNumber(Integer.parseInt(message));
        } else if(message.startsWith("PLAYLIST")){
            String serializedValue = serviceLayer.getPlayList();
            serializedValue = "PLAYLIST_" + serializedValue;
            rabbitController.template.convertAndSend("dark-stream-queue-clients", serializedValue);
        }
        else if(message.startsWith("SEARCH")){
            String[] splittedRequest = message.split(Separator.SEPARATOR.toString());
            String uuid = splittedRequest[1];
            String requestForSearch = splittedRequest[2];
            String serializedValue =  baseSearchService.searchTrackInBase(requestForSearch);
            serializedValue = "RESULT" + Separator.SEPARATOR + uuid + Separator.SEPARATOR + serializedValue;
            rabbitController.template.convertAndSend("dark-stream-queue-clients", serializedValue);
        }
    }
}
