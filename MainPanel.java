import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.Color;


class MainPanel extends JPanel implements ActionListener {

	LifePanel lifePanel;

	private JButton startButton;
	private JButton stopButton;
	private JButton ecrireButton;
	private JButton lireButton;

	MainPanel(int taille)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panelBoutons = new JPanel();
		panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.X_AXIS));

		this.startButton = new JButton ("START"); 
		this.startButton.setAlignmentX( Component.CENTER_ALIGNMENT );
		this.startButton.addActionListener(this);
		panelBoutons.add(this.startButton);

		this.stopButton = new JButton ("STOP"); 
		this.stopButton.setAlignmentX( Component.CENTER_ALIGNMENT );
		this.stopButton.addActionListener(this);
		panelBoutons.add(this.stopButton);

		this.ecrireButton = new JButton ("ECRIRE"); 
		this.ecrireButton.setAlignmentX( Component.CENTER_ALIGNMENT );
		this.ecrireButton.addActionListener(this);
		panelBoutons.add(this.ecrireButton);

		this.lireButton = new JButton ("LIRE"); 
		this.lireButton.setAlignmentX( Component.CENTER_ALIGNMENT );
		this.lireButton.addActionListener(this);
		panelBoutons.add(this.lireButton);

		this.add(panelBoutons);

		this.lifePanel = new LifePanel(taille);

		this.add(this.lifePanel);
	}

	public void actionPerformed(ActionEvent e) {
		//this.cycle();
		//System.out.println("ok");
		if ( e.getSource() == this.startButton )
		{
			this.lifePanel.start();
		}
		if ( e.getSource() == this.stopButton )
		{
			this.lifePanel.stop();
		}
		if ( e.getSource() == this.ecrireButton )
		{
			this.lifePanel.ecrire("Fichiertest.jdl");
		}
		if ( e.getSource() == this.lireButton )
		{
			this.lifePanel.lire("canon.jdl");
		}

	}

}