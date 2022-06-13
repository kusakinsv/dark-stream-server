package ru.dark.stream.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name = "playlist_music_track")
public class PlaylistMusicTrack {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;

        @Column(name = "number", unique = true)
        int number;


//        @Column(name = "music_track")
//        @JoinTable(name = "joiner")
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "music_track", referencedColumnName = "id")
        MusicTrack musicTrack;

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof PlaylistMusicTrack)) return false;

                PlaylistMusicTrack that = (PlaylistMusicTrack) o;

                return getMusicTrack().equals(that.getMusicTrack());
        }

        @Override
        public int hashCode() {
                return getId() != null ? getId().hashCode() : 0;
        }
}


