package SERVICES_STREAM;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonObject;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import STRATEGY_PATTERN.SoundCloudMusic;
import STRATEGY_PATTERN.SpotifyMusic;
import STRATEGY_PATTERN.StreamingStrategy;

public class MusicManager{
	public ArrayList<Music> search(String  sound){
		ArrayList<Music> music = new ArrayList<Music>();
		ContextStrategy contextSoundCloud = new ContextStrategy(new SoundCloudMusic());
		music = contextSoundCloud.executeSearch(sound);
		//ContextStrategy contextSpotify = new ContextStrategy(new SpotifyMusic());
		//music.addAll(contextSpotify.executeSearch(sound));
		return music;
	}
	public ArrayList<Music> search(String  sound,StreamingStrategy strategy){
		ArrayList<Music> music = new ArrayList<Music>();
		ContextStrategy contextSoundCloud = new ContextStrategy(strategy);
		music = contextSoundCloud.executeSearch(sound);
		//ContextStrategy contextSpotify = new ContextStrategy(new SpotifyMusic());
		//music.addAll(contextSpotify.executeSearch(sound));
		return music;
	}
	public void createPlayList(Music music,JSONObject jo){
		try {
			JSONArray so = new JSONArray();
			so.put("Name:"+ music.getName());
			so.put("Genre:" + music.getGenre());
			so.put("Source:" + music.getSource());
			so.put("Api:"+ music.getApi());
			so.put("IdTrack:"+ String.valueOf(music.getIdTrack()));
			jo.put("music", so);
			System.out.println("\nJSON Object: " + jo);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void saveInJson(String name,JSONObject obj){
		FileWriter file;
		try {
			String workingDir = System.getProperty("user.dir");
			   System.out.println("Current working directory : " + workingDir);
				file = new FileWriter(workingDir+"/Files/"+name+".json");
				file.write(obj.toString());
				file.flush();
				file.close();
				System.out.println("Successfully Copied JSON Object to File...");
				System.out.println("\nJSON Object: " + obj);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void updateFileJson(String nameFile,JSONObject jo){
		String workingDir = System.getProperty("user.dir");
		try {
			BufferedReader br = new BufferedReader(new FileReader(workingDir+"/Files/"+nameFile+".json"));     
			if (br.readLine() == null) {
				saveInJson(nameFile,jo);
			    System.out.println("No errors, and file empty");
			}else{
				
				try {
					
					String jsonData = "";
					BufferedReader br1 = null;
					try {
						String line;
						br1 = new BufferedReader(new FileReader(workingDir+"/Files/"+nameFile+".json"));
						while ((line = br1.readLine()) != null) {
							jsonData += line + "\n";
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							if (br1 != null)
								br1.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
					JSONObject jsonObject = new JSONObject(jsonData);
					//JSONObject jsonObject = JsonObject.readFrom(new FileReader(workingDir+"/Files/"+nameFile+".json"));
					System.out.println("\nJSON Object: " + jsonObject);
					// loop array
						JSONArray msg = jsonObject.getJSONArray("music");
						for (int i = 0; i < msg.length(); i++)
						{
							/*JSONObject costing = msg.getJSONObject(i);
							String Name =costing.getString("Name");
							String Genre = costing.getString("Genre");
							String Source = costing.getString("Source");
							String Api = costing.getString("Api");
							int IdTrack = Integer.parseInt(String.valueOf(costing.getString("IdTrack")));
							Music music = serializeToObject(Name,Genre,Source,Api,IdTrack);
							createPlayList(music,jo);*/
						}
						saveInJson(nameFile,jo);

				} catch (ParseException e) {
					e.printStackTrace();
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public Music serializeToObject(String name,String genre,String source,String api,int idTrack){
		Music music = new Music(name,genre,source,api,idTrack);
		return music;
	}
	public void createNewFile(String name){
		String workingDir = System.getProperty("user.dir");
		File file = new File("/users/mkyong/filename.txt");
		if(!file.exists()){
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(workingDir+"/Files/"+name+".json")))) {
	   
		} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
	}
	
}
