package ru.dark.stream.service;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ru.dark.stream.HibernateUtil;
import ru.dark.stream.entities.MusicTrack;
import ru.dark.stream.entities.PlaylistMusicTrack;
import ru.dark.stream.model.MusicRequest;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceLayer {
    static Session session;

    public void searchMusic() {

    }


    void addMusicToPlayList() {

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

    public void addTrackToPlayList(MusicRequest musicRequest) {


        List<MusicTrack> musicMusicTrackList = new ArrayList<>();
        String trackName = musicRequest.getTrackName();
        String pattern = musicRequest.getPattern();
        MusicTrack musicTrack = new MusicTrack();
//                musicRequest.getTrackName(),
//                musicRequest.getAuthor(),
//                musicRequest.getDuration(),
//                musicRequest.getUrl());
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<PlaylistMusicTrack> playlistMusicTrackList = null;
            try {
                Criteria criteria = session.createCriteria(PlaylistMusicTrack.class);
                playlistMusicTrackList = (List<PlaylistMusicTrack>) criteria.list();
            } catch (Exception e) {
            }
            if (playlistMusicTrackList == null) {
                playlistMusicTrackList = new ArrayList<>();
            }
            int number = playlistMusicTrackList.size();
            PlaylistMusicTrack playlistMusicTrack = new PlaylistMusicTrack();
            playlistMusicTrack.setMusicTrack(musicTrack);
            playlistMusicTrack.setNumber(number + 1);
        } catch (Exception e) {
        }
    }

    public void replaceTrack() {

    }

    public void deleteFromPlayList() {

    }

}
