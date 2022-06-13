package ru.dark.stream.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.dark.stream.entities.MusicTrack;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Getter
@Setter
@AllArgsConstructor
public class BaseSearchService {
    MusicTrackRepository musicTrackRepository;
    ObjectMapper objectMapper;

    @SneakyThrows
    public String searchTrackInBase(String name) {
        List<MusicTrack> musicTrackList = musicTrackRepository.findAll();
        List<MusicTrack> searched = new ArrayList<>();
        for (MusicTrack musicTrack : musicTrackList) {
            if (hasWord((musicTrack.getAuthor() + " " + musicTrack.getTrackName()), name)) {
                searched.add(musicTrack);
            }
        }

        return objectMapper.writeValueAsString(searched);
    }


    private boolean hasWord(String trackName, String name) {
        boolean hasWord = false;
        trackName = trackName.toLowerCase(Locale.ROOT);
        name = name.toLowerCase(Locale.ROOT);
        Set<String> splittedName = Arrays.stream(name.replaceAll(",|:|\\(|\\)", "").split(" ")).filter(x-> x.length()>2).collect(Collectors.toSet());
        for (String word : splittedName) {
            if (trackName.contains(word)) {
                hasWord = true;
                break;
            }
        }
        return hasWord;
    }

}




