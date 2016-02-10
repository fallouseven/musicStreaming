package STRATEGY_PATTERN;

import java.util.ArrayList;

import SERVICES_STREAM.Music;

public class Gmusic implements StreamingStrategy{
	
	public void play(){
		
	}
	
	public int doOperation(int num1, int num2) {
	      return num1 - num2;
	}

	@Override
	public Music get(int idTrack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Music> search(String music) {
		// TODO Auto-generated method stub
		return null;
	}

}
