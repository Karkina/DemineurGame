
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class TerrainCanvas extends Canvas implements MouseListener {
		int posx;
		int posy;
		int predx;
		int predy;
		
		public TerrainCanvas()
		{
			this.posx=0;
			this.posy=0;
			addMouseListener(this);
			
		}
	public void paint(Graphics g)
	{
		g.clearRect(this.predx, this.predy, 50, 50);
		g.setColor(Color.GRAY);
		g.fill3DRect(this.posx,this.posy, 50, 50,false);
		this.predx=this.posx;
		this.predy=this.posy;
		
	}
	public void paintColor(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.clearRect(this.predx, this.predy, 50, 50);
		g.fill3DRect(this.posx,this.posy, 50, 50,true);
	}
	public void paintOut(Graphics g)
	{
		g.clearRect(0,0, this.getHeight(),this.getWidth());
		g.drawString("Revenez dans la zone", 400, 300);
	}
	public void paintIn(Graphics g)
	{
		g.clearRect(0,0, this.getHeight(),this.getWidth());
		paint(this.getGraphics());
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if((e.getX()>this.posx && e.getX()<this.posx+50) && (e.getY()>this.posy && e.getY()<this.posy+50))
		{
			paintColor(this.getGraphics());
		}
		else
		{
			this.posx=e.getX();
			this.posy=e.getY();
			paint(this.getGraphics());	
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		paintIn(this.getGraphics());
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		paintOut(this.getGraphics());
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
