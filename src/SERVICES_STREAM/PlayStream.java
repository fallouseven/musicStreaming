package SERVICES_STREAM;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.json.JSONObject;

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
				KeepValue keepValue = new KeepValue();
				MusicManager search = new MusicManager();
				System.out.println("TEST STREAMING");
				JFrame frame = new PlayStream();
				JButton button = new JButton("EXIT");
				//button.addActionListener(this); 
				JLabel label = new JLabel("un petit texte");
				JButton button2 = new JButton("PLAY");
				button2.setToolTipText("Play Music");
				JButton button3 = new JButton("SEARCH");
				button3.setToolTipText("Search Music");
				JButton button4 = new JButton("CREATE");
				button4.setToolTipText("Create PLAYLIST");
				JButton button5 = new JButton("ADD");
				button5.setToolTipText("Add Music to PLAYLIST");
				JButton button6 = new JButton("REMOVE");
				button6.setToolTipText("Remove Music to PLAYLIST");
				JTextField textField = new JTextField(10);
				JPanel pane = new JPanel();
				//pane.setLayout(new GridLayout(1,2));
				JPanel pane1 = new JPanel();
				JPanel pane2 = new JPanel();
				pane.add(button);
				pane.add(button5);
				pane.add(button6);
				pane.add(button2);
				pane.add(button3);
				pane.add(button4);
				pane.add(textField);
				frame.getContentPane().add(pane, BorderLayout.NORTH);
				frame.getContentPane().add(pane1, BorderLayout.WEST);
				frame.getContentPane().add(pane2, BorderLayout.CENTER);
				pane.add(label);
				
				frame.show();
				String[] columnNames = {"Title",
                        "Genre",
                        "source",
                        "api",
                        "numero"
                        };
				String[] columnNames1 = {"Playlist"
                        };
				DefaultTableModel dtm1 = new DefaultTableModel(null, columnNames1);
				JTable table1 = new JTable(dtm1);
				pane1.add(table1.getTableHeader(), BorderLayout.NORTH);
				pane1.add(table1, BorderLayout.CENTER);
				DefaultTableModel dtm2 = new DefaultTableModel(null, columnNames1);
				JTable table2 = new JTable(dtm2);
				pane1.add(table2, BorderLayout.EAST);
				DefaultTableModel dtm = new DefaultTableModel(null, columnNames);
				JTable table = new JTable(dtm);
				
				button3.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						
						 
						pane2.add(table.getTableHeader(), BorderLayout.PAGE_START);
						pane2.add(table, BorderLayout.CENTER);
						dtm.setRowCount(0);
						
						ArrayList<Music> musi = search.search(textField.getText());
						
								for(Music sound : musi){
									dtm.addRow(new Object[]{String.valueOf(sound.getName()),
										String.valueOf(sound.getGenre()),
										String.valueOf(sound.getSource()),
										String.valueOf(sound.getApi()),
										Integer.valueOf(sound.getIdTrack())});
								}
						
					}
					
				});
				button4.addActionListener(new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						boolean notinsert = false;
						for(int i=0;i<dtm1.getRowCount();i++){
							System.out.println(dtm1.getValueAt(i, 0).toString());
							if((textField.getText().toLowerCase()).equals(dtm1.getValueAt(i, 0).toString()))
								notinsert = true;
						}
						if(!notinsert){
							search.createNewFile(String.valueOf(textField.getText()));
							dtm1.addRow(new Object[]{String.valueOf(textField.getText())
							});
						}
					}
					
				});
				button5.addActionListener(new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println("rowSelected :"+keepValue.getRowSelected1());
						boolean notinsert = false;
						for(int i=0;i<dtm2.getRowCount();i++){
							System.out.println(dtm2.getValueAt(i, 0).toString());
							if((textField.getText().toLowerCase()).equals(dtm2.getValueAt(i, 0).toString()))
								notinsert = true;
						}
						if(!notinsert && keepValue.getRowSelected() != -1 && keepValue.getRowSelected1() != -1){
							Music music = search.serializeToObject(String.valueOf(dtm.getValueAt(keepValue.getRowSelected(), 0)),
									String.valueOf(dtm.getValueAt(keepValue.getRowSelected(), 1)),
											String.valueOf(dtm.getValueAt(keepValue.getRowSelected(), 2)), 
													String.valueOf(dtm.getValueAt(keepValue.getRowSelected(), 3)),
													Integer.parseInt(String.valueOf(dtm.getValueAt(keepValue.getRowSelected(), 4))));
							JSONObject jo = new JSONObject();
							search.createPlayList(music, jo);
							search.updateFileJson(String.valueOf(dtm1.getValueAt(keepValue.getRowSelected1(), 0)), jo);
						dtm2.addRow(new Object[]{String.valueOf(dtm.getValueAt(keepValue.getRowSelected(), 0).toString())
						});
						}
						if(keepValue.getRowSelected() == -1 && keepValue.getRowSelected1() == -1){
							JOptionPane.showMessageDialog(null, "Please Select a playList and a sound!", "InfoBox: Select", JOptionPane.INFORMATION_MESSAGE);
						}else if(keepValue.getRowSelected1() == -1){
							JOptionPane.showMessageDialog(null, "Please Select a playList!", "InfoBox: Select", JOptionPane.INFORMATION_MESSAGE);
						}else if(keepValue.getRowSelected() == -1){
							JOptionPane.showMessageDialog(null, "Please Select a sound!", "InfoBox: Select", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
				});
				table1.addMouseListener(new MouseAdapter() {
					  public void mouseClicked(MouseEvent e) {
					    if (e.getClickCount() == 1) {
					    	dtm2.setRowCount(0);
					      JTable target = (JTable)e.getSource();
					      keepValue.setRowSelected1(target.getSelectedRow());
					      int column = target.getSelectedColumn();
					      // do some action if appropriate column
					      System.out.println(dtm1.getValueAt(keepValue.getRowSelected1(), 0).toString());
					    }
					  }
					});
				table2.addMouseListener(new MouseAdapter() {
					  public void mouseClicked(MouseEvent e) {
					    if (e.getClickCount() == 1) {
					      JTable target = (JTable)e.getSource();
					      keepValue.setRowSelected2(target.getSelectedRow());
					      int column = target.getSelectedColumn();
					      // do some action if appropriate column
					      
					    }
					  }
					});
				table.addMouseListener(new MouseAdapter() {
					int rowSelected = -1;
					  public void mouseClicked(MouseEvent e) {
					    if (e.getClickCount() == 1) {
					      JTable target = (JTable)e.getSource();
					      keepValue.setRowSelected(target.getSelectedRow());
					      int column = target.getSelectedColumn();
					      // do some action if appropriate column
					      System.out.println(dtm.getValueAt(keepValue.getRowSelected(), 0).toString());
					    }
					  }
					});
				

			}
}

