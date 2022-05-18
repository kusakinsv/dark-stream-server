package ru.dark.stream.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import ru.dark.stream.HibernateUtil;
import ru.dark.stream.entities.MusicTrack;
import ru.dark.stream.entities.PlaylistMusicTrack;

import java.math.BigInteger;

public class PlaylistService {

    MusicTrackCRUDService musicTrackCRUDService = new MusicTrackCRUDService();

    static Session session = HibernateUtil.getSessionFactory().openSession();

    public void addToPlayList(MusicTrack musicTrack){
        PlaylistMusicTrack playlistMusicTrack = new PlaylistMusicTrack();
        playlistMusicTrack.setMusicTrack(musicTrack);
        playlistMusicTrack.setNumber(getCountOfCompositions()+1);
        session.beginTransaction();
        session.save(playlistMusicTrack);
        session.getTransaction().commit();
        System.out.println("CREATED: " + playlistMusicTrack.getMusicTrack().getTrackInfo() + " " + playlistMusicTrack.getNumber());
    }

    public void deleteFromPlayList(MusicTrack musicTrack){
        musicTrack = musicTrackCRUDService.find(musicTrack);
        session.beginTransaction();
//        NativeQuery query = session.createNativeQuery("delete from playlist_music_track where music_track = ?");
//        query.setParameter(1, track.getId());
//        query.executeUpdate();
        String hql = "delete PlaylistMusicTrack where music_track = :musicTrack";
        Query query = session.createQuery(hql);
        query.setParameter("musicTrack", musicTrack);
        query.executeUpdate();
        session.getTransaction().commit();
        System.out.printf("DELETED: %s with number:  from Playlist\n",
                musicTrack.getTrackInfo());
    }

    public int getCountOfCompositions(){
    BigInteger count = (BigInteger) session.createSQLQuery("select count('number') from music_track;")
            .uniqueResult();
    return count.intValue();
    }
}
