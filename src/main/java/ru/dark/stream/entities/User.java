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
//public class User {
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
//    private PlaylistMusicTrack playlistMusicTrack;
//
//    public User() {
//        super();
//    }
//}
