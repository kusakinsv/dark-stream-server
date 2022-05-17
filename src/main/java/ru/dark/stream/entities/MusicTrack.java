package ru.dark.stream.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "music_track")
public class MusicTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "track_name")
    String trackName;

    @Column(name = "author")
    String author;

    @Column(name = "url")
    String url;

    @Column(name = "duration")
    String duration;

//    @OneToMany
//    @JoinColumn(name = "music_track", referencedColumnName = "id")
//    Set<Pattern> patterns;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "playlist_music_track", referencedColumnName = "id")
    PlaylistMusicTrack playlistMusicTrack;

    public MusicTrack(long id, String trackName, String author, String url, String duration, PlaylistMusicTrack playlistMusicTrack) {
        this.trackName = trackName;
        this.author = author;
        this.duration = duration;
        this.url = url;
        this.playlistMusicTrack = playlistMusicTrack;
    }

    public String getTrackInfo(){
        return this.author + " - " + this.trackName + " " + this.duration;
    }

    @Override
    public String toString() {
        return "MusicTrack{" +
                "id=" + id +
                ", trackName='" + trackName + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", duration='" + duration + '\'' +
                ", playlistMusicTrack=" + playlistMusicTrack +
                '}';
    }
}
