package ce325.hw2;

public class RGBImage  {
	
	int width;
	int height;
	int colordepth;
	RGBPixel image[][] ;
	
	public RGBImage(int width, int height, int colordepth)  {//dimiourgia kenou RGBImage
		int i,j;
		
		this.width = width;
		this.height = height;
		this.colordepth = colordepth;
		
		image = new RGBPixel[this.height][this.width];
		
		for (i=0; i<this.height; i++) {
			for (j=0; j<this.width; j++) {
				image[i][j] = new RGBPixel( (short)0, (short)0, (short)0);
			}
		}
				
	}
	
	public RGBImage(RGBImage copyImg) {// copy ena ena ta pixel
		int i,j;
		
		this.width = copyImg.width;
		this.height = copyImg.height;
		this.colordepth = copyImg.colordepth;
		
		image = new RGBPixel[this.height][this.width];

		
		for (i=0; i<this.height; i++) {
			for (j=0; j<this.width; j++) {
				
				this.image[i][j] = new RGBPixel(copyImg.image[i][j]);
				
			}
		}
		
	}
		
		
	public RGBImage(YUVImage YUVImg) {//metatropeas yuv->rgb
		int i,j;
		
		this.width = YUVImg.width;
		this.height = YUVImg.height;
		this.colordepth = 255;
		
		image = new RGBPixel[this.height][this.width];

		
		for (i=0; i<this.height; i++) {
			for (j=0; j<this.width; j++) {
				
				this.image[i][j] = new RGBPixel(YUVImg.image[i][j]);
				
			}
		}
		
	}	
		
	public void grayscale() {// upologismos gray timis kai ekxwrisi tis se ka8e pixel ksexwrista
		int i,j;
		short gray;
		
		for (i=0; i<this.height; i++) {
			for (j=0; j<this.width; j++) {
				
				gray = (short)(this.image[i][j].getRed() * 0.3 + this.image[i][j].getGreen() * 0.59 + this.image[i][j].getBlue() * 0.11);
				this.image[i][j].setRed(gray);
				this.image[i][j].setBlue(gray);
				this.image[i][j].setGreen(gray);
				
			}
		}
	
	}
	
	public void doublesize() {// antigrafi enos pixes sta 3 geitonika tou
		int i,j;
		RGBImage temp;
		temp = new RGBImage(this.width*2, this.height*2, this.colordepth);
		
		for (i=0; i<this.height; i++) {
			for (j=0; j<this.width; j++) {
				
				temp.image[2*i][2*j] = this.image[i][j];
				temp.image[2*i+1][2*j] = this.image[i][j];
				temp.image[2*i][2*j+1] = this.image[i][j];
				temp.image[2*i+1][2*j+1] = this.image[i][j];
				
			}
		}
		this.height = this.height * 2;
		this.width = this.width * 2;
		this.image =  new RGBPixel[this.height][this.width];// nea megaliteri eikona
		this.image = temp.image;
		
	}

	public void halfsize() {
		int i,j;
		RGBImage temp;
		temp = new RGBImage(this.width/2, this.height/2, this.colordepth);
		
		for (i=0; i<this.height/2; i++) {
			for (j=0; j<this.width/2; j++) {
				temp.image[i][j].setRed((short)((this.image[2*i][2*j].getRed() + this.image[2*i+1][2*j].getRed() + this.image[2*i][2*j+1].getRed() + this.image[2*i][2*j].getRed())/4));
				temp.image[i][j].setBlue((short)((this.image[2*i][2*j].getBlue() + this.image[2*i+1][2*j].getBlue() + this.image[2*i][2*j+1].getBlue() + this.image[2*i][2*j].getBlue())/4));
				temp.image[i][j].setGreen((short)((this.image[2*i][2*j].getGreen() + this.image[2*i+1][2*j].getGreen() + this.image[2*i][2*j+1].getGreen() + this.image[2*i][2*j].getGreen())/4));
		
			}
		}
		
		this.height = this.height / 2;
		this.width = this.width / 2;
		this.image =  new RGBPixel[this.height][this.width];//dimiourgia mikroteris eikonas
		this.image = temp.image;
		
	}
	
	public void rotateClockwise() {//kanoume ka8e to teleutaio  row tou original pinaka prwto column ston rotated k.o.k.
		int temp_height, i, j;
		RGBImage temp;
		temp = new RGBImage(this.height, this.width, this.colordepth);
		
		for (i=0; i<this.height; i++) {
			for (j=0; j<this.width; j++) {
				temp.image[j][i] = this.image[this.height-i-1][j];
			}
		}
		
		this.image =  new RGBPixel[this.height][this.width];
		this.image = temp.image;
		
		temp_height = this.height;
		this.height = this.width;
		this.width = temp_height;
		
	}
	
}
	
	
	
	
	
	
	
	
	
	
	



	
	
	
	
	
	
	
	


