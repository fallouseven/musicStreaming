package SERVICES_STREAM;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.BorderFactory;
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
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.json.JSONObject;
import org.w3c.dom.Document;

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
				JButton buttonSearch = new JButton("SEARCH");
				buttonSearch.setToolTipText("Search Music");
				JButton button4 = new JButton("CREATE");
				button4.setToolTipText("Create PLAYLIST");
				JButton buttonAdd = new JButton("ADD");
				buttonAdd.setToolTipText("Add Music to PLAYLIST");
				JButton buttonRemove = new JButton("REMOVE");
				buttonRemove.setToolTipText("Remove Music to PLAYLIST");
				JTextField textField = new JTextField(10);
				JPanel pane = new JPanel();
				//pane.setLayout(new GridLayout(1,2));
				JPanel pane1 = new JPanel();
				JPanel pane2 = new JPanel();
				JPanel pane3 = new JPanel();
				JPanel pane4 = new JPanel();
				pane4.setLayout(new GridLayout(1,2));
				pane.add(button);
				pane.add(buttonAdd);
				pane.add(buttonRemove);
				pane.add(button2);
				pane.add(buttonSearch);
				pane.add(button4);
				pane.add(textField);
				frame.getContentPane().add(pane, BorderLayout.NORTH);
				frame.getContentPane().add(pane4, BorderLayout.WEST);
				frame.getContentPane().add(pane2, BorderLayout.CENTER);
				//frame.getContentPane().add(pane3, BorderLayout.EAST);
				pane4.add(pane1);
				pane4.add(pane3);
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
				String[] columnNames2 = {"Playlist selectionnée"
                };
				DefaultTableModel dtm1 = new DefaultTableModel(null, columnNames1);
				JTable table1 = new JTable(dtm1){
			        private static final long serialVersionUID = 1L;

			        public boolean isCellEditable(int row, int column) {                
			                return false;               
			        };
			    };
				table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
				pane1.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
				pane1.setLayout(new BorderLayout());
				pane1.add(table1.getTableHeader(), BorderLayout.NORTH);
				pane1.add(table1, BorderLayout.CENTER);
				DefaultTableModel dtm2 = new DefaultTableModel(null, columnNames2);
				JTable table2 = new JTable(dtm2){
			        private static final long serialVersionUID = 1L;

			        public boolean isCellEditable(int row, int column) {                
			                return false;               
			        };
			    };
				table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
				pane3.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
				pane3.setLayout(new BorderLayout());
				pane3.add(table2.getTableHeader(), BorderLayout.NORTH);
				pane3.add(table2, BorderLayout.CENTER);
				DefaultTableModel dtm = new DefaultTableModel(null, columnNames);
				JTable table = new JTable(dtm){
			        private static final long serialVersionUID = 1L;

			        public boolean isCellEditable(int row, int column) {                
			                return false;               
			        };
			    };
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
				search.parseDir("", dtm1);
				
				buttonSearch.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						pane2.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
						pane2.add(new JScrollPane(table));
						pane2.setLayout(new BorderLayout());
						pane2.add(table.getTableHeader(), BorderLayout.PAGE_START);
						pane2.add(table, BorderLayout.CENTER);
						dtm.setRowCount(0);
						
						ArrayList<Music> musi = search.search(textField.getText());
						if(musi.isEmpty()){
							JOptionPane.showMessageDialog(null, "Cette musique n'existe pas!", "Information", JOptionPane.INFORMATION_MESSAGE);
						}else{
								for(Music sound : musi){
									dtm.addRow(new Object[]{String.valueOf(sound.getName()),
										String.valueOf(sound.getGenre()),
										String.valueOf(sound.getSource()),
										String.valueOf(sound.getApi()),
										Integer.valueOf(sound.getIdTrack())});
								}
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
				//click button add
				buttonAdd.addActionListener(new ActionListener(){
					
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
							Document doc = search.createPlayList();
							search.updateFileJson(music,String.valueOf(dtm1.getValueAt(keepValue.getRowSelected1(), 0)), doc);
						dtm2.addRow(new Object[]{String.valueOf(dtm.getValueAt(keepValue.getRowSelected(), 0).toString())
						});
						}
						if(keepValue.getRowSelected() == -1 && keepValue.getRowSelected1() == -1){
							JOptionPane.showMessageDialog(null, "Veuillez selectionner une play liste!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						}else if(keepValue.getRowSelected1() == -1){
							JOptionPane.showMessageDialog(null, "Veuillez selectionner une play liste!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						}else if(keepValue.getRowSelected() == -1){
							JOptionPane.showMessageDialog(null, "Veuillez selectionner une musique!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
				});
				//click buttonRemove
				buttonRemove.addActionListener(new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Object[] options = {"Oui",
	                    "Non"};
						// TODO Auto-generated method stub
						if(keepValue.getRowSelected2() != -1 && keepValue.getRowSelected1() != -1){
							int dialogResult = JOptionPane.showOptionDialog (null, "Voulez vous supprimer la musique?","Suppression",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
								    null,     //do not use a custom Icon
								    options,  //the titles of buttons
								    options[0]);
							if(dialogResult == JOptionPane.YES_OPTION){
							search.removePlayList(String.valueOf(dtm1.getValueAt(keepValue.getRowSelected1(), 0)),String.valueOf(dtm2.getValueAt(keepValue.getRowSelected2(), 0)));
							dtm2.removeRow(keepValue.getRowSelected2());
							}
						}else if(keepValue.getRowSelected1() != -1){
							int dialogResult = JOptionPane.showOptionDialog (null, "Voulez vous supprimer la play liste?","Suppression",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
								    null,     //do not use a custom Icon
								    options,  //the titles of buttons
								    options[0]);
							if(dialogResult == JOptionPane.YES_OPTION){
							search.removePlayList(String.valueOf(dtm1.getValueAt(keepValue.getRowSelected1(), 0)),"");
							dtm2.setRowCount(0);
							dtm1.removeRow(keepValue.getRowSelected1());
							}
						}else if(keepValue.getRowSelected1() == -1 && keepValue.getRowSelected2() == -1){
							JOptionPane.showMessageDialog(null, "Veuillez selectionner une play liste!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						}else if(keepValue.getRowSelected2() == -1){
							JOptionPane.showMessageDialog(null, "Veuillez selectionner une musique!", "Attention", JOptionPane.INFORMATION_MESSAGE);
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
					      String nameFile = dtm1.getValueAt(keepValue.getRowSelected1(), 0).toString();
					      search.parseFile(nameFile, dtm2);
					      
					    }
					  }
					});
				table2.addMouseListener(new MouseAdapter() {
					  public void mouseClicked(MouseEvent e) {
					    if (e.getClickCount() == 1) {
					      JTable target = (JTable)e.getSource();
					      keepValue.setRowSelected2(target.getSelectedRow());
					      //int column = target.getSelectedColumn();
					      
					    }
					  }
					});
				table.addMouseListener(new MouseAdapter() {
					int rowSelected = -1;
					  public void mouseClicked(MouseEvent e) {
					    if (e.getClickCount() == 1) {
					      JTable target = (JTable)e.getSource();
					      keepValue.setRowSelected(target.getSelectedRow());
					      //int column = target.getSelectedColumn();
					    }
					  }
					});
				

			}
}

