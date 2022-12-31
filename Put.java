package rs.ac.bg.etf.kdp.neo.gui;

import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

import rs.ac.bg.etf.kdp.neo.*;

public class Put extends JFrame {

	private static final long serialVersionUID = 1L; // ne koristimo sad ovo

	MessageBox<String> buffer;

	JButton button;
	JTextArea area;

	public Put(final MessageBox<String> buffer) {
		super("Put");
		this.buffer = buffer;
		
		
		button  = new JButton("Put");
		area = new JTextArea();
		button.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						button.setEnabled(false);
						Thread t = new Thread(){
							
							public void run() {
								Message<String> m = new TextMessage();
								m.setBody(area.getText());
								buffer.put(m, null, 0);
								button.setEnabled(true);
							}
						};
						t.start();
					}
		});
		this.setLayout((new GridLayout(2,1)));
		this.add(area);
		this.add(button);
		setSize(400,200);
		setVisible(true);
		
	}
	
}
