package notepad;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class About extends JFrame implements ActionListener{
	
	About(){
		setTitle("About");
		Image icon = new ImageIcon(getClass().getResource("notepad.png")).getImage();
		setIconImage(icon);
		setBounds(400,100,600,500);
		setLayout(null);
		ImageIcon label = new ImageIcon(getClass().getResource("windows.png"));
		Image i1= label.getImage().getScaledInstance(300, 60	, Image.SCALE_DEFAULT);
		ImageIcon i2 = new ImageIcon(i1);
		JLabel jLabel = new JLabel(i2);
		jLabel.setBounds(70, 40, 400, 80);
		add(jLabel);
		
		ImageIcon label2 = new ImageIcon(getClass().getResource("notepad.png"));
		Image i12= label2.getImage().getScaledInstance(70, 70	, Image.SCALE_DEFAULT);
		ImageIcon i22 = new ImageIcon(i12);
		JLabel jLabel2 = new JLabel(i22);
		jLabel2.setBounds(50, 180, 70, 70);
		add(jLabel2);
		
		JLabel text = new JLabel("<html>Shubham Mehra<br>Version 0.1.0 (OS Build Java)<br>Shubham Mehra. All rights reserved</html>");
		text.setBounds(150,100,500, 240);
		text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		add(text);
		
		JButton b1 = new JButton("OK");
		b1.addActionListener(this);
		b1.setBounds(220, 360, 120, 25);
		add(b1);
		
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new About();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

}
