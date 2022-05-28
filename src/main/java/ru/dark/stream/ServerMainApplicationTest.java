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
//		MusicTrack musicTrack = new MusicTrack();
//		musicTrack.setAuthor("NЮ");
//		musicTrack.setTrackName("А мы с тобой теперь никто");
//		musicTrack.setDuration("02:36");
//		musicTrack.setUrl("https://rus.muzmo.cc/get/cuts/db/bd/dbbd681fe919fec14907b712caea9830/73291262/NYU_-_A_my_s_tobojj_teper_nikto_b128f0d156.mp3");
//		PlaylistMusicTrack playlistMusicTrack = new PlaylistMusicTrack();
////		playlistMusicTrack.setNumber(1);
////		playlistMusicTrack.setMusicTrack(musicTrack);
////		musicTrack.setPlaylistMusicTrack(playlistMusicTrack);
//		MusicTrack musicTrack2 = new MusicTrack();
//		musicTrack2.setAuthor("Би-2");
//		musicTrack2.setTrackName("А мы не ангелы парень");
//		musicTrack2.setDuration("03:11");
//		musicTrack2.setUrl("https://r1.");
//		MusicTrackService musicTrackService = new MusicTrackService();
////		musicTrackService.update(musicTrack, musicTrack2);
////		musicTrackService.create(musicTrack2);
//		PlaylistService playlistService = context.getBean(PlaylistService.class);
//		List<PlaylistMusicTrack> playlistMusicTrackList = playlistService.findAll();
//		System.out.println(playlistMusicTrackList.toString());

//		PlaylistService playlistservice = new PlaylistService();

//		playlistservice.addToPlayList(musicTrack);
//		playlistservice.deleteFromPlayList(musicTrack);

	}



}
