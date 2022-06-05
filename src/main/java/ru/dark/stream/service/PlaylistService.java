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
            }
        }
        session.getTransaction().commit();
    }

    public void addToPlayList(MusicTrack musicTrack) {

        PlaylistMusicTrack playlistMusicTrack = new PlaylistMusicTrack();
        MusicTrack trackForFound = null;
        trackForFound = musicTrackService.find(musicTrack);
        if (trackForFound == null){
            playlistMusicTrack.setMusicTrack(musicTrack);
            playlistMusicTrack.setNumber(getCountOfCompositions() + 1);
            System.out.println("CREATED: " + playlistMusicTrack.getMusicTrack().getTrackInfo() + " " + playlistMusicTrack.getNumber());
        }
        else {
            playlistMusicTrack.setMusicTrack(trackForFound);
            playlistMusicTrack.setNumber(getCountOfCompositions() + 1);
            System.out.println("ДАННЫЙ ТРЕК УЖЕ ЕСТЬ: " + playlistMusicTrack.getMusicTrack().getTrackInfo() + " " + playlistMusicTrack.getNumber());
        }
        PlaylistMusicTrack findedplaylist = find(playlistMusicTrack);
        if (findedplaylist == null) findedplaylist=playlistMusicTrack;
        session.beginTransaction();
        session.save(findedplaylist);
        session.getTransaction().commit();
    }

    public PlaylistMusicTrack find(PlaylistMusicTrack playlistMusicTrack) {
        Criteria criteria = session.createCriteria(PlaylistMusicTrack.class);
        criteria.add(Restrictions.eq("musicTrack", playlistMusicTrack.getMusicTrack()));
        PlaylistMusicTrack findedPlayListTrack = (PlaylistMusicTrack) criteria.uniqueResult();
        return findedPlayListTrack;
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

    public void deleteFromPlayListByNumber(int number) {
        System.out.println("Удаляем номер: " + number);
        session.beginTransaction();
        Criteria criteria = session.createCriteria(PlaylistMusicTrack.class);
        criteria.add(Restrictions.eq("number", number));
        PlaylistMusicTrack playlistMusicTrack = (PlaylistMusicTrack) criteria.uniqueResult();
        System.out.printf("DELETED: %s with number:  from Playlist\n",
                playlistMusicTrack.getMusicTrack().getTrackInfo());
        playlistMusicTrack.setMusicTrack(null);
        session.delete(playlistMusicTrack);
        session.getTransaction().commit();
        renumeratePlayList();
    }

    public List<PlaylistMusicTrack> findAll() {
        Criteria criteria = session.createCriteria(PlaylistMusicTrack.class);
        List<PlaylistMusicTrack> unsortedList = criteria.list();
        return unsortedList;
    }

    public int getCountOfCompositions() {
        BigInteger count = (BigInteger) session.createSQLQuery("select count('number') from playlist_music_track;")
                .uniqueResult();
        return count.intValue();
    }

    public List<MusicTrack> getTracksFromPlayList(){

        return null;
    }
}
