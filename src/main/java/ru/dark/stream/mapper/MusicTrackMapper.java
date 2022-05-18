package ru.dark.stream.mapper;

import org.springframework.stereotype.Component;
import ru.dark.stream.entities.MusicTrack;
import ru.dark.stream.model.MusicRequest;

@Component
public class MusicTrackMapper {

    public MusicTrack toMusicTrack(MusicRequest musicRequest) {
        MusicTrack musicTrack = new MusicTrack();
        musicTrack.setAuthor(musicRequest.getTrackName());
        musicTrack.setTrackName(musicRequest.getTrackName());
        musicTrack.setDuration(musicRequest.getDuration());
        musicTrack.setUrl(musicRequest.getUrl());
        return musicTrack;
    }

    public MusicRequest toMusicRequest(MusicTrack musicTrack) {
        MusicRequest musicRequest = new MusicRequest();
        musicRequest.setAuthor(musicTrack.getTrackName());
        musicRequest.setTrackName(musicTrack.getTrackName());
        musicRequest.setDuration(musicTrack.getDuration());
        musicRequest.setUrl(musicTrack.getUrl());
        return musicRequest;
    }
}
