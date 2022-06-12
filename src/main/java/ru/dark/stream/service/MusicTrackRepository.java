package ru.dark.stream.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dark.stream.entities.MusicTrack;

public interface MusicTrackRepository extends JpaRepository<MusicTrack, Long> {

}
