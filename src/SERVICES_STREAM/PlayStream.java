package SERVICES_STREAM;

import com.google.gson.*;
import com.soundcloud.api.ApiWrapper;
import com.soundcloud.api.Request;
import com.sun.media.Log;
import com.sun.media.MediaPlayer;
import com.wrapper.*;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.View;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import javax.media.*;

public class PlayStream extends JFrame{
	  private Player mediaPlayer;
	  private Player player;
	  private File file;
	  /*
	   public PlayStream()
	   {
	      super( "Demonstrating the Java Media Player" );
	 
	      JButton openFile = new JButton( "Open file to play" );
	      openFile.addActionListener(
	         new ActionListener() {
	            public void actionPerformed( ActionEvent e )
	            {
	               openFile();
	               createPlayer();
	            }
	         }
	      );
	      getContentPane().add( openFile, BorderLayout.NORTH );
	   
	      setSize( 300, 300 );
	      show();
	   }
	 
	   private void openFile()
	   {      
	      JFileChooser fileChooser = new JFileChooser();
	 
	      fileChooser.setFileSelectionMode(
	         JFileChooser.FILES_ONLY );
	      int result = fileChooser.showOpenDialog( this );
	 
	      // user clicked Cancel button on dialog
	      if ( result == JFileChooser.CANCEL_OPTION )
	         file = null;
	      else
	         file = fileChooser.getSelectedFile();
	   }
	 
	   private void createPlayer()
	   {
	      if ( file == null )
	         return;
	 
	      removePreviousPlayer();
	 
	      try {
	         // create a new player and add listener
	         player = Manager.createPlayer( file.toURL() );
	         player.addControllerListener( new EventHandler() );
	         player.start();  // start player
	      }
	      catch ( Exception e ){
	         JOptionPane.showMessageDialog( this,
	            "Invalid file or location", "Error loading file",
	            JOptionPane.ERROR_MESSAGE );
	      }
	   }
	 
	   private void removePreviousPlayer()
	   {
	      if ( player == null )
	         return;
	 
	      player.close();
	 
	      Component visual = player.getVisualComponent();
	      Component control = player.getControlPanelComponent();
	 
	      Container c = getContentPane();
	      
	      if ( visual != null ) 
	         c.remove( visual );
	 
	      if ( control != null ) 
	         c.remove( control );
	   }*/
	 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bonjour! JE SUIS EN TRAIN DE TESTER");
		System.out.println("Bonjour! Explore");
	/*	PlayStream app = new PlayStream();
		 
	      app.addWindowListener(
	         new WindowAdapter() {
	            public void windowClosing( WindowEvent e )
	            {
	               System.exit(0);
	            }
	         }
	      );
	   }
	 
	   // inner class to handler events from media player
	   private class EventHandler implements ControllerListener {
	      public void controllerUpdate( ControllerEvent e ) {
	         if ( e instanceof RealizeCompleteEvent ) {
	            Container c = getContentPane();
	          
	            // load Visual and Control components if they exist
	            Component visualComponent =
	               player.getVisualComponent();
	 
	            if ( visualComponent != null )
	               c.add( visualComponent, BorderLayout.CENTER );
	 
	            Component controlsComponent =
	               player.getControlPanelComponent();
	 
	            if ( controlsComponent != null )
	               c.add( controlsComponent, BorderLayout.SOUTH );
	 
	            c.doLayout();
	         }
	      }*/
		/*final String songs_url[]={
			    "http://soundcloud.com/qassim/brbjjajwsfcz"
			};

			btn_play.setOnClickListener(new View.OnClickListener() {

			    @Override
			    public void onClick(View arg0) {
			        try {
			            mediaPlayer.setDataSource(songs_url[0]);
			        } catch (IllegalArgumentException e) {
			            // TODO Auto-generated catch block
			            e.printStackTrace();
			        } catch (SecurityException e) {
			            // TODO Auto-generated catch block
			            e.printStackTrace();
			        } catch (IllegalStateException e) {
			            // TODO Auto-generated catch block
			            e.printStackTrace();
			        } catch (IOException e) {
			            // TODO Auto-generated catch block
			            e.printStackTrace();
			        }
			        try {
			            mediaPlayer.prepare();
			        } catch (IllegalStateException e) {
			            // TODO Auto-generated catch block
			            e.printStackTrace();
			        } catch (IOException e) {
			            // TODO Auto-generated catch block
			            e.printStackTrace();
			        } // might take long! (for buffering, etc)
			        mediaPlayer.start();
			    }
			});*/
		 	/*String id = getResources().getString(R.string.sc_client_id);
	        String secret = getResources().getString(R.string.sc_client_secret);
	        ApiWrapper wrapper = new ApiWrapper(id,secret, null, null);

	        try {
	            //Only needed for user-specific actions;
	            //wrapper.login("<user>", "<pass>");
	            //HttpResponse resp = wrapper.get(Request.to("/me"));
	            //Get a track
	            HttpResponse trackResp = wrapper.get(Request.to("/tracks/60913196"));
	            //Track JSON response OK?
	            if(trackResp.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
	            {
	                JSONObject trackJSON = new JSONObject(EntityUtils.toString(trackResp.getEntity()));
	                //If track is streamable, fetch the stream URL (mp3-https) and start the MediaPlayer
	                if(trackJSON.getBoolean("streamable"))
	                {
	                    HttpResponse streamResp = wrapper.get(Request.to("/tracks/60913196/stream"));
	                    JSONObject streamJSON = new JSONObject(EntityUtils.toString(streamResp.getEntity()));
	                    String streamurl = streamJSON.getString("location");
	                    Log.i("SoundCloud", trackJSON.getString("streamable"));
	                    Log.i("SoundCloud", streamurl);
	                    m_soundcloudPlayer.stop();
	                    m_soundcloudPlayer = new MediaPlayer();
	                    m_soundcloudPlayer.setDataSource(streamurl);
	                    m_soundcloudPlayer.prepare();
	                    m_soundcloudPlayer.start();
	                }

	            }
	        }
	        catch (IOException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }catch (ParseException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        catch (JSONException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }*/
	}

}
