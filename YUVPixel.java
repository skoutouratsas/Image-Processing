package ce325.hw2;
public class YUVPixel{
	private int yuv;
		//klasi enos aplou YUVpixes. h enthesi timwn ston int ginetai me olisthiseis

	public YUVPixel(short Y, short U, short V) {
	
		this.yuv = V;
		this.yuv += U<<8;
		this.yuv += Y<<16;
	
	}
	
	public YUVPixel(YUVPixel pixel) {
		
		this.yuv = pixel.yuv;
		
	}
	
	public YUVPixel(RGBPixel pixel) {//metatropi rgb timws se yuv
		this.yuv = ( ( 112 * pixel.getRed() -  94 * pixel.getGreen() -  18 * pixel.getBlue() + 128) >> 8) + 128;
		this.yuv += ( ( ( -38 * pixel.getRed() -  74 * pixel.getGreen() + 112 * pixel.getBlue() + 128) >> 8) + 128)<<8;
		this.yuv += ( ( (  66 * pixel.getRed() + 129 * pixel.getGreen() +  25 * pixel.getBlue() + 128) >> 8) +  16 )<<16;
	}
	//setter getters
	public short getY() {
		return (short)(this.yuv>>16);
		
	}
	
	public short getU() {
		
		return (short)((this.yuv>>8) - (this.getY()<<8));
		
	}
	
	public short getV() {
		
		return (short)(this.yuv - (this.getU()<<8) - (this.getY()<<16));
		
	}
	
	public void setY(short Y) {
		
		this.yuv = this.yuv - (this.getY()<<16) + (Y<<16);
		
	}
	
	public void setU(short U) {

		this.yuv = this.yuv - (this.getU()<<8) + (U<<8);
		
	}
	
	
	public void setV(short V) {
		
		this.yuv = this.yuv - this.getV() + V;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}