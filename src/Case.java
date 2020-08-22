import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Case extends JButton implements MouseListener{

	private int adjacents;
	private int testAdjacents;
	int i;
	int j;
	boolean bombe;
	boolean bombeTestDrapeu;
	private boolean drapeau;
	JeuTerrain currentFrame;
	boolean visible;
	Case(JeuTerrain jeu,int i,int j)
	{
		this.i=i;
		this.j=j;
		this.drapeau=false;
		this.bombeTestDrapeu=false;
		currentFrame=jeu;
		this.adjacents=0;
		this.visible=false;
		this.bombe=false;
		this.addMouseListener(this);
	}
	//Bombe + Drapeau
	public boolean getDrapeau()
	{
		if( this.drapeau ){
			return true;
		}
		return false;
	}
	public boolean getDrapeauBombe()
	{
		if(this.bombe && this.drapeau)
		{
			return true;
		}
		if(!this.bombe&& this.drapeau){
			currentFrame.PartiePerdu();
		}
		if(this.bombe){
			currentFrame.PartiePerdu();
		}
		
			return this.visible;
		
	}
	public boolean getBombe(){
		return this.bombe;
	}
	public void setBombe(){
		this.bombe=true;
		this.adjacents=-1;
	}
	public void setAdja(int adja){
		this.adjacents=adja;
		this.setTestAdjacents(adja);
		
		
	}
	public int getAdja()
	{
		return adjacents;
	}
	public void setText()
	{
			if(!this.bombe)
			{
			Integer obj = new Integer(this.adjacents); 
			String s = obj.toString();
			ImageIcon img = new ImageIcon("images/vide.jpg");
			this.setIcon(img);
			this.setText(s);
			this.setHorizontalTextPosition(SwingConstants.CENTER);
			this.setFont(new Font("Arial", Font.PLAIN, 10));
		    this.setForeground(Color.RED);
			}
		    if(this.bombe){
				
				this.setIcon(currentFrame.bombeView);
		    }
	}
	

	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(!currentFrame.getDecouverte())
		{
			if(!drapeau)
			{
				drapeau=true;
				
				this.setIcon(currentFrame.drapeauView);
			}
			else
			{
				
				drapeau=false;
				this.setIcon(null);
				
			}
			if(this.bombe && drapeau)
			{
				currentFrame.restantNeg();
			}
			
		}
		if(!visible && currentFrame.getDecouverte() && !this.bombe)
		{
			this.visible=true;
			currentFrame.restantNeg();
			this.setText();
		}
		if(currentFrame.getDecouverte()&& this.bombe){
			
			this.setIcon(currentFrame.bombeView);
			currentFrame.PartiePerdu();
			
			}
		if(currentFrame.getDecouverte() && !this.bombe){
			
			currentFrame.calculAdjaTest(this.i,this.j);
			currentFrame.AdjacentVide(this.i, this.j);
			//currentFrame.testFin(this.i,this.j);
			this.testAdjacents=this.adjacents;
		}
		
		
	}
	public void setVisible(){
		this.visible=true;
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @return the testAdjacents
	 */
	public boolean getVisible(){
		return this.visible;
	}
	public int getTestAdjacents() {
		return testAdjacents;
	}
	public void getTestAdjacentsMoins() {
		this.testAdjacents--;
	}
	/**
	 * @param testAdjacents the testAdjacents to set
	 */
	public void setTestAdjacents(int testAdjacents) {
		this.testAdjacents = testAdjacents;
	}

}
