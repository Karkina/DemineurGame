import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
	public class JeuTerrain extends JLabel {

		 public  final ImageIcon bombeView =new ImageIcon("images/bombe.jpg");
		 public  final ImageIcon drapeauView = new ImageIcon("images/drapeau.jpg");
		 public JFrame frame=new JFrame("Jlabel test ");
		 private ListeRecord mesRecords;
		 public  Case [][] buttonAll;
		 public Timer myTime;
		 public JButton buttonTimer;
		 public int seconde,heure,minute;
		 public int taille;
		 public int bombes;
		 public boolean perdu;
		 private int restant;
		 public JButton test;
		 private boolean decouverte=true;
		 
		 //private static final ImageIcon iconePartieEnCours = new ImageIcon(cheminBase + "voiture_marche.png");

		// private static final ImageIcon iconePartieGagnee = new ImageIcon(cheminBase + "partie_gagnee.png");

		// private static final ImageIcon iconePartiePerdue =new ImageIcon(cheminBase + "partie_perdue.png");
		 public JeuTerrain(int taille,int bombe) 
		 {
			 buttonTimer=new JButton();
			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 frame.setSize(1000,600);
			 frame.setLocationRelativeTo(null);
			 frame.setLayout(new BorderLayout());
			 frame.setVisible(true);
			 perdu=false;
			 test=new JButton();
			 setPartie(taille,bombe);
			 
			 
			
		 }
		 public void restantNeg()
		 {
			 this.restant--;
			 if(this.restant==0){
				 this.PartieGagner(); 
			}
		 }
		 public void restantAdd()
		 {
			 this.restant++;
		 }
		 public boolean getDecouverte()
		 {
			 return decouverte;
		 }
		 public String getTime(){
			 String time=heure+":"+minute+":"+seconde;
			 return time;
		 }
		 public void setTimer(){
			 this.heure=0;
			 this.minute=0;
			 this.seconde=0;
			 ActionListener  timeListener;
			 timeListener=new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					 seconde++;
					  if(seconde==60)
					  {
						  seconde=0;
						  minute++;
					  }
					  if(minute==60)
					  {
						  minute=0;
						  heure++;
					
					  }
			
					  buttonTimer.setText(getTime());
					 
					  
				 
			 }
			 };
			 myTime=new Timer(1000,timeListener);
			 myTime.start();
			 
		 }
		 public void setPartie(int taille, int bombe)
		 {
			 mesRecords=new ListeRecord();
			 setTimer();
			 this.taille=taille;
			 this.bombes=bombe; 
			 this.restant=this.taille*this.taille;
			 this.buttonAll=new Case[this.taille][this.taille];
			  
			 //frame.setVisible(true);
			  TerrainCanvas test=new TerrainCanvas();
			  JButton button2=new JButton("Decouvre Bombe ");
			  JMenuBar menu=new JMenuBar();
			  JMenu moyen=new JMenu("Moyen");
			  JMenu facile=new JMenu("Facile");
			  JMenu difficile=new JMenu("Difficile");
			  JPanel myPanel=new JPanel(new GridLayout(this.taille,this.taille));
			  JPanel changement=new JPanel();
			  Dimension size = new Dimension(150,50);
			  button2.setLayout(null);
			  button2.setPreferredSize(size);
			  button2.setMaximumSize(size);
			  button2.setMinimumSize(size);
			  
			  
		
			 // changement.setMaximumSize(new Dimension(100, 100));
		      //changement.setMinimumSize(new Dimension(100,100));
		     
			  int inix=0;
			  int iniy=0;
			  //JLabel jlabel = new JLabel(this.iconePartieEnCours, JLabel.CENTER);
			  menu.add(facile);
			  menu.add(moyen);
			  menu.add(difficile);
			  //Button Drapeau Ou Bombe
			  button2.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					if(decouverte){
					button2.setIcon(drapeauView);
					button2.setText("Drapeau");
					button2.setForeground(Color.RED);
					button2.setHorizontalTextPosition(SwingConstants.CENTER);
					decouverte=false;
					}
					else{
						button2.setText("Decouvre Bombe");
						button2.setHorizontalTextPosition(SwingConstants.CENTER);
						
						button2.setIcon(bombeView);
						decouverte=true;
					}
					
				}
				  
			  });
			  changement.add(button2);	
			  changement.add(buttonTimer);	
			  
			  //CASE
			  for(int i=0;i<this.taille;i++)
			  {
				  for(int j=0;j<this.taille;j++){
				 
				  this.buttonAll[i][j]=new Case(this,i,j);
				  this.buttonAll[i][j].setBounds(inix, iniy, 10, 10);
				  inix+=50;
				  if(inix>frame.getBounds().getX())
				  {
					  inix=0;
					  iniy+=50;
				  }
				  myPanel.add(buttonAll[i][j]);
				  }
			  }
			  //BOMBE
			  int random1=(int) (Math.random()*this.taille);
			  int random2=(int) (Math.random()*this.taille);
			  int testBombe=0;
			  while(bombes!=testBombe)
				{
					
					if(!this.buttonAll[random1][random2].getBombe())
					{
						this.buttonAll[random1][random2].setBombe();
						testBombe++;
					}
					random1=(int) (Math.random()*this.taille);
					random2=(int) (Math.random()*this.taille);
				}
			  //CALCUL ADJA
			  for(int i=0;i<this.taille;i++){
					for(int j=0;j<this.taille;j++){
						this.calculAdja(i, j);
					}
				}
			  //button1.setBounds(0, 0, 50, 50);
				 // myPanel.add(button1);
				 
				 // menu.add(myPanel);
				 // frame.validate();
				 // frame.getContentPane().add(jlabel);
				  //frame.validate();
				 
				 //frame.getContentPane().add(test,BorderLayout.SOUTH);
				 frame.setJMenuBar(menu);
				 frame.add(myPanel,BorderLayout.CENTER);
				 frame.add(changement,BorderLayout.NORTH);
				 frame.validate();
				 moyen.addMenuListener(new MenuMoyenListener());
				 difficile.addMenuListener(new MenuDifficileListener());
					
				
				
				 
		 }
				
			public void testFin(int i,int j)
			{
				 Integer obj = new Integer(this.restant); 
				 String s = obj.toString();
				 this.test.setText(s);
				 frame.getContentPane().add(this.test,BorderLayout.SOUTH);
			}
			public void calculAdja(int i, int j)
			{
				int adja=0;
						if(!this.buttonAll[i][j].getBombe())
						{
							if(j+1<this.taille)
							{
								if(this.buttonAll[i][j+1].getBombe())
									adja++;
							}
							if(j-1>=0){
								if(this.buttonAll[i][j-1].getBombe())
									adja++;
							}
							
							if(i-1>=0)
							{
								
								if(this.buttonAll[i-1][j].getBombe()){
									adja++;
								}
								if(j+1<this.taille)
								{
									
									if(this.buttonAll[i-1][j+1].getBombe())
										adja++;
								}
								if(j-1>=0){
				
									if(this.buttonAll[i-1][j-1].getBombe())
										adja++;
								}
							}
							
						
							if(i+1<this.taille)
							{
								
								if(this.buttonAll[i+1][j].getBombe())
									adja++;
								
								if(j+1<this.taille)
								{
									if(this.buttonAll[i+1][j+1].getBombe())
										adja++;
								
								}
								if(j-1>=0)
								{
									if(this.buttonAll[i+1][j-1].getBombe())
										adja++;
								}

							}
							this.buttonAll[i][j].setAdja(adja);
						}
						
			  
			 
			  
			  
			 // frame.add(test);
			 		 
		 }
			public void calculAdjaTest(int i, int j)
			{
						if(!this.buttonAll[i][j].getBombe())
						{
							if(j+1<this.taille)
							{
								if(this.buttonAll[i][j+1].getDrapeau())
									this.buttonAll[i][j].getTestAdjacentsMoins();
							}
							if(j-1>=0)
							{
								if(this.buttonAll[i][j-1].getDrapeau())
									this.buttonAll[i][j].getTestAdjacentsMoins();
							}
							
							if(i-1>=0)
							{
								
								if(this.buttonAll[i-1][j].getDrapeau()){
									this.buttonAll[i][j].getTestAdjacentsMoins();
								}
								if(j+1<this.taille)
								{
									
									if(this.buttonAll[i-1][j+1].getDrapeau())
										this.buttonAll[i][j].getTestAdjacentsMoins();
								}
								if(j-1>=0){
				
									if(this.buttonAll[i-1][j-1].getDrapeau())
										this.buttonAll[i][j].getTestAdjacentsMoins();
								}
							}
							
						
							if(i+1<this.taille)
							{
								
								if(this.buttonAll[i+1][j].getDrapeau())
									this.buttonAll[i][j].getTestAdjacentsMoins();
								
								if(j+1<this.taille)
								{
									if(this.buttonAll[i+1][j+1].getDrapeau())
										this.buttonAll[i][j].getTestAdjacentsMoins();
								
								}
								if(j-1>=0)
								{
									if(this.buttonAll[i+1][j-1].getDrapeau())
										this.buttonAll[i][j].getTestAdjacentsMoins();
								}

							}
							
						}
						
			  
			 
			  
			  
			 // frame.add(test);
			 		 
		 }
			public void AdjacentVide(int i,int j)
			{
				if(this.buttonAll[i][j].getTestAdjacents()<=0){
					if(j+1<this.taille)
					{
						if(!this.buttonAll[i][j+1].getDrapeauBombe()){
							this.buttonAll[i][j+1].setVisible();
							this.restantNeg();
						   this.buttonAll[i][j+1].setText();
						}
					}
					if(j-1>=0){
						if(!this.buttonAll[i][j-1].getDrapeauBombe()){
							this.buttonAll[i][j-1].setVisible();
							this.restantNeg();
						   this.buttonAll[i][j-1].setText();
						}
					}
					
					if(i-1>=0)
					{
						
						if(!this.buttonAll[i-1][j].getDrapeauBombe()){
							this.buttonAll[i-1][j].setVisible();
							this.restantNeg();
						   this.buttonAll[i-1][j].setText();
						}
						if(j+1<this.taille)
						{
							
							if(!this.buttonAll[i-1][j+1].getDrapeauBombe()){
								this.buttonAll[i-1][j+1].setVisible();
								this.restantNeg();
							   this.buttonAll[i-1][j+1].setText();
							}
								
						}
						if(j-1>=0){
		
							if(!this.buttonAll[i-1][j-1].getDrapeauBombe()){
								this.buttonAll[i-1][j-1].setVisible();
								this.restantNeg();
							   this.buttonAll[i-1][j-1].setText();
							}
								
						}
					}
					
				
					if(i+1<this.taille)
					{
						
						if(!this.buttonAll[i+1][j].getDrapeauBombe()){
							this.buttonAll[i+1][j].setVisible();
							this.restantNeg();
						   this.buttonAll[i+1][j].setText();
						}
							
						
						if(j+1<this.taille)
						{
							if(!this.buttonAll[i+1][j+1].getDrapeauBombe()){
								this.buttonAll[i+1][j+1].setVisible();
								this.restantNeg();
							   this.buttonAll[i+1][j+1].setText();
							}
						
						
						}
						if(j-1>=0)
						{
							if(!this.buttonAll[i+1][j-1].getDrapeauBombe()){
								this.buttonAll[i+1][j-1].setVisible();
								this.restantNeg();
							   this.buttonAll[i+1][j-1].setText();
							}
								
						}

					}
				}
					
				
			}
			public void PartiePerdu()
			{
				
				//currentFrame.getContentPane().removeAll();
				this.perdu=true;
				for(int i=0;i<this.taille;i++){
					for(int j=0;j<this.taille;j++){
						this.buttonAll[i][j].setText();
					}
				}
				JLabel textFin=new JLabel("C'est Perdu");
				textFin.setHorizontalAlignment(CENTER);
				textFin.setVerticalAlignment(CENTER);
				textFin.setFont(new Font("Arial", Font.PLAIN, 75));
				textFin.setForeground(Color.RED);
				this.mesRecords.closeRecord();
				myTime.stop();
				frame.getContentPane().add(textFin,BorderLayout.SOUTH);
				frame.revalidate();
				frame.repaint();
				
			}
			//Si la partie est gagnée
			public void PartieGagner()
			{
				JLabel textFin=new JLabel("C'est Gagner");
				textFin.setHorizontalAlignment(CENTER);
				textFin.setVerticalAlignment(CENTER);
				textFin.setFont(new Font("Arial", Font.PLAIN, 75));
				textFin.setForeground(Color.RED);
				//this.mesRecords.write("Record :"+ buttonTimer.getText() );
				this.mesRecords.ajouterRecord(this.getTime());
				myTime.stop();
				frame.getContentPane().removeAll();
				frame.getContentPane().add(textFin,BorderLayout.SOUTH);
				frame.getContentPane().add(this.mesRecords.getAffichage(),BorderLayout.CENTER);
				frame.revalidate();
				frame.repaint();
			}
			public class MenuMoyenListener implements MenuListener  {

				@Override
				public void menuCanceled(MenuEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void menuDeselected(MenuEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void menuSelected(MenuEvent e) {
					frame.getContentPane().removeAll();
					frame.revalidate();
					frame.repaint();
					mesRecords.closeRecord();
					myTime.stop();
					setPartie(5,3);
					
					
					
					
				}

			}
			public class MenuDifficileListener implements MenuListener  {

				@Override
				public void menuCanceled(MenuEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void menuDeselected(MenuEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void menuSelected(MenuEvent e) {
					myTime.stop();
					frame.getContentPane().removeAll();
					frame.revalidate();
					frame.repaint();
					mesRecords.closeRecord();
				
						setPartie(20,50);
						
						
					
					
				}

			}

			
		
		
	}

		