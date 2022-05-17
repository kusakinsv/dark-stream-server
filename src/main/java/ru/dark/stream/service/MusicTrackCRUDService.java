package ru.dark.stream.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ru.dark.stream.HibernateUtil;
import ru.dark.stream.entities.MusicTrack;

import java.util.List;

public class MusicTrackCRUDService {

    static Session session = HibernateUtil.getSessionFactory().openSession();


    public void create(MusicTrack musicTrack){
        session.beginTransaction();
        session.save(musicTrack);
        session.getTransaction().commit();
        System.out.println("CREATED: " + musicTrack.getTrackInfo());
    }

    public MusicTrack find(MusicTrack musicTrack){
        Criteria criteria = session.createCriteria(MusicTrack.class);
        criteria.add(Restrictions.eq("url", musicTrack.getUrl()));
        MusicTrack findedTrack = (MusicTrack) criteria.add(Restrictions.eq("trackName", musicTrack.getTrackName()))
                .uniqueResult();
        return findedTrack;
    }

    public List<MusicTrack> findAll(MusicTrack musicTrack){
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MusicTrack.class);
        criteria.add(Restrictions.eq("url", musicTrack.getUrl()));
        List<MusicTrack> findedTracks = (List<MusicTrack>) criteria.add(Restrictions.eq("trackName", musicTrack.getTrackName()))
                .list();
        session.getTransaction().commit();
        return findedTracks;
    }

    public void update(MusicTrack oldTrack, MusicTrack newTrack){
        session.beginTransaction();
        MusicTrack musicTrack = find(oldTrack);
        musicTrack.setTrackName(newTrack.getTrackName());
        musicTrack.setAuthor(newTrack.getAuthor());
        musicTrack.setDuration(newTrack.getDuration());
        musicTrack.setPlaylistMusicTrack(newTrack.getPlaylistMusicTrack());
        musicTrack.setUrl(newTrack.getUrl());
        session.update(musicTrack);
        session.getTransaction().commit();
        System.out.println("UPDATED");
    }

    public void delete(MusicTrack musicTrack){
        session.beginTransaction();
        MusicTrack findedTrack = find(musicTrack);
        session.delete(findedTrack);
        session.getTransaction().commit();
        System.out.println("DELETED: " + findedTrack.getTrackInfo());
    }

}
