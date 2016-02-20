package STRATEGY_PATTERN;

import java.util.ArrayList;

import SERVICES_STREAM.Music;

public interface StreamingStrategy {
	
  public void play();
  public Music get(int idTrack);
  public ArrayList<Music> search(String music);

}
