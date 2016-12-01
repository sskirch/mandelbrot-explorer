package net.steinkirch.Mandelbrot;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ComplexPlanePanel extends JPanel implements MouseListener, MouseMotionListener {

	private int zoomRecWidth;
	private int zoomRecHeight;
	private boolean dragging = false; 
	private BufferedImage setImage;

	private MandelbrotSet mandelbrotSet;

	
	private int dragX =0;
	private int dragY =0;
	private int newDragX=0;
	private int newDragY=0;
	
	
	
	
	private Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
	private Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	
	public ComplexPlanePanel() {
		setBackground(Color.WHITE);
		addMouseListener(this);
		addMouseMotionListener(this);

		
		zoomRecWidth = getWidth()/100;
		zoomRecHeight = getHeight()/100;			
		mandelbrotSet = MandelbrotSet.getInstance();
		
		
		
		mandelbrotSet.setWidth(getWidth()); // Width of the panel.
		mandelbrotSet.setHeight(getHeight()); // Height of the panel.
		
		
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				// This is only called when the user releases the mouse
				// button.
				System.out.println("componentResized");
				zoomRecWidth = getWidth()/10;
				zoomRecHeight = getHeight()/10;
				
				repaint();
			}
		});

	}

	/*
	 * Draw the Mandelbrot Set!!!!
	 * the x,y,x2,y2 cordinates describe the  slice of screeen, or the dimensions of the whole screen 
	 */
	private void drawScreen(Graphics2D g2, int x, int y, int x2, int y2){
		
		if(x2!=0 && y2!=0){
		
			mandelbrotSet.setWidth(getWidth()); // Width of the panel.
			mandelbrotSet.setHeight(getHeight()); // Height of the panel.


			System.out.println("Paint:" + x + " " + y + " " + x2	+ " " + y2);

			for (int yDraw = y; yDraw < y2; yDraw++) {
				for (int xDraw = x; xDraw < x2; xDraw++) {
					
					
					int bd = mandelbrotSet.bindingDepth(xDraw, yDraw); //find out how many iterations it takes for the number to become unbounded.
																	   //The number of iterations will determine the color						
					Color color;
					if (bd < 2) {  // if is immediately bounded, then mark it black
						color = mandelbrotSet.getBoundColor();
					} else {
						color = Gradient.getGradient(mandelbrotSet.getGradientId())[bd];
					}

					g2.setColor(color);
					g2.drawLine((int) xDraw, (int) yDraw, (int) xDraw, (int) yDraw);
				}
			} // x y cycle
			
				
			
			}

		
	}
	
	
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		System.out.println("paintComponent");
		setCursor(hourglassCursor);
				
		super.paintComponent(g2); // Fill with background color (white).
				
		mandelbrotSet.setWidth(getWidth()); // Width of the panel.
		mandelbrotSet.setHeight(getHeight()); // Height of the panel.
				
		
		//Draw the image to a buffer, then display the buffer.
		//This will allow to drag the image around as a separate object
		
				
		setImage = drawSetOnBuffer(mandelbrotSet.getWidth(), mandelbrotSet.getHeight());
		
		g2.drawImage(setImage, 0,  0, null);
		
		
		setCursor(normalCursor);
	} // end paintComponent()


	
	
	
	/*
	 * Draws the set on a image buffer instead of directly to the screen.
	 * This gives us a dragable object.
	 * And lets us render a set of anysize off screen
	 */
	private BufferedImage drawSetOnBuffer(int width,int height) {

	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = bi.createGraphics();
	    drawScreen(g2, 0,0,width,height);		    
	    		    
	    return bi;
	}
	

	public void mousePressed(MouseEvent evt) {
		
		System.out.println("mousePressed" + dragging);
		int x = evt.getX();
		int y = evt.getY();
		dragX = x;
		dragY = y;
		if (dragging == true)  // Ignore mouse presses that occur
	            return;            //    when user is already drawing a curve.
		
		

	} // end mousePressed()

	public void mouseReleased(MouseEvent evt) {
		if(dragging) { //we just dragged something so calculate the new position and redraw.

			System.out.println("mouseReleased" + dragging);
			//translate movement to the units of the current scale
			double scaledNewDragX = (double) newDragX * ((mandelbrotSet.getScaleX()/mandelbrotSet.getWidth())*mandelbrotSet.getScaleXmultiplier());
			double scaledNewDragY = (double) newDragY * mandelbrotSet.getScaleY()/mandelbrotSet.getHeight();
			
			//System.out.println("andelbrotSet.getScaleX() " + mandelbrotSet.getScaleX() + "mandelbrotSet.getScaleY() " + mandelbrotSet.getScaleY());
			//System.out.println("scaledNewDragX " + scaledNewDragX + "scaledNewDragX " + scaledNewDragY);
			
			
			//shift the perspective by the scaled units
			mandelbrotSet.setxShift(mandelbrotSet.getxShift() + scaledNewDragX);
			mandelbrotSet.setyShift(mandelbrotSet.getyShift() + scaledNewDragY);
			
			repaint();
			
			
			dragging = false;
		}else{ //we just clicked on something so zoom and redraw

			System.out.println("mouseReleased" + dragging);
		
			int x = evt.getX() - zoomRecWidth/2; // x-coordinate where the user clicked.
			int y = evt.getY() - zoomRecHeight/2; // y-coordinate where the user clicked.
			
			Graphics2D g2 = (Graphics2D) getGraphics();
			 
			 
	        g2.setColor(Color.BLACK);
	        g2.drawRect(x,y, zoomRecWidth, zoomRecHeight);

			System.out.println("Click: " + x + " " + y);

			mandelbrotSet.moveAndZoom(x, y);
			repaint();
		
		}
		
	}

	public void mouseDragged(MouseEvent evt) {
		dragging = true;
		System.out.println("mouseDragged" + dragging);
		int x = evt.getX();
		int y = evt.getY();
		
		//calculate the new x,y of the top left of the image
		newDragX = x - dragX;
		newDragY = y - dragY;
		
					
		Graphics2D g2 = (Graphics2D) getGraphics();
		g2.setColor(Color.BLACK);
		//Now calculate two rectangles to fill the space left my the moving image.
		//Fill these areas with black space will smooth out the movement and prevent smearing
		
		if(newDragY > 0){   //we moved it down and must fill the top
			g2.fillRect(0,0, getWidth(), newDragY);
		}
		if(newDragY < 0){  //we moved it up and must fill the bottom of the screen
			g2.fillRect(0, getHeight()+ newDragY, getWidth(),getHeight());
		}
		//if newDragY is zero then we didn't move it up or down and don't need to draw anything
		
		//Fill these areas with black space will smooth out the movement and prevent smearing
		if(newDragX > 0){  //we moved it right and must fill the left of the screen
			g2.fillRect(0,0, newDragX, getHeight());
		}
		if(newDragX < 0){   //we moved it Left and must fill the right side of the screen
			
			g2.fillRect(getWidth()+newDragX,0, getWidth(),getHeight());
		}
		//if newDragY is zero then we didn't move it up or down and don't need to draw anything
		
		
		//g.setColor(Color.BLACK);
        //g.fillRect(0,0, getWidth(), getHeight());
		
		g2.drawImage(setImage, newDragX,  newDragY, null);
		
	} // end mouseDragged()

	public void mouseEntered(MouseEvent evt) {						
	} // Some empty routines.

	public void mouseExited(MouseEvent evt) {
	} // (Required by the MouseListener

	public void mouseClicked(MouseEvent evt) {
	} // and MouseMotionListener

	public void mouseMoved(MouseEvent evt) {
	} // interfaces).
	
	
	
	private void writeImgFile(BufferedImage img){
		File outputfile = new File("saved.png");
	    try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

} // End class SimplePaintPanel
