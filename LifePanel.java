import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.Timer;

import java.awt.Color;

class LifePanel extends JPanel implements ActionListener {

	boolean [][] grid;
	private Random r = new Random();

	private int taillePanel = 600;

	private int sizeGrid;

	private Timer timeline;
	private int delay = 100;

	LifePanel(int sizeGrid)
	{
		this.setSize(this.taillePanel,this.taillePanel);

		this.sizeGrid = sizeGrid;

		this.grid = new boolean [sizeGrid][sizeGrid];

		this.randInit();
		

		this.timeline = new Timer(this.delay,this);
		//this.timeline.start();

		//this.lire("vaisseau1.jdl");
		//this.ecrire("Fichiertest.jdl");

	}


	public void actionPerformed(ActionEvent e) {
		this.cycle();
		//System.out.println("ok")
	}

	public void stop()
	{
		this.timeline.stop();
	}

	public void start()
	{
		this.timeline.start();
	}

	public void randInit()
	{
		for ( int i = 0 ; i < this.grid.length ; i++ )
 		{
 			for ( int j = 0 ; j < this.grid[i].length ; j++ )
 			{
 				if ( this.r.nextBoolean() )
 				{
 					this.grid[i][j] = true;
 				}
 			}
 		}
		
	}

	private int compterVoisins(int i, int j)
	{
		int voisin = 0;
		if ( i > 0 ) // haut
		{
			if ( this.grid[i-1][j] )
			{
				voisin ++;
			}
		}

		if ( i > 0 && j < this.grid[i-1].length - 1 ) // haut droite
		{
			if ( this.grid[i-1][j+1] )
			{
				voisin ++;
			}
		}

		if ( j < this.grid[i].length - 1 ) // droite
		{
			if ( this.grid[i][j+1] )
			{
				voisin ++;
			}
		}

		if ( i < this.grid.length - 1 && j < this.grid[i+1].length - 1 ) // droite bas
		{
			if ( this.grid[i+1][j+1] )
			{
				voisin ++;
			}
		}

		if ( i < this.grid.length - 1 ) // bas
		{
			if ( this.grid[i+1][j] )
			{
				voisin ++;
			}
		}

		if ( i < this.grid.length - 1 && j > 0) // bas gauche
		{
			if ( this.grid[i+1][j-1] )
			{
				voisin ++;
			}
		}

		if ( j > 0) // gauche
		{
			if ( this.grid[i][j-1] )
			{
				voisin ++;
			}
		}

		if ( i > 0 && j > 0) // gauche haut
		{
			if ( this.grid[i-1][j-1] )
			{
				voisin ++;
			}
		}

		return voisin;
	}

	public void cycle()
	{
		boolean [][] newGrid = new boolean [this.sizeGrid][this.sizeGrid];
		for ( int i = 0 ; i < this.grid.length ; i++ )
 		{
 			for ( int j = 0 ; j < this.grid[i].length ; j++ )
 			{
 				if ( this.grid[i][j] ) // si elle est vivante à l'instant t
 				{
 					if ( this.compterVoisins(i,j) == 2 || this.compterVoisins(i,j) == 3 ) // et qu'elle a 2 ou 3 voisines
 					{
 						newGrid[i][j] = true; // elle vie à l'instant t + 1
 					}
 					// sinon elle meurt ( par defaut car initialisé à false)
 				}
 				else // si elle est morte à l'instant t
 				{
 					if ( this.compterVoisins(i,j) == 3 ) // et qu'elle a 3 voisines
 					{
 						newGrid[i][j] = true; // elle vie à l'instant t + 1
 					}
 				}
 				// sinon elle reste morte ( par defaut car initialisé à false)


 				//System.out.print(" || "+this.compterVoisins(i,j) + " ||");
 			}
 			//System.out.println("");
 		}

 		for ( int i = 0 ; i < this.grid.length ; i++ )
 		{
 			for ( int j = 0 ; j < this.grid[i].length ; j++ )
 			{
 				this.grid[i][j] = newGrid[i][j]; 
 			}
 		}

 		this.repaint();
	}


	public void lire(String nomFichier)
	{
		this.stop();

		
		File fichier = new File(nomFichier);

		//int [] tempTab;
		//int [] tempTabBis;

		byte[] toRead = null;
		try {
			toRead = new byte[(int)fichier.length()];
			FileInputStream filer = new FileInputStream(fichier);
			DataInputStream lire = new DataInputStream(filer);

			//filer.read(toRead);

			//HashMap<Byte, boolean[]> mapPath  = new HashMap<Byte, boolean[]>();


			Byte carac = lire.readByte();

			int newTaille = (int)carac;

			/*while ( carac != null )
			{
				for ( int i = 0 ; i < 8 ; i ++ )
				{
					tempTabBis = new int [ tempTab.length + 8 ];

					for ( int l = 0 ; l < tempTab.length ; l++ )
					{
						tempTabBis[l] = tempTab[l];
					}

					tempTab = tempTabBis;
					//mask = (byte)(mask + (byte)(0 << 1)); //On on écrit 0
				}
				carac = lire.readByte();
				//mask = 0x1;
			}*/

			
			boolean [][] newGrid = new boolean [newTaille][newTaille];

			int compteur = 0;
			int i = 0;
			int j = 0;
			
			//carac = lire.readByte();
			//while (carac != null)s
			for ( int l = 0 ; l < newTaille*newTaille-2 ; l = l + 1)
			{
				carac = lire.readByte();
				if ( compteur == newTaille)
				{
					i++;
					compteur = 0;
					j = 0;
				}
				if ( carac != 0 )
				{
					if ( i < newGrid.length && j < newGrid.length)
					{
						newGrid[i][j] = true;
						//System.out.println(l);
					}
				}
				j++;
				compteur++;
				
			}

			/*for ( int i = 0 ; i < newGrid.length ; i++ )
	 		{
	 			for ( int j = 0 ; j < newGrid[i].length ; j++ )
	 			{
	 				if ( i * newTaille + j == 1)
	 				{
	 					newGrid[i][j] = true;
	 				}
	 			}
	 		}*/
	 		this.sizeGrid = newTaille;
	 		this.grid = newGrid;


	 		filer.close();


		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}


 		this.repaint();


	}



	public void ecrire(String nomFichier)
	{
		this.stop();

		try {
			File newFichier = new File(nomFichier);
			FileOutputStream newFile = new FileOutputStream(newFichier);
			//newFile.write();
			DataOutputStream ecrire = new DataOutputStream(newFile);
	 
			//Huffman.clefDuCode(ecrire,mapPath);

			try {
				ecrire.write((byte)this.sizeGrid);

				for ( int i = 0 ; i < this.grid.length ; i++ )
		 		{
		 			for ( int j = 0 ; j < this.grid[i].length ; j++ )
		 			{
		 				if ( this.grid[i][j] )
		 				{
							ecrire.write((byte)1);
		 				}
		 				else
		 				{
		 					ecrire.write((byte)0);
		 				}
		 			}
		 		}
			}
			catch (IOException e) {
				System.out.println(e.getMessage());
			}

			newFile.close();

		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}















	/* ------------- DESSINER ------------- */
	private void carre(Graphics pinceau,int x, int y, int taille,boolean vivant) {
		pinceau.setColor(Color.black);
		pinceau.drawRect(x,y,taille,taille); //on le dessine

		if ( vivant )
		{
			pinceau.fillRect(x,y,taille,taille); //on le dessine
		}
	}

	@Override
	public void paintComponent(Graphics pinceau) {
		super.paintComponent(pinceau);

		int taille = this.taillePanel/this.grid.length;

		//System.out.println("taille="+taille);
		//this.carre(pinceau,0,0,taille);
		//this.carre(pinceau,taille,0,taille);

 		for ( int i = 0 ; i < this.grid.length ; i++ )
 		{
 			for ( int j = 0 ; j < this.grid[i].length ; j++ )
 			{
 				this.carre(pinceau,i*taille,j*taille,taille,this.grid[i][j]);

 			}
 		}

	}

}