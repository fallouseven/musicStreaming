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

import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
	public Document createPlayList(){
		
		DocumentBuilderFactory dbFactory =
		         DocumentBuilderFactory.newInstance();
		         DocumentBuilder dBuilder;
				try {
					dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.newDocument();
			         // root element
			         Element rootElement = doc.createElement("playList");
			         doc.appendChild(rootElement);
			         return doc;
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;	         
	}
	public void createPlayList(Music music,Document doc){
		
    //  music element
		Element rootElement = doc.getDocumentElement();
					 Element musi = doc.createElement("music");
					 rootElement.appendChild(musi);
     // name element
					 Element name = doc.createElement("name");
					 name.appendChild(
					 doc.createTextNode(music.getName()));
					 musi.appendChild(name);
     // Genre element
					 Element genre = doc.createElement("genre");
					 genre.appendChild(
					 doc.createTextNode(music.getGenre()));
					 musi.appendChild(genre);
     // Source element
					 Element source = doc.createElement("source");
					 source.appendChild(
					 doc.createTextNode(music.getSource()));
					 musi.appendChild(source);
     // Api element
					 Element api = doc.createElement("api");
					 api.appendChild(
					 doc.createTextNode(music.getApi()));
					 musi.appendChild(api);
     // idTrack element
					 Element idTrack = doc.createElement("idTrack");
					 idTrack.appendChild(
					 doc.createTextNode(String.valueOf(music.getIdTrack())));
					 musi.appendChild(api);
	}
	public void saveInFile(String name,Document doc){
		 // write the content into xml file
		String workingDir = System.getProperty("user.dir");
		String file = workingDir+"/Files/"+name+".xml";
        TransformerFactory transformerFactory =
        TransformerFactory.newInstance();
        Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
	        StreamResult result =
	        new StreamResult(new File(file));
	        try {
				transformer.transform(source, result);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	public void updateFileJson(Music music,String nameFile,Document doc){
		String workingDir = System.getProperty("user.dir");
		try {
			BufferedReader br = new BufferedReader(new FileReader(workingDir+"/Files/"+nameFile+".xml"));     
			if (br.readLine() == null) {
				this.createPlayList(music, doc);
				saveInFile(nameFile,doc);
			   
			}else{
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder;
				try {
					documentBuilder = documentBuilderFactory.newDocumentBuilder();
					/* parse existing file to DOM */
					try {
						Document document = documentBuilder.parse(new File(workingDir+"/Files/"+nameFile+".xml"));
						this.createPlayList(music, document);
						saveInFile(nameFile,document);
					} catch (SAXException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (ParserConfigurationException e) {
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
		File file = new File(workingDir+"/Files/"+name+".xml");
		if(!file.exists()){
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(workingDir+"/Files/"+name+".xml")))) {
	   
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
	public void parseFile(String nameFile,DefaultTableModel dtm){
		String workingDir = System.getProperty("user.dir");
	
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			/* parse existing file to DOM */
			
				try {
					BufferedReader br = new BufferedReader(new FileReader(workingDir+"/Files/"+nameFile+".xml"));     
					if (br.readLine() == null) {
						
					}else{
					Document document = documentBuilder.parse(new File(workingDir+"/Files/"+nameFile+".xml"));
					NodeList list = document.getElementsByTagName("music");
					
					for(int i = 0; i < list.getLength(); i++){
			        if (list.item(i) != null) {
			            NodeList subList = list.item(i).getChildNodes();
			            if (subList != null && subList.getLength() > 0) {
			            	dtm.addRow(new Object[]{subList.item(0).getTextContent()
							});
			            	
			            }
			        }
					}
					}
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void parseDir(String nameDir,DefaultTableModel dtm){
		String workingDir = System.getProperty("user.dir");
		final File folder = new File(workingDir+"/Files/");
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	parseDir(fileEntry.getName(),dtm);
	        } else {
	        	String extension = "";

	        	int i = fileEntry.getName().lastIndexOf('.');
	        	if (i > 0) {
	        	    extension = fileEntry.getName().substring(i+1);
	        	}

	        	if(extension.equals("xml"))
	        		dtm.addRow(new Object[]{fileEntry.getName().substring(0,i)
	        		});
	           
	        }
	    }
	}
	public void removePlayList(String nameList, String nameFile){
		String workingDir = System.getProperty("user.dir");
		if(nameFile.isEmpty()){
			File file = new File(workingDir+"/Files/"+nameList+".xml");
			file.deleteOnExit();
		}else{
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			/* parse existing file to DOM */
			
				try {
					Document document = documentBuilder.parse(new File(workingDir+"/Files/"+nameList+".xml"));
					NodeList list = document.getElementsByTagName("music");
					boolean find = false;
					int i = 0;
					for(i = 0; i < list.getLength() && !find; i++){
			        if (list.item(i) != null) {
			            NodeList subList = list.item(i).getChildNodes();
			            if (subList != null && subList.getLength() > 0) {
			            	if(subList.item(0).getTextContent().equals(nameFile))
			            		find = true;
			            	
			            }
			        }
					}
					
					list.item(i-1).getParentNode().removeChild(list.item(i-1));
					this.saveInFile(nameList, document);
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	public void searchLocal(){
		
	}
}
