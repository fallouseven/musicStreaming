package STRATEGY_PATTERN;

import java.util.ArrayList;

import SERVICES_STREAM.Music;
import de.voidplus.soundcloud.SoundCloud;
import de.voidplus.soundcloud.Track;

public class SoundCloudMusic implements StreamingStrategy{
	private SoundCloud soundcloud;
	private boolean actif;
	@Override
	public void play(){
		
	}
	
	public SoundCloudMusic(){
		setActif(false);
		connect();
	} 
	@Override
	public Music get(int idTrack) {
		// TODO Auto-generated method stub
		Track track = soundcloud.getTrack(idTrack);
		Music musi = new Music(track.getTitle(),track.getGenre(),"","soundcloud",track.getId());
		return musi;
	}

	@Override
	public ArrayList<Music> search(String music) {
		// TODO Auto-generated method stub
		ArrayList<Music> listMusic = new ArrayList<Music>();
		ArrayList<Track> result = soundcloud.findTrack(music);
		if(result!=null){
			
		    System.out.println("Tracks: "+result.size());
		    for(Track track:result){
		            Music musi = new Music(track.getTitle(),track.getGenre(),"","soundcloud",track.getId());
		            listMusic.add(musi);
		    }
		}
		return listMusic;
	}
	
	private void connect(){
		 soundcloud = new SoundCloud(
			    "3a9d3057e13d2d0bf2bc6d31a036f6bb",
			    "3f02d82d9b67a06ebd3ee2deca11fae5"
			);
			setActif(soundcloud.login(
			    "oussou.dieng@gmail.com",
			    "soundcloud7"
			));
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

}
