package ru.dark.stream.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dark.stream.HibernateUtil;
import ru.dark.stream.entities.MusicTrack;
import ru.dark.stream.entities.PlaylistMusicTrack;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistService {

    @Autowired
    private MusicTrackService musicTrackService;

    static Session session = HibernateUtil.getSessionFactory().openSession();

    private void renumeratePlayList() {
        session.beginTransaction();
        List<PlaylistMusicTrack> playlist = findAll();
        int nextnumber = 1;
        for (PlaylistMusicTrack track : playlist) {
            if (track.getNumber() == nextnumber) nextnumber++;
            else if (track.getNumber() > nextnumber) {
                track.setNumber(nextnumber);
                nextnumber++;
                session.update(track);
                session.getTransaction().commit();
            }
        }
        session.getTransaction().commit();
    }


    public void addToPlayList(MusicTrack musicTrack) {
        PlaylistMusicTrack playlistMusicTrack = new PlaylistMusicTrack();
        playlistMusicTrack.setMusicTrack(musicTrack);
        playlistMusicTrack.setNumber(getCountOfCompositions() + 1);
        session.beginTransaction();
        session.save(playlistMusicTrack);
        session.getTransaction().commit();
        System.out.println("CREATED: " + playlistMusicTrack.getMusicTrack().getTrackInfo() + " " + playlistMusicTrack.getNumber());
    }

    public void deleteFromPlayList(MusicTrack musicTrack) {
        musicTrack = musicTrackService.find(musicTrack);
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
        renumeratePlayList();
    }

    public void deleteByNumber(Integer number) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MusicTrack.class);
        MusicTrack findedTrack = (MusicTrack) criteria.add(Restrictions.eq("number", (long) number)).uniqueResult();
        session.delete(findedTrack);
        session.getTransaction().commit();
        System.out.println("DELETED: " + findedTrack.getTrackInfo());
        renumeratePlayList();
    }


    public List<PlaylistMusicTrack> findAll() {
        Criteria criteria = session.createCriteria(PlaylistMusicTrack.class);
        List<PlaylistMusicTrack> unsortedList = criteria.list();
        return unsortedList;
    }

    public int getCountOfCompositions() {
        BigInteger count = (BigInteger) session.createSQLQuery("select count('number') from music_track;")
                .uniqueResult();
        return count.intValue();
    }

    public List<MusicTrack> getTracksFromPlayList(){

        return null;
    }
}
