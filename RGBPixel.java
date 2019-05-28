package ce325.hw2;
public class RGBPixel{
	private int colors;
	
	//klasi enos aplou RGBPixel. h enthesi timwn ston int ginetai me olisthiseis
	public RGBPixel(short red, short green, short blue) {
	
		this.colors = blue;
		this.colors += green<<8;
		this.colors += red<<16;
	
	}
	
	public RGBPixel(RGBPixel pixel) {
		
		this.colors = pixel.colors;
		
	}
	
	public RGBPixel(YUVPixel pixel) { //metatropi timwn yuv se rgb. ilopoihsh clip me if
		int c,d,e,red,green,blue;
		
		
		
		c = pixel.getY() - 16;
		d = pixel.getU() - 128;
		e = pixel.getV() - 128;
		
		red = (( 298 * c + 409 * e +128)>>8);
		green = (( 298 * c - 100 * d - 208 * e + 128)>>8);
		blue = (( 298 * c + 516 * d + 128)>>8);
		
		if (red < 0) red = 0;
		if (red > 255) red = 255;
		if (green < 0) green = 0;
		if (green > 255) green = 255;
		if (blue < 0) blue = 0;
		if (blue > 255) blue = 255;
		
		this.colors = blue;
		this.colors += green<<8;
		this.colors += red<<16;
		
		
		
	}
	//setters getters
	public short getRed() {
		
		return (short)(this.colors>>16);
		
	}
	
	public short getGreen() {
		
		return (short)((this.colors>>8)-(this.getRed()<<8));
		
	}
	
	public short getBlue() {
		
		return (short)(this.colors-(this.getGreen()<<8)-(this.getRed()<<16));
		
	}
	
	public void setRed(short red) {
		
		this.colors = this.colors - (this.getRed()<<16) + (red<<16);
		
	}
	
	public void setGreen(short green) {
		
		this.colors = this.colors - (this.getGreen()<<8) + (green<<8);
		
	}
	
	
	public void setBlue(short blue) {
		
		this.colors = this.colors - this.getBlue() + blue;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}