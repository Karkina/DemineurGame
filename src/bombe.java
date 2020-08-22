
public class bombe {
	int posx;
	int posy;
	int adjacents;
	boolean bombe;
	boolean visible;
	
	public bombe(){
		this.posx=0;
		this.posy=0;
		this.adjacents=0;
		this.bombe=false;
		this.visible=false;
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
	}
	public int getAdja()
	{
		return adjacents;
	}
	
}
