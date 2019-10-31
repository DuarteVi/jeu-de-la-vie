import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

import java.awt.Color;


public class Main {
public static void main(String[] args) {

		/*Scanner sc = new Scanner(System.in);
		int taille = sc.nextInt();*/

		int taille = 100;
		if ( args.length > 0)
			taille = Integer.parseInt(args[0]);

		if ( taille > 100 )
		{
			taille = 100;
		}
		else if ( taille < 2 )
		{
			taille = 2;
		}

		JFrame fenetre = new JFrame(); 

		fenetre.setLocation(0,0);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setTitle("Jeu de La Vie");
		fenetre.setSize(1200, 1200);


		MainPanel jeu = new MainPanel(taille);

		//lifePanel.randInit();

		fenetre.add(jeu);

		fenetre.setVisible(true);

	}
}