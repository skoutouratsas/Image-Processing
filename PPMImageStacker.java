package ce325.hw2;
import java.util.*;
import java.io.*;


public class PPMImageStacker {
	List<PPMImage> imageList = new ArrayList<>();
	public RGBImage stackedImage;
	
	public PPMImageStacker(java.io.File dir) {
		File []fileArray;
		int i;
		
		
		if (!dir.exists()) 
			System.out.println(" [ERROR] Directory " + dir.getName() + "does not exist!\n");
		
		if (!dir.isDirectory())
			System.out.println(" [ERROR] " + dir.getName() + "is not a directory!\n");
		
		fileArray = new File[dir.listFiles().length];
		fileArray = dir.listFiles();
		
		for(i=0; i<fileArray.length; i++) {//pros8iki eikonwn stin lista
			imageList.add(new PPMImage(fileArray[i]));
		}
		
		
	}
	
	public void stack(){
		int i,j,k;
			
		
		
		stackedImage = new RGBImage(imageList.get(0).width, imageList.get(0).height, imageList.get(0).colordepth);
		
		for(i=0; i<imageList.get(0).height; i++) {//upologismos mesou orou gia ka8e pixel
			for(j=0; j<imageList.get(0).width; j++) {
				
				int sumR = 0, sumG = 0, sumB = 0;
				
				for(k=0; k<imageList.size(); k++) {
					
					sumR += imageList.get(k).image[i][j].getRed();
					sumG += imageList.get(k).image[i][j].getGreen();
					sumB += imageList.get(k).image[i][j].getBlue();
					
				}
				
				stackedImage.image[i][j].setRed((short)(sumR/(k+1)));
				stackedImage.image[i][j].setGreen((short)(sumG/(k+1)));
				stackedImage.image[i][j].setBlue((short)(sumB/(k+1)));
			}
		}
	
	}
	
	public PPMImage getStackedImage() {
		stack();
		return new PPMImage(this.stackedImage);
		
	}
	
	
	
	
	
}