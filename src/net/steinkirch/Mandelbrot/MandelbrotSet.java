package net.steinkirch.Mandelbrot;

import java.awt.Color;

public class MandelbrotSet {
	
	private static MandelbrotSet setInstance = null;
	
	
	
	private double scaleX;	
	private double scaleY;
	private double scaleXmultiplier; 
	private double xShift;  
	private double yShift;
	private double Zoom;
	private int width; // Width of the panel/screen.
	private int height; // Height of the panel/screen.
	
	private int gradientId;
	private Color boundColor;
	
	
	private int numberOfCycles;
	private int distance;
	
	
	public Color getBoundColor() {
		return boundColor;
	}

	public void setBoundColor(Color boundColor) {
		this.boundColor = boundColor;
	}

	public int getGradientId() {
		return gradientId;
	}

	public void setGradientId(int gradientId) {
		this.gradientId = gradientId;
	}

	public double getScaleX() {
		return scaleX;
	}

	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}

	public double getScaleY() {
		return scaleY;
	}

	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}

	public double getScaleXmultiplier() {
		return scaleXmultiplier;
	}

	public void setScaleXmultiplier(double scaleXmultiplier) {
		this.scaleXmultiplier = scaleXmultiplier;
	}

	public double getxShift() {
		return xShift;
	}

	public void setxShift(double xShift) {
		this.xShift = xShift;
	}

	public double getyShift() {
		return yShift;
	}

	public void setyShift(double yShift) {
		this.yShift = yShift;
	}

	public double getZoom() {
		return Zoom;
	}

	public void setZoom(double zoom) {
		Zoom = zoom;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		this.scaleXmultiplier = (double) this.width / (double) this.height;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		this.scaleXmultiplier = (double) this.width / (double) this.height;
	}

	
	
	private int unBounded(double realX, double imaginaryY, double depth) {
		double First, Inside, Outside, Last, Distance2;
		double real2, imaginary2;
		int Bounded = 0;
		int Counter = 0;

		real2 = realX;
		imaginary2 = imaginaryY;

		while ((Counter < depth) && Bounded == 0) {
			
			// f c(z) = z2 + c
			Counter++;
							
			First = realX * realX;
			Inside = realX * imaginaryY;
			Outside = realX * imaginaryY;
			Last = imaginaryY * imaginaryY * -1;

			realX = First + Last;
			imaginaryY = Inside + Outside;

			realX += real2;
			imaginaryY += imaginary2;

			Distance2 = Math.sqrt((realX * realX) + (imaginaryY * imaginaryY));
			if (Distance2 > distance) {
				Bounded = (int) (500*(((double)Counter/depth))) - 1;
			} else {
				Bounded = 0;
			}
		}

		return (Bounded);
	} // end unBounded function

	
	/*
	 * Get the number of cycles that it took to determine if the coordinate is bounded.
	 * This will correspond to the color of the the pixel in the final rendering
	 */
	public int bindingDepth (int screenX, int screenY){  
		//first get the Cartesian coordinates on the complex plane.  X is X and Y is imaginary.
		double real = (double) ((scaleX * scaleXmultiplier) / 2) - ((double)screenX * ((scaleX * scaleXmultiplier) / width)) + xShift;
		double imaginary = (double) scaleY / 2 - ((double)screenY * ((scaleY) / height)) + yShift;
	
		return unBounded(real, imaginary, numberOfCycles);
	}
	
	public void moveAndZoom(int screenX, int screenY){
		xShift += ((scaleX * scaleXmultiplier) / 2) - ((double)screenX + ((width * Zoom) / 2)) * ((scaleX * scaleXmultiplier) / width);
		yShift += (scaleY / 2) - ((double)screenY + ((height * Zoom) / 2)) * (scaleY / height);

		scaleX = scaleX * Zoom;
		scaleY = scaleY * Zoom;
	}
	
	
	private MandelbrotSet(){
		scaleX = 3;	 //Starting size of the complex plane 
		scaleY = 3;	//Starting size of the complex plane	
		xShift = -0.4;  //Move it to the left a little so that it is centered in the panel.  The Mandelbrot set is not centered on the Cartesian plane.
		yShift = 0;
		Zoom = 0.1;		
		boundColor = Color.BLACK;
		gradientId = Gradient.ID_RAINBOW;
		
		numberOfCycles = 500;
		distance = 2;
		
	}
	
	public static MandelbrotSet getInstance() {
		if(setInstance==null){
			setInstance = new MandelbrotSet();
			return setInstance;
		}else{
			return setInstance;
		}
			
	}
	
	
	
	
}
