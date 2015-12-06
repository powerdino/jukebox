package application;

import java.io.File;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.Predicate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import logic.Jukebox;
import logic.Song;

public class FrontendController {

	@FXML private GridPane root;
	@FXML private ListView<Song> playlist;
	@FXML private ListView<Song> library;
	@FXML private ProgressBar songprogress;
	@FXML private Label labelRemainingTime;
	@FXML private Label labelNowPlaying;
	@FXML private Label labelCredits;
	@FXML private VBox categoriesContainer;
	private ToggleGroup tg;
	
	private MediaPlayer mediaplayer;
	private int credits = 0;
	
	private LinkedList<Song> l_library;	
	private ObservableList<Song> o_library;
	private SortedList<Song> s_library;
	private FilteredList<Song> f_library;
	private ObservableList<Song> o_playlist;
	private LinkedList<Song> l_playlist;
	
	public final static String LIBRARY_FOLDER = "F:\\Eigene Dateien\\workspace\\MyJukebox\\songs"; 
	
	public void initialize(){
		
		File f = new File(LIBRARY_FOLDER);
		File[] lib_files = f.listFiles();
		
		l_library = new LinkedList<Song>();
		
		for(File s : lib_files){
			l_library.add(new Song(s));
		}
		
		this.setupCategories();		
		
		this.o_library = FXCollections.observableList(l_library);
		
		this.f_library = new FilteredList<Song>(this.o_library);
		this.f_library.setPredicate(new FilterLibraryPredicate());
		this.tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			@Override
			public void changed(ObservableValue<? extends Toggle> arg0,	Toggle arg1, Toggle arg2) {
				f_library.setPredicate(new FilterLibraryPredicate());
			}
		});
		
		this.s_library = new SortedList<Song>(this.f_library);
		this.s_library.comparatorProperty().set(new Comparator<Song>(){
			public int compare(Song song1, Song song2) {
				return song1.toString().compareTo(song2.toString());
			}
		});
		
		
		this.library.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		this.library.setItems(this.s_library);
		
		// init playlist
		this.l_playlist = new LinkedList<Song>();
		this.o_playlist = FXCollections.observableList(this.l_playlist);
		this.playlist.setItems(o_playlist);
		
		this.library.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent clickEvent) {
				if(clickEvent.getButton().equals(MouseButton.PRIMARY)){
					if((clickEvent.getClickCount() == 2) && (credits > 0)){
						o_playlist.add(library.getSelectionModel().getSelectedItem());
						decCredit();
					}
				}
			}
		});
		
		this.root.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent keyEvent) {
				if(keyEvent.getCode().equals(KeyCode.O)){
					incCredit();
				}
				if(keyEvent.getCode().equals(KeyCode.LEFT)){
					int i = tg.getToggles().indexOf(tg.getSelectedToggle());
					if(i > 0){
						tg.getToggles().get(i-1).setSelected(true);
					}
				}
				if(keyEvent.getCode().equals(KeyCode.RIGHT)){
					int i = tg.getToggles().indexOf(tg.getSelectedToggle());
					if(i < 27){
						tg.getToggles().get(i+1).setSelected(true);
					}
				}
				if(keyEvent.getCode().equals(KeyCode.DOWN)){
					
				}
				if(keyEvent.getCode().equals(KeyCode.UP)){
					
				}
			}
			
		});
		
		this.library.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if((keyEvent.getCode().equals(KeyCode.ENTER)) && (credits > 0)){
					o_playlist.add(library.getSelectionModel().getSelectedItem());
					decCredit();
				}
			}
		});
		
		this.o_playlist.addListener(new Jukebox(mediaplayer, o_playlist, songprogress, labelRemainingTime, labelNowPlaying));
	}
	
	private void incCredit(){
		this.credits++;
		this.labelCredits.setText("Credits: " + String.valueOf(this.credits));
	}
	
	private void decCredit() {
		this.credits--;
		this.labelCredits.setText("Credits: " + String.valueOf(this.credits));
		
	}
	
	private void setupCategories() {
		// create toggle group for all categories
		this.tg = new ToggleGroup();
		
		// create categories radio buttons
		RadioButton rbAll = new RadioButton("All");
		rbAll.setSelected(true);
		RadioButton rbA = new RadioButton("A");
		RadioButton rbB = new RadioButton("B");
		RadioButton rbC = new RadioButton("C");
		RadioButton rbD = new RadioButton("D");
		RadioButton rbE = new RadioButton("E");
		RadioButton rbF = new RadioButton("F");
		RadioButton rbG = new RadioButton("G");
		RadioButton rbH = new RadioButton("H");
		RadioButton rbI = new RadioButton("I");
		RadioButton rbJ = new RadioButton("J");
		RadioButton rbK = new RadioButton("K");
		RadioButton rbL = new RadioButton("L");
		RadioButton rbM = new RadioButton("M");
		RadioButton rbN = new RadioButton("N");
		RadioButton rbO = new RadioButton("O");
		RadioButton rbP = new RadioButton("P");
		RadioButton rbQ = new RadioButton("Q");
		RadioButton rbR = new RadioButton("R");
		RadioButton rbS = new RadioButton("S");
		RadioButton rbT = new RadioButton("T");
		RadioButton rbU = new RadioButton("U");
		RadioButton rbV = new RadioButton("V");
		RadioButton rbW = new RadioButton("W");
		RadioButton rbX = new RadioButton("X");
		RadioButton rbY = new RadioButton("Y");
		RadioButton rbZ = new RadioButton("Z");
		
		// add all radio buttons to toggle group
		rbAll.setToggleGroup(tg);
		rbA.setToggleGroup(tg);
		rbB.setToggleGroup(tg);
		rbC.setToggleGroup(tg);
		rbD.setToggleGroup(tg);
		rbE.setToggleGroup(tg);
		rbF.setToggleGroup(tg);
		rbG.setToggleGroup(tg);
		rbH.setToggleGroup(tg);
		rbI.setToggleGroup(tg);
		rbJ.setToggleGroup(tg);
		rbK.setToggleGroup(tg);
		rbL.setToggleGroup(tg);
		rbM.setToggleGroup(tg);
		rbN.setToggleGroup(tg);
		rbO.setToggleGroup(tg);
		rbP.setToggleGroup(tg);
		rbQ.setToggleGroup(tg);
		rbR.setToggleGroup(tg);
		rbS.setToggleGroup(tg);
		rbT.setToggleGroup(tg);
		rbU.setToggleGroup(tg);
		rbV.setToggleGroup(tg);
		rbW.setToggleGroup(tg);
		rbX.setToggleGroup(tg);
		rbY.setToggleGroup(tg);
		rbZ.setToggleGroup(tg);
		
		// add all radio buttons to GUI
		this.categoriesContainer.getChildren().add(rbAll);
		this.categoriesContainer.getChildren().add(rbA);
		this.categoriesContainer.getChildren().add(rbB);
		this.categoriesContainer.getChildren().add(rbC);
		this.categoriesContainer.getChildren().add(rbD);
		this.categoriesContainer.getChildren().add(rbE);
		this.categoriesContainer.getChildren().add(rbF);
		this.categoriesContainer.getChildren().add(rbG);
		this.categoriesContainer.getChildren().add(rbH);
		this.categoriesContainer.getChildren().add(rbI);
		this.categoriesContainer.getChildren().add(rbJ);
		this.categoriesContainer.getChildren().add(rbK);
		this.categoriesContainer.getChildren().add(rbL);
		this.categoriesContainer.getChildren().add(rbM);
		this.categoriesContainer.getChildren().add(rbN);
		this.categoriesContainer.getChildren().add(rbO);
		this.categoriesContainer.getChildren().add(rbP);
		this.categoriesContainer.getChildren().add(rbQ);
		this.categoriesContainer.getChildren().add(rbR);
		this.categoriesContainer.getChildren().add(rbS);
		this.categoriesContainer.getChildren().add(rbT);
		this.categoriesContainer.getChildren().add(rbU);
		this.categoriesContainer.getChildren().add(rbV);
		this.categoriesContainer.getChildren().add(rbW);
		this.categoriesContainer.getChildren().add(rbX);
		this.categoriesContainer.getChildren().add(rbY);
		this.categoriesContainer.getChildren().add(rbZ);
		
		this.categoriesContainer.setPadding(new Insets(15,10,10,10));
	}
	
	class FilterLibraryPredicate implements Predicate<Song>{
		@Override
		public boolean test(Song song) {
			String filter = ((RadioButton)tg.getSelectedToggle()).getText();
			if(filter.equals("All")){
				return true;
			}else{
				if(song.toString().toUpperCase().startsWith(filter)){
					return true;
				}else{
					return false;
				}
			}
		}
	}
}
