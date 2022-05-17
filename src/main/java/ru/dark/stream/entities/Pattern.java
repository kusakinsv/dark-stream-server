//package ru.dark.stream.entities;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Data
//@Entity
//@Table(name = "patterns")
//public class Pattern {
//    @Id
//    long id;
//
//    String value;
//
//    @ManyToOne
//    @JoinColumn(name = "patterns", referencedColumnName = "id")
//    MusicTrack musicTrack;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Pattern)) return false;
//
//        Pattern pattern = (Pattern) o;
//
//        return getValue() != null ? getValue().equals(pattern.getValue()) : pattern.getValue() == null;
//    }
//
//    @Override
//    public int hashCode() {
//        return getValue() != null ? getValue().hashCode() : 0;
//    }
//}
