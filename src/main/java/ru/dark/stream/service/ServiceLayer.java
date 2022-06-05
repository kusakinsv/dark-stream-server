package ru.dark.stream.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dark.stream.entities.MusicTrack;
import ru.dark.stream.entities.PlaylistMusicTrack;
import ru.dark.stream.mapper.MusicTrackMapper;
import ru.dark.stream.model.MusicRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceLayer {

    private final PlaylistService playlistService;
    private final MusicTrackService musicTrackService;
    private final MusicTrackMapper musicTrackMapper;
    private final ObjectMapper objectMapper;


    public void searchMusic() {

    }

    public void addTrackToPlayList(MusicRequest musicRequest) {
        MusicTrack musicTrack = musicTrackMapper.toMusicTrack(musicRequest);
        playlistService.addToPlayList(musicTrack);
        if (!musicTrackService.trackIsPresent(musicTrack)) {
            musicTrackService.create(musicTrack);
        }
    }

    public void deletePlaylistTrackByNumber(int number) {
        playlistService.deleteFromPlayListByNumber(number);
    }

    public String getPlayList() throws JsonProcessingException {
        List<PlaylistMusicTrack> musicTrackList = playlistService.findAll();
        System.out.println("ДОСТАЛ ИЗ БАЗЫ: " + musicTrackList);
        List<MusicTrack> musicTracks = musicTrackList.stream()
                .map(PlaylistMusicTrack::getMusicTrack)
                .collect(Collectors.toList());
        String writedObject = objectMapper.writeValueAsString(musicTracks);
        return writedObject;
    }


}

