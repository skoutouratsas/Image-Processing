package ce325.hw2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


//grafiko periballon gia to programma

public class ImageProcessing extends JFrame implements ActionListener {
	private JMenuItem item1 = new JMenuItem("PPM File");
	private JMenuItem item2 = new JMenuItem("YUV File");
	private JMenuItem item3 = new JMenuItem("PPM File");
	private JMenuItem item4 = new JMenuItem("YUV File");
	private JMenuItem item5 = new JMenuItem("Grayscale");
	private JMenuItem item6 = new JMenuItem("Increase Size");
	private JMenuItem item7 = new JMenuItem("Decrease Size");
	private JMenuItem item8 = new JMenuItem("Rotate Clockwise");
	private JMenuItem item9 = new JMenuItem("Equalize Histogram");
	private JMenuItem item10 = new JMenuItem("Select directory");
	JFileChooser F = new JFileChooser(".");
	JFileChooser F1 = new JFileChooser();
	PPMImage modifiedImagePpm; 
	File file;
	boolean ppmflag;
	
	public ImageProcessing() {
		//diamorfosi menu
		JFrame header = new JFrame(" Image Processing ");
		JMenuBar menu = new JMenuBar();
		JMenu fl = new JMenu("File");
		JMenu act = new JMenu("Actions");
		
		JMenu open = new JMenu("Open");
		JMenu save = new JMenu("Save");
		JMenu stak = new JMenu("Stacking Algorithm");

		
		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);
		item5.addActionListener(this);
		item6.addActionListener(this);
		item7.addActionListener(this);
		item8.addActionListener(this);
		item9.addActionListener(this);
		item10.addActionListener(this);
		
		
		menu.add(fl);
		menu.add(act);
		
		
		fl.add(open);
		fl.add(save);
		
		act.add(stak);
		act.add(item5);
		act.add(item6);
		act.add(item7);
		act.add(item8);
		act.add(item9);
		
		open.add(item1);
		open.add(item2);
		save.add(item3);
		save.add(item4);
		
		stak.add(item10);
		
		
		header.setJMenuBar(menu);
		header.setSize(300,100);
		header.setVisible(true);
		

		
	}
	
	
	//action handles
	public void actionPerformed(ActionEvent E) {
		
		if (E.getSource() == item1) {
			JFileChooser F = new JFileChooser(".");
			F.showOpenDialog(null);
			file = F.getSelectedFile();
			
			modifiedImagePpm = new PPMImage(file);
			
			displayImage(modifiedImagePpm);

		}
			
			
		if (E.getSource() == item2) {
			JFileChooser F = new JFileChooser(".");
			F.showOpenDialog(null);
			file = F.getSelectedFile();
			
			YUVImage temp = new YUVImage(file);
			modifiedImagePpm = new PPMImage(temp);
			
			displayImage(modifiedImagePpm);
			
		}
			
		if (E.getSource() == item3) {
			
			modifiedImagePpm.toFile(file);
			displayImage(modifiedImagePpm);
			
			
		}
		
		if (E.getSource() == item4) {
			
			YUVImage converted = new YUVImage(modifiedImagePpm);
			
			converted.toFile(file);
			
			
			displayImage(modifiedImagePpm);
			
		}
			
			
			
			
		
		
		if (E.getSource() == item5) {
							
				modifiedImagePpm.grayscale();			
				displayImage(modifiedImagePpm);
				

		}
		
		if (E.getSource() == item6) {
							
				modifiedImagePpm.doublesize();
				displayImage(modifiedImagePpm);
				
		}
		
		if (E.getSource() == item7) {
				
				modifiedImagePpm.halfsize();
				displayImage(modifiedImagePpm);
				
		}
		
		if (E.getSource() == item8) {
				
				modifiedImagePpm.rotateClockwise();
				displayImage(modifiedImagePpm);
				
		}
		
		if (E.getSource() == item9) {
				
				YUVImage yuvimage = new YUVImage(modifiedImagePpm);
				
				yuvimage.equalize();
				
				modifiedImagePpm = new PPMImage(yuvimage);
				
				
				displayImage(modifiedImagePpm);
		}
		
		
		
		
		
		
		
			if (E.getSource() == item10) {
				
			
				F1.setCurrentDirectory(new java.io.File("."));
				F1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				F1.showOpenDialog(null);
			

			
			
				F1.setAcceptAllFileFilterUsed(false);
						
				if (F1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
					
					 F1.getCurrentDirectory();
			 
					file = F1.getSelectedFile();
				}
   
    
			
				PPMImageStacker stackedImage = new PPMImageStacker(file);
					
				File newFile = new File("stackedImage.ppm");
				
				stackedImage.getStackedImage().toFile(newFile);
				
				displayImage(stackedImage.getStackedImage());
		
		}
		
		
		
	
	}
	
	//low level metatropi PPMImage se Buffered Image
	public static BufferedImage ppmToBufferedImage(PPMImage im) {
	int i,j;
	BufferedImage bi = new BufferedImage(im.width ,im.height, BufferedImage.TYPE_INT_RGB);
	for(i=0; i<im.height; i++){
		for(j=0; j<im.width; j++){
			
			bi.setRGB(j,i, (im.image[i][j].getRed() << 16) | (im.image[i][j].getGreen() << 8) |  im.image[i][j].getBlue());
			
		}
	}
	 return bi;
}
	//emfanisi PPM eikonas
	public void displayImage(PPMImage im){
	 BufferedImage img = ppmToBufferedImage(im);
	 ImageIcon icon = new ImageIcon(img);
	 JLabel label = new JLabel(icon);
	 JOptionPane.showMessageDialog(null, label);
	}
	
	

	
	
	
	
	
	
	
	
	
	public static void main(String args[]) {
		
		new ImageProcessing();
		
		
		
	} 
	
}
