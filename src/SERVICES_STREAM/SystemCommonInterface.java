package SERVICES_STREAM;

import java.util.ArrayList;

import STRATEGY_PATTERN.StreamingStrategy;

public interface SystemCommonInterface {
	public Music getMusic(int idTrack);
	public ArrayList<Music> executeSearch(String sound);
}
