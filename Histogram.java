package ce325.hw2;
import java.io.*;




public class Histogram {
	int []instances;
	int maxBright;
	int []meanValue;
	float []prob;
	float []sumProb;
	YUVImage equalizedImg;
	
	
	public Histogram(YUVImage img) {//katametrisi emfanisewn ekastote fotinotitas
		int i,j;
		
		this.instances = new int[236];
		
		
		for (i=0; i<img.height; i++) {
			for (j=0; j<img.width; j++) {
				

				this.instances[img.image[i][j].getY()]++;
				
				
			}
		}
		
		this.equalizedImg = new YUVImage(img);
		
	}
	
	
	
	public String toString() {//80 * gia tin pio suxni fotinotita kai analogika me auth emfanizoume kai gia tis alles fotinotites
		int i,j,maxInstances,maxInstancesIndex;
		StringBuffer out = new StringBuffer();
		
		maxInstances = instances[0];
		for (i=1; i<236; i++){
			if(instances[i]>maxInstances){
				maxInstances = instances[i];
				maxInstancesIndex = i;
			}
		}
		
		for (i=0; i<236; i++) {
			out.append("\n" + instances[i]);
			j=0;

			while ( j < (int)(Math.ceil((float)(instances[i]*80)/maxInstances))) {
				out.append("*");
				j++;
			}
		}
		return(out.toString());
		
	}
	
	
	
	public void toFile(File file) {
		try{
			PrintWriter pw = new PrintWriter(file);
			pw.println(toString());
			pw.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("The specified file was not found");
		}
		
	}
	
	
	public void equalize() {
		int i,j;
		
		prob = new float[236];
		sumProb = new float[236];
		
		
		for (i=0; i<236; i++) {//upologismos pi8anotitas gia ka8e fotinotita
			this.prob[i] = (float)(this.instances[i])/(float)(this.equalizedImg.height * this.equalizedImg.width);
		}
		
		sumProb[0]=prob[0];
		for (i=1; i<236; i++) {//a8roistiki pi8anotita
			
			
				sumProb[i] = sumProb[i-1] + prob[i];
			
			
			
		}
		
		for (i=235; i>=0; i--) { // euresi pio lamprou pixel. 
			
			if (this.instances[i] > 0) {
				maxBright = i;
				break;
			}
		}
			
		
		meanValue = new int[236];
		
		for (i=0; i<236; i++) {// dimiourgia neou eksisoropimenou pinaka
			meanValue[i] = (int)(sumProb[i] * maxBright);
		}
		
		
		for (i=0; i<this.equalizedImg.height; i++) {// allagi eikonas simfwna me pinaka meanValue
			for (j=0; j<this.equalizedImg.width; j++) {
				
				equalizedImg.image[i][j].setY(getEqualizedLuminocity(equalizedImg.image[i][j].getY())); 
				
			}
			
		}	
	}
	
	
	public short getEqualizedLuminocity(int luminocity) {//euresi timwn apo meanValue
		
		return ((short)(meanValue[luminocity]));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}