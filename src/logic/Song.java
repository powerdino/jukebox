package logic;

import java.io.File;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;

public class Song {

	private String name;
	private File file;
	private MP3File mp3file;
	
	public Song(File file){
		this.file = file;
		String artist = null;
		String title = null;
		try {
			this.mp3file = new MP3File(file);
			artist = this.mp3file.getID3v2Tag().getFirst(FieldKey.ARTIST);
			title = this.mp3file.getID3v2Tag().getFirst(FieldKey.TITLE);
		} catch (Exception  e) {
			this.mp3file = null;
			System.out.println("Error handling MP3-file or Tags.");
			//e.printStackTrace();
		}
		if((artist != null) && (title != null) && (artist != "") && (title != "")){
			name = artist + " - " + title;
		}else{
			name = file.getName().split("\\.")[0];
		}
	}
	
	public File getFile(){
		return this.file;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
