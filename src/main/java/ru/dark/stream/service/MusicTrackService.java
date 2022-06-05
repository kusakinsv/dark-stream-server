package ru.dark.stream.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import ru.dark.stream.HibernateUtil;
import ru.dark.stream.entities.MusicTrack;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MusicTrackService {

    static Session session = HibernateUtil.getSessionFactory().openSession();

    public void create(MusicTrack musicTrack) {
        session.beginTransaction();
        session.save(musicTrack);
        session.getTransaction().commit();
        System.out.println("CREATED: " + musicTrack.getTrackInfo());
    }

    public MusicTrack find(MusicTrack musicTrack) {
        Criteria criteria = session.createCriteria(MusicTrack.class);
        criteria.add(Restrictions.eq("url", musicTrack.getUrl()));
        MusicTrack findedTrack = (MusicTrack) criteria.add(Restrictions.eq("trackName", musicTrack.getTrackName()))
                .uniqueResult();
        return findedTrack;
    }

    public List<MusicTrack> findAll() {
        System.out.println("Ищем...");
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MusicTrack.class);
        List<MusicTrack> findedTracks = criteria.list();
        session.getTransaction().commit();
        return findedTracks;
    }

    public void update(MusicTrack oldTrack, MusicTrack newTrack) {
        session.beginTransaction();
        MusicTrack musicTrack = find(oldTrack);
        musicTrack.setTrackName(newTrack.getTrackName());
        musicTrack.setAuthor(newTrack.getAuthor());
        musicTrack.setDuration(newTrack.getDuration());
//        musicTrack.setPlaylistMusicTrack(newTrack.getPlaylistMusicTrack());
        musicTrack.setUrl(newTrack.getUrl());
        session.update(musicTrack);
        session.getTransaction().commit();
        System.out.println("UPDATED");
    }

    public void delete(MusicTrack musicTrack) {
        session.beginTransaction();
        MusicTrack findedTrack = find(musicTrack);
        session.delete(findedTrack);
        session.getTransaction().commit();
        System.out.println("DELETED: " + findedTrack.getTrackInfo());
    }

    public void deleteById(Integer id) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MusicTrack.class);
        MusicTrack findedTrack = (MusicTrack) criteria.add(Restrictions.eq("id", (long) id)).uniqueResult();
        session.delete(findedTrack);
        session.getTransaction().commit();
        System.out.println("DELETED: " + findedTrack.getTrackInfo());
    }

    public void deleteTrackByNumber(Integer number) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MusicTrack.class);
        MusicTrack findedTrack = (MusicTrack) criteria.add(Restrictions.eq("number", (long) number)).uniqueResult();
        session.delete(findedTrack);
        session.getTransaction().commit();
        System.out.println("DELETED: " + findedTrack.getTrackInfo());
    }

    public boolean trackIsPresent(MusicTrack musicTrack) {
        return find(musicTrack) != null;
    }
}


