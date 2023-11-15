package notepad;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Notepad extends JFrame implements ActionListener {
	JTextArea area;
	String text;
	Notepad(){
		
		setTitle("NotePad");
		
		Image icon = new ImageIcon(getClass().getResource("notepad.png")).getImage();
		setIconImage(icon);
		
		JMenuBar menubar = new JMenuBar();
		menubar.setBackground(Color.WHITE);
		
//		File menu
		
		JMenu file = new JMenu("File");
		file.setFont(new Font("AERIAL", Font.BOLD, 15));
		
		
		JMenuItem newDoc = new JMenuItem("New");
		JMenuItem openDoc = new JMenuItem("Open");
		JMenuItem saveDoc = new JMenuItem("Save");
		JMenuItem printDoc = new JMenuItem("Print");
		JMenuItem closeDoc = new JMenuItem("Exit");

		newDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newDoc.addActionListener(this);
		openDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		openDoc.addActionListener(this);
		saveDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveDoc.addActionListener(this);
		printDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		printDoc.addActionListener(this);
		closeDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK));
		closeDoc.addActionListener(this);

		file.add(newDoc);
		file.add(openDoc);
		file.add(saveDoc);
		file.add(printDoc);
		file.add(closeDoc);
		
		menubar.add(file);
		
//		Edit Menu
		
		JMenu edit = new JMenu("Edit");
		edit.setFont(new Font("AERIAL", Font.BOLD, 15));

		JMenuItem copy = new JMenuItem("Copy");
		JMenuItem paste = new JMenuItem("Paste");
		JMenuItem cut = new JMenuItem("Cut");
		JMenuItem selectAll = new JMenuItem("Select All");
		
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		copy.addActionListener(this);
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		paste.addActionListener(this);
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		cut.addActionListener(this);
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		selectAll.addActionListener(this);
		
		edit.add(copy);
		edit.add(paste);
		edit.add(cut);
		edit.add(selectAll);

		menubar.add(edit);
		
//		About menu
		
		JMenu helpMenu = new JMenu("About");
		helpMenu.setFont(new Font("AERIAL", Font.BOLD, 15));
		JMenuItem help = new JMenuItem("About");
		help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		help.addActionListener(this);
		helpMenu.add(help);
		
		menubar.add(helpMenu);
		
		setJMenuBar(menubar);
		
		area = new JTextArea();
		area.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		
		JScrollPane jScrollPane = new JScrollPane(area);
		jScrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(jScrollPane);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setVisible(true);;
	}
	
	public static void main(String[] args) {
		new Notepad();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("New")) {
			area.setText("");			
		}
		else if(e.getActionCommand().equals("Open")) {
			JFileChooser chooser = new JFileChooser();
			chooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
			chooser.addChoosableFileFilter(restrict);
			int action = chooser.showOpenDialog(this);
			if(action != JFileChooser.APPROVE_OPTION) {
				return;
			}
			File file = chooser.getSelectedFile();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				area.read(reader, null);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}else if(e.getActionCommand().equals("Save")) {
			JFileChooser saveas =  new JFileChooser();
			saveas.setApproveButtonText("Save");
			int action = saveas.showOpenDialog(this);
			if(action != JFileChooser.APPROVE_OPTION) {
				return;
			}
			File filename = new File(saveas.getSelectedFile()+".txt");
			BufferedWriter outfile = null;
			try {
				outfile = new BufferedWriter(new FileWriter(filename));
				area.write(outfile);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}else if(e.getActionCommand().equals("Print")) {
			try {
				area.print();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}else if(e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}else if(e.getActionCommand().equals("Copy")) {
			text = area.getSelectedText();
		}else if(e.getActionCommand().equals("Paste")) {
			area.insert(text, area.getCaretPosition());
		}else if(e.getActionCommand().equals("Cut")) {
			text = area.getSelectedText();
			area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
		}else if(e.getActionCommand().equals("Select All")) {
			area.selectAll();
		}else if(e.getActionCommand().equals("About")) {
			new About().setVisible(true);
		}
	}
}
