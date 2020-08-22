
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ListeRecord {
	
	
	public PrintWriter mesRecords;
	private JPanel affichageRecords;
	ListeRecord()
	{
		
			 FileWriter fw;
			try {
				fw = new FileWriter("record.txt", true);
				 BufferedWriter bw = new BufferedWriter(fw);
				  mesRecords = new PrintWriter(bw);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			affichageRecords=new JPanel((new GridLayout(10,1)));
			
	
			try {
				getScore();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}
	
	public void ajouterRecord(String time)
	{
		
		this.mesRecords.println("Record :"+ time);
		this.mesRecords.close();
	}
	
	
	public void closeRecord(){
		mesRecords.close();
	}
	
	public void getScore() throws IOException
	{
		BufferedReader test=null;
		JLabel tonRecord=new JLabel();
		String ligne;
		String last=null;
		affichageRecords.add(tonRecord);
		try {
			test=new BufferedReader(new FileReader("record.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while((ligne=test.readLine())!=null)
		{
			JLabel recordLine=new JLabel();
			recordLine.setText(ligne);
			recordLine.setForeground(Color.red);
			recordLine.setHorizontalAlignment(SwingConstants.CENTER);
			recordLine.setFont(new Font("Arial", Font.PLAIN, 20));
			last=ligne;
			affichageRecords.add(recordLine);
		}
		tonRecord.setText("ton "+last);
		tonRecord.setForeground(Color.green);
		tonRecord.setHorizontalAlignment(SwingConstants.CENTER);
		tonRecord.setFont(new Font("Arial", Font.PLAIN, 20));
		test.close();
		
		
	}
	public JPanel getAffichage()
	{
		return affichageRecords;
	}

}
