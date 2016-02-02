package SERVICES_STREAM;

import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import STRATEGY_PATTERN.Gmusic;
import STRATEGY_PATTERN.SoundCloudMusic;
import STRATEGY_PATTERN.SpotifyMusic;

public class PlayStream extends javax.swing.JFrame{

	public PlayStream()
	{
		super("STREAMING SERVICE");

		WindowListener l = new WindowAdapter() {
			public void windowClosing(Window e){
				System.exit(0);
			}
			
		};
				addWindowListener(l);
				setSize(800,700);
				setVisible(true);	
	}
			/**
			 * @param args
			 */

			public static void main(String[] args) {
				// TODO Auto-generated method stub

				System.out.println("TEST STREAMING");
				JFrame frame = new PlayStream();
				JButton button = new JButton("EXIT");
				//button.addActionListener(this); 
				JLabel label = new JLabel("un petit texte");
				JButton button2 = new JButton("PLAY");
				JButton button3 = new JButton("SEARCH");
				JButton button4 = new JButton("CREATE PLAYLIST");
				JPanel pane = new JPanel();
				//pane.setLayout(new GridLayout(3,2));
				pane.add(button);
				
				pane.add(button2);
				pane.add(button3);
				pane.add(button4);
				frame.getContentPane().add(pane, BorderLayout.CENTER);
				pane.add(label);
				
				frame.show();

				ContextStrategy context = new ContextStrategy(new SpotifyMusic());		
				System.out.println("10 + 5 = " + context.executeStrategy(10, 5));
				ContextStrategy product = new ContextStrategy(new Gmusic());
				ContextStrategy substract = new ContextStrategy(new SoundCloudMusic());			
				System.out.println("10 - 5 = " + product.executeStrategy(10, 5));


				System.out.println("10 * 5 = " + substract.executeStrategy(10, 5));

			}
}

