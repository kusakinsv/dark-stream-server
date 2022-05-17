//package ru.dark.stream.entities;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "[user]")
//public class UserOld {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private long id;
//
//    @Column(name = "username")
//    private String username;
//
//    private String password;
//
//    @ManyToMany
//    @JoinTable(name = "user_track",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "music_track_id")})
//    private Set<MusicTrack> playlistMusicTrack = new HashSet<>();
//
//    public UserOld() {
//        super();
//    }
//}
