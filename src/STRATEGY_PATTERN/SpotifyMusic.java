package STRATEGY_PATTERN;
import com.google.gson.*;
import com.soundcloud.api.ApiWrapper;
import com.soundcloud.api.Request;
import com.sun.media.Log;
import com.sun.media.MediaPlayer;
import com.sun.media.util.SettableTime;
import com.wrapper.*;
import com.wrapper.spotify.Api.*;
import com.wrapper.spotify.Api.Builder;
import com.wrapper.spotify.methods.AlbumRequest;
import com.wrapper.spotify.methods.TrackRequest;
import com.wrapper.spotify.models.Album;
import com.wrapper.spotify.models.AuthorizationCodeCredentials;

import com.google.common.util.concurrent.FutureCallback; 
import com.google.common.util.concurrent.Futures; 
import com.google.common.util.concurrent.SettableFuture; 
import com.wrapper.spotify.Api; 
import com.wrapper.spotify.models.AuthorizationCodeCredentials; 
import static org.junit.Assert.*;

import static junit.framework.TestCase.assertEquals; 
import static junit.framework.TestCase.fail; 
import java.util.*;
import java.util.List;
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
import gmusic.api.impl.*;
import java.util.List;

import javax.swing.JFrame;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.AlbumRequest;
import com.wrapper.spotify.models.Album;

public class SpotifyMusic implements StreamingStrategy{
	//Connection
		String client_id = "68a4bceb1bc64b53a0d344bf152ac006"; // My client id 
		String client_secret = "d042d574650245829c55efd554c88670"; // My client secret 
		String redirect_uri = "http://localhost:8888/callback/"; // My redirect uri 
		String URL="";

		private String connectUserName="soumrin";
		private String connectPassWord="arrazzek";

		String stateKey = "spotify_auth_state";
		
		public void play(){
			System.out.println("Bonjour! JE SUIS EN TRAIN DE TESTER");
			System.out.println("Bonjour! Explore");
			//final String clientId = "68a4bceb1bc64b53a0d344bf152ac006"; 
			final String clientSecret = "d042d574650245829c55efd554c88670";
			final String code = "GOOD";
			final String redirectUri = "http://localhost:8888/callback/";

			Api api = Api.builder()
					  .clientId("68a4bceb1bc64b53a0d344bf152ac006")
					  .clientSecret("d042d574650245829c55efd554c88670")
					  .redirectURI("http://localhost:8888/callback/")
					  .build();
		/*	final Api api = Api.builder().clientId(clientId).clientSecret(clientSecret).redirectURI(redirectUri).build(); 

			/* Make a token request. Asynchronous requests are made with the .getAsync method and synchronous requests 
			 * are made with the .get method. This holds for all type of requests. */ 
		/*	final SettableFuture<AuthorizationCodeCredentials> authorizationCodeCredentialsFuture = api.authorizationCodeGrant(code).build().getAsync();

			/* Add callbacks to handle success and failure */ 
		/*	Futures.addCallback(authorizationCodeCredentialsFuture, new FutureCallback<AuthorizationCodeCredentials>() { 
				public void onSuccess(AuthorizationCodeCredentials authorizationCodeCredentials) { 
					/* The tokens were retrieved successfully! */ 
					//System.out.println("Successfully retrieved an access token! " + authorizationCodeCredentials.getAccessToken()); 
					//System.out.println("The access token expires in " + authorizationCodeCredentials.getExpiresIn() + " seconds"); 
					//System.out.println("Luckily, I can refresh it using this refresh token! " + authorizationCodeCredentials.getRefreshToken()); 
				//} 
	/*
				public void onFailure(Throwable throwable) { 
					/* Let's say that the client id is invalid, or the code has been used more than once, 
					 * the request will fail. Why it fails is written in the throwable's message. */ 
					//fail(throwable.getMessage()); 
				//} 
				
			//}); 
			//
			// Create an API instance. The default instance connects to https://api.spotify.com/.
			//Api api = Api.DEFAULT_API; 

			// Create a request object for the type of request you want to make
			AlbumRequest request = api.getAlbum("7e0ij2fpWaxOEHv5fUYZjd").build();

			// Retrieve an album
			try {
			  Album album = request.get();

			  // Print the genres of the album
			  List<String> genres = album.getGenres(); 
			  for (String genre : genres) {
			    System.out.println(genre);
			  };

			} catch (Exception e) {
			  System.out.println("Could not get albums.");
			}
		}
		
		 public int doOperation(int num1, int num2) {
		      return num1 + num2;
		 }

}
