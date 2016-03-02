package logic;

import java.io.FileInputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;


import org.tritonus.share.sampled.file.TAudioFileFormat;

import javazoom.jl.player.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
//import javafx.scene.media.Media;
import javafx.util.Duration;

public class Jukebox implements ListChangeListener<Song>{
	
	private Player mediaplayer = null;
	private ObservableList<Song> o_playlist;
//	private ProgressBar songprogress;
//	private Label remainingTime;
//	private Label nowPlaying;
	
	public Jukebox(ObservableList<Song> o_playlist, ProgressBar songprogress, Label remainingTime, Label nowPlaying){
		this.o_playlist = o_playlist;
//		this.songprogress = songprogress;
//		this.remainingTime = remainingTime;
//		this.nowPlaying = nowPlaying;
	}
	
	@Override
	public void onChanged(Change<? extends Song> c) {
			// start playing when empty list is not empty anymore
			if((!o_playlist.isEmpty()) && (mediaplayer == null)){
//				mediaplayer = new MediaPlayer(new Media(o_playlist.get(0).getFile().toURI().toString()));
				
				try {
					
					AudioFileFormat baseFileFormat = null;
//					AudioFormat baseFormat = null;
					baseFileFormat = AudioSystem.getAudioFileFormat(o_playlist.get(0).getFile());
//					baseFormat = baseFileFormat.getFormat();
					// TAudioFileFormat properties
					if (baseFileFormat instanceof TAudioFileFormat){
					    System.out.println(((TAudioFileFormat)baseFileFormat).properties().get("duration"));
					}
					// TAudioFormat properties
//					if (baseFormat instanceof TAudioFormat)
//					{
//					     Map properties = ((TAudioFormat)baseFormat).properties();
//					     String key = "bitrate";
//					     Integer val = (Integer) properties.get(key);
//					     System.out.println(val);
//					}
					
					this.mediaplayer = new Player(new FileInputStream(o_playlist.get(0).getFile()));
					this.mediaplayer.play();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
//				mediaplayer.currentTimeProperty().addListener(new SongProgressListener());
//				mediaplayer.setOnEndOfMedia(new AutoPlay());
//				nowPlaying.setText(o_playlist.get(0).toString());
//				mediaplayer.start();
//				o_playlist.remove(0);
			}
	}
	
	class SongProgressListener implements ChangeListener<Duration> {

		@Override
		public void changed(ObservableValue<? extends Duration> arg0, Duration arg1, Duration arg2) {
//			// set song progress bar progress
//			songprogress.setProgress(mediaplayer.getCurrentTime().toMillis()/mediaplayer.getTotalDuration().toMillis());
//
//			// calculate human readable time format
//			int seconds = ((int)Math.floor(mediaplayer.getTotalDuration().toSeconds() - mediaplayer.getCurrentTime().toSeconds()));
//			int hours = seconds / 3600;
//			int minutes = (seconds / 60) % 60;
//			seconds = seconds % 60;
//			remainingTime.setText(( "- " + (hours >= 10 ? String.valueOf(hours) : ("0" + String.valueOf(hours))) + ":" +
//				(minutes >= 10 ? String.valueOf(minutes) : ("0" + String.valueOf(minutes))) + ":" +
//				(seconds >= 10 ? String.valueOf(seconds) : ("0" + String.valueOf(seconds)))));
		}
	}
	
	class AutoPlay implements Runnable{
		@Override
		public void run() {
			if(!o_playlist.isEmpty()){
//				mediaplayer.stop();
//				mediaplayer = new MediaPlayer(new Media(o_playlist.get(0).getFile().toURI().toString()));
//				mediaplayer.currentTimeProperty().addListener(new SongProgressListener());
//				mediaplayer.setOnEndOfMedia(new AutoPlay());
//				nowPlaying.setText(o_playlist.get(0).toString());
//				mediaplayer.play();
//				o_playlist.remove(0);
//			}else{
//				mediaplayer.stop();
//				mediaplayer = null;
			}
		}
	}
	

}
