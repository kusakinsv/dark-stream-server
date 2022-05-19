package ru.dark.stream.entities;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Getter
@Table(name = "playlist_music_track")
public class PlaylistMusicTrack {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;

        @Column(name = "number", unique = true)
        int number;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "music_track", referencedColumnName = "id")
        MusicTrack musicTrack;

    }
