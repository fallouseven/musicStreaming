package SERVICES_STREAM;

import java.util.ArrayList;

import STRATEGY_PATTERN.StreamingStrategy;


public class ContextStrategy implements SystemCommonInterface{
	
	private StreamingStrategy strategy;

	public ContextStrategy(StreamingStrategy strategy){
		this.strategy = strategy;	
	}


	@Override
	public Music getMusic(int idTrack) {
		// TODO Auto-generated method stub
		return strategy.get(idTrack);
	}

	@Override
	public ArrayList<Music> executeSearch(String sound) {
		// TODO Auto-generated method stub
		return strategy.search(sound);
	}
}
