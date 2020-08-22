
public class terrain {
	bombe[][] terrain;
	final int taille=10;
	final int bombes=20;
	
	public terrain(){
		//case vide terrain
		this.terrain=new bombe[this.taille][this.taille];
		for(int i=0;i<this.taille;i++){
			for(int j=0;j<this.taille;j++){
				terrain[i][j]=new bombe();
			}
		}
		int random1=(int) (Math.random()*10);
		int random2=(int) (Math.random()*10);
		int testBombe=0;
		while(bombes!=testBombe)
		{
			
			if(!this.terrain[random1][random2].getBombe())
			{
				this.terrain[random1][random2].setBombe();
				testBombe++;
			}
			random1=(int) (Math.random()*10);
			random2=(int) (Math.random()*10);
		}
		 
		//calcul adja
		for(int i=0;i<this.taille;i++){
			for(int j=0;j<this.taille;j++){
				this.calculAdja(i, j);
			}
		}
		
		
	}
	
	public void calculAdja(int i, int j){
		int adja=0;
				if(!this.terrain[i][j].getBombe())
				{
					if(i-1>0)
					{
						
						if(this.terrain[i-1][j].getBombe()){
							adja++;
						}
						if(j+1<this.taille)
						{
							if(this.terrain[i][j+1].getBombe())
								adja++;
							if(this.terrain[i-1][j+1].getBombe())
								adja++;
						}
						if(j-1>0){
							if(this.terrain[i][j-1].getBombe())
								adja++;
							if(this.terrain[i-1][j-1].getBombe())
								adja++;
						}
					}
					
				
					if(i+1<this.taille)
					{
						
						if(this.terrain[i+1][j].getBombe())
							adja++;
						
						if(j+1<this.taille)
						{
							if(this.terrain[i+1][j+1].getBombe())
								adja++;
						
						}
						if(j-1>0)
						{
							if(this.terrain[i+1][j-1].getBombe())
								adja++;
						}

					}
					this.terrain[i][j].setAdja(adja);
				}
		
		}
	public void afficherTerrain()
	{
		for(int i=0;i<this.taille;i++){
			for(int j=0;j<this.taille;j++)
			{
				System.out.printf("%d",this.terrain[i][j].getAdja());
				System.out.print(" | ");
				
			}
			System.out.printf("\n");
		}
		
	}
	
	
	
}
	
		
		
	

