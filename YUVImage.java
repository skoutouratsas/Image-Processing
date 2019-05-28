package ce325.hw2;
import java.io.*;
import java.util.*;

public class YUVImage {
	int width;
	int height;
	YUVPixel [][]image;
	
	public YUVImage(int width, int height){//dimourgia kenou yuvimage
		int i,j;
		
		this.width = width;
		this.height = height;
		
		image = new YUVPixel[height][width];
		
		for (i=0; i<height; i++) {
			
			for (j=0; j<width; j++) {
				
				image[i][j] = new YUVPixel( (short)16, (short)128, (short)128);
				
			}
			
		}
		
	}
	
	public YUVImage(YUVImage copyImg) {//antrigrafi ena ena ta pixel
		int i,j;
		
		this.width = copyImg.width;
		this.height = copyImg.height;
		
		image = new YUVPixel[this.height][this.width];

		
		for (i=0; i<this.height; i++) {
			for (j=0; j<this.width; j++) {
				
				this.image[i][j] = new YUVPixel(copyImg.image[i][j]);
				
			}
		}
	}
	
	public YUVImage(RGBImage RGBImg) {//metatropeas rgb->yuvi
		int i,j;
		this.width = RGBImg.width;
		this.height = RGBImg.height;
		
		image = new YUVPixel[height][width];

		
		for (i=0; i<this.height; i++) {
			for (j=0; j<this.width; j++) {
				
				this.image[i][j] = new YUVPixel(RGBImg.image[i][j]);
				
			}
		}
	}
	
	public YUVImage(java.io.File file) {//dimiourgia yuvimage apo file
		int i,j;
		
		try{
			
			Scanner sc = new Scanner(file);
			
			if (!sc.next().equals("YUV3")) {
				throw new UnsupportedFileFormatException();
			}
		
		
			if (sc.hasNext()) {
				this.width = Integer.parseInt(sc.next());
			}
			if (sc.hasNext()) {
				this.height = Integer.parseInt(sc.next());
			}

			
			this.image = new	YUVPixel[this.height][this.width];

			for (i=0; i<this.height; i++) {
				for (j=0; j<this.width; j++) {
					
					this.image[i][j] = new YUVPixel((short)(16), (short)(128), (short)(128));
					
					if (sc.hasNext())
						this.image[i][j].setY(Short.parseShort(sc.next()));
					if (sc.hasNext())
						this.image[i][j].setU(Short.parseShort(sc.next()));
					if (sc.hasNext())
						this.image[i][j].setV(Short.parseShort(sc.next()));
				}
			}
		}
		catch (UnsupportedFileFormatException ex) {
			System.out.println("Unsupported File Exception!");
		}
		catch(FileNotFoundException ex) {
			System.out.println("The specified file was not found");
		}
	}
	
	public String toString() {//xristi StringBuffer gia taxitita
		StringBuffer out = new StringBuffer();
		int i,j;
		
		out.append("YUV3" + "\n" + this.width + " " + this.height ); 
		for (i=0; i<this.height; i++) {
			for (j=0; j<this.width; j++) {
				out .append("\n" + this.image[i][j].getY() + " " + this.image[i][j].getU() + " " + this.image[i][j].getV());
			}
		}
		
		
		return(out.toString());
		
	}
	
	public void toFile(java.io.File file) {
		try{
			PrintWriter pw = new PrintWriter(file);
			pw.println(toString());
			pw.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("The specified file was not found");
		}
	}
	
	public void equalize() {// dimiourgia histogram kai eksisoropisi simfwna me auto
		int i,j;
		
		YUVImage temp = new YUVImage(this);
		Histogram hist = new Histogram(temp);
		hist.equalize();
		
		for (i=0; i<this.height; i++) {
			for (j=0; j<this.width; j++) {
				
				this.image[i][j] = new YUVPixel(hist.equalizedImg.image[i][j]);
				
			}
		}
		
	}
	
	
	
	
	

}