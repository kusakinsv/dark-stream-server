package ru.dark.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.dark.stream.entities.MusicTrack;
import ru.dark.stream.entities.PlaylistMusicTrack;
import ru.dark.stream.service.MusicTrackService;
import ru.dark.stream.service.PlaylistService;

import java.util.List;

@SpringBootApplication
public class ServerMainApplicationTest {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ServerMainApplicationTest.class, args);

		PlaylistService playlistservice = new PlaylistService();

//		playlistservice.addToPlayList(musicTrack);
		playlistservice.deleteFromPlayListByNumber(4);

	}



}
