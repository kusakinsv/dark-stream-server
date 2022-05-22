package ru.dark.stream.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dark.stream.entities.MusicTrack;
import ru.dark.stream.mapper.MusicTrackMapper;
import ru.dark.stream.model.MusicRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceLayer {

    private final PlaylistService playlistService;
    private final MusicTrackService musicTrackService;
    private final MusicTrackMapper musicTrackMapper;

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
        playlistService.deleteByNumber(number);
    }

    public List<MusicTrack> getPlayList() {

        return new ArrayList<>();
    }


}


//    MusicTrack checkAndAddMusicToTrackStore(MusicRequest musicRequest) {
//        String pattern = musicRequest.getPattern();
//        String url = musicRequest.getUrl();
//        MusicTrack musicTrack = null;
//        try {
//            Query query = session.createQuery("from MusicTrack where url = :url)");
//            query.setParameter("url", url);
//            musicTrack = (MusicTrack) query.getSingleResult();
//            Set<Pattern> patternList = musicTrack.getPatterns();
//            Set<String> patternByString = patternList.stream().map(Pattern::getValue).collect(Collectors.toSet());
//            if (!patternByString.contains(pattern)) {
//                session.beginTransaction();
//                Pattern newPattern = new Pattern();
//                musicTrack.getPatterns().add(newPattern);
//                newPattern.setMusicTrack(musicTrack);
//                newPattern.setValue(pattern);
//                session.save(newPattern);
//                session.getTransaction().commit();
//                System.out.println("Паттерн добавлен");
//            } else System.out.println("Такой паттерн уже есть!");
//        } catch (Exception e) {
//            System.out.println("Трек не найден");
//        }
//        if (musicTrack == null){
//            musicTrack = new MusicTrack();
//            musicTrack.setAuthor(musicRequest.getTrackName());
//            musicTrack.setTrackName(musicRequest.getTrackName());
//            musicTrack.setDuration(musicRequest.getDuration());
//            musicTrack.setUrl(musicRequest.getUrl());
//            Pattern pa = new Pattern();
//            pa.setValue(pattern);
//            musicTrack.setPatterns(new HashSet<>(){{
//                add(pa);
//            }});
//            session.beginTransaction();
//            session.save(musicTrack);
//            session.getTransaction().commit();
//
//        }
//
//        return musicTrack;
//    }

