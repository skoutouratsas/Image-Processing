package ce325.hw2;
import java.io.*;
import java.util.*;


public class PPMImage extends RGBImage {
	

	public PPMImage(java.io.File file) {//dimiourgia enos PPMImage apo file
		super(0,0,0);
		int i,j;
		
		try{
			
			Scanner sc = new Scanner(file);


			if (!sc.next().equals("P3")) {
				throw new UnsupportedFileFormatException();
			}

		
			if (sc.hasNext()) {
				this.width = Integer.parseInt(sc.next());
			}
			if (sc.hasNext()) {
				this.height = Integer.parseInt(sc.next());
			}
			if (sc.hasNext()) {
				this.colordepth = Integer.parseInt(sc.next());
			}
			
			
			
			this.image = new	RGBPixel[this.height][this.width];
			

			for (i=0; i<this.height; i++) {
				for (j=0; j<this.width; j++) {
					this.image[i][j] = new RGBPixel( (short)0, (short)0, (short)0);

					if (sc.hasNext())
						this.image[i][j].setRed(Short.parseShort(sc.next()));
					if (sc.hasNext())
						this.image[i][j].setGreen(Short.parseShort(sc.next()));
					if (sc.hasNext())
						this.image[i][j].setBlue(Short.parseShort(sc.next()));
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
	
	
	
	public PPMImage(RGBImage img) { // antigrafi ena ena ta pixel enos RGBImage
		super(img.width, img.height, img.colordepth);
		int i,j;
		
		
		for (i=0; i<img.height; i++) {
			for (j=0; j<img.width; j++) {
				
				super.image[i][j] = img.image[i][j];
				
			}
		}
	}
	
	public PPMImage(YUVImage img) { //klisi ston metatropea tis RGBImage
		super(img);
	}
	
	public  String toString() { // xrisi StringBuffer gia taxitita
		StringBuffer out = new StringBuffer();
		int i,j;
		
		out.append("P3" + "\n" + super.width + " " + super.height + " " + super.colordepth); 
		for (i=0; i<super.height; i++) {
			for (j=0; j<super.width; j++) {
				out.append("\n" + super.image[i][j].getRed() + " " + super.image[i][j].getGreen() + " " + super.image[i][j].getBlue());
			}
		}
		
		return(out.toString());
		
	}
	
	public void toFile(java.io.File file) { //save
		try{
			PrintWriter pw = new PrintWriter(file);
			pw.println(toString());
			pw.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("The specified file was not found");
		}
		
		
		
	}
	
	
}