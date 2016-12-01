package net.steinkirch.Mandelbrot;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class mandelbrot extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MandelbrotSet mandelbrotSet;
	private ComplexPlanePanel complexPlanePanel;
	private JFrame window;
	private JFrame boundColorChooser;
	
	
public static void main(String[] args) {
	int x = 2;
	System.out.println("main" + 2);
	JFrame window;
		window = new JFrame("Simple Paint");
		ComplexPlanePanel content = new ComplexPlanePanel();
		window.setContentPane(content);
		window.setSize(1600, 1000);
		window.setLocation(100, 100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setBackground(Color.RED);

	}

	public void init() {
		
		setContentPane(complexPlanePanel);
		
	}

	public mandelbrot(){
		complexPlanePanel = new ComplexPlanePanel();

		mandelbrotSet = MandelbrotSet.getInstance();
		
		initUI();
	}
	
	private void initUI() {
		
		

		//boundColorChooser = new JFrame("Java Swing Examples");
		//boundColorChooser.setSize(400,400);
		//boundColorChooser.setLayout(new GridLayout(3, 1));
		  
		
		

        JMenuBar menubar = new JMenuBar();
        
        ImageIcon exitIcon = new ImageIcon("Icons/exit.png");
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem("Exit", exitIcon);
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        file.add(eMenuItem);

        
        JMenuItem refreshMenuItem = new JMenuItem("Refresh", exitIcon);
        refreshMenuItem.setMnemonic(KeyEvent.VK_R);
        refreshMenuItem.setToolTipText("Refresh Screen");
        refreshMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	complexPlanePanel.revalidate();
            	complexPlanePanel.repaint();
            }
        });
        
        file.add(refreshMenuItem);
        
               
        
        JMenu capture = new JMenu("Capture");
        capture.setMnemonic(KeyEvent.VK_C);
        
        ImageIcon imageIcon = new ImageIcon("Icons/image.png");
        JMenuItem imageMenuItem = new JMenuItem("Image", imageIcon);
        imageMenuItem.setMnemonic(KeyEvent.VK_P);
        imageMenuItem.setToolTipText("Save Image as File");
        imageMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        ImageIcon videoIcon = new ImageIcon("Icons/movie.png");
        JMenuItem movieMenuItem = new JMenuItem("Movie", videoIcon);
        movieMenuItem.setMnemonic(KeyEvent.VK_M);
        movieMenuItem.setToolTipText("Zoom out, and capture animation as a video file");
        movieMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        capture.add(imageMenuItem);
        capture.add(movieMenuItem);
        
        JMenu settings = new JMenu("Settings");
        settings.setMnemonic(KeyEvent.VK_S);
        
        ImageIcon mathIcon = new ImageIcon("Icons/math.png");
        JMenuItem mathMenuItem = new JMenuItem("Mathmatics", mathIcon);
        mathMenuItem.setMnemonic(KeyEvent.VK_M);
        mathMenuItem.setToolTipText("Mathmatical Adjustments");
        mathMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        ImageIcon colorIcon = new ImageIcon("Icons/color.png");
        JMenuItem colorMenuItem = new JMenuItem("Colors", colorIcon);
        colorMenuItem.setMnemonic(KeyEvent.VK_C);
        colorMenuItem.setToolTipText("Color Adjustments");
        colorMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                PreferencesColor pcWindow = new PreferencesColor();
                pcWindow.addWindowListener(new WindowAdapter() {
                   
                    @Override
                    public void windowDeactivated(WindowEvent e)
                    {
                      System.out.println("window deactivated");

	                  	complexPlanePanel.revalidate();
	                  	complexPlanePanel.repaint();
                    }

                });
                pcWindow.setVisible(true);
                
                complexPlanePanel.repaint();
                
            }
        });
        
        
        ImageIcon boundColorIcon = new ImageIcon("Icons/color.png");
        JMenuItem boundColorMenuItem = new JMenuItem("Bound Color", colorIcon);
        boundColorMenuItem.setMnemonic(KeyEvent.VK_C);
        boundColorMenuItem.setToolTipText("Color Adjustments");
        boundColorMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
            	//Color backgroundColor = JColorChooser.showDialog(null, "Choose background color", Color.white);
            	
            	
//            	 JColorChooser chooser = new JColorChooser();
//            	 chooser.setPreviewPanel(new JPanel());
            	 
            	 
            	 
            	 
//            	 Color c = chooser.showDialog(null, "Choose background color", Color.white);

            	
            	//boundColorChooser.setVisible(true);  
//                PreferencesColor pcWindow = new PreferencesColor();
//                pcWindow.addWindowListener(new WindowAdapter() {
//                   
//                    @Override
//                    public void windowDeactivated(WindowEvent e)
//                    {
//                      System.out.println("window deactivated");
//
//	                  	complexPlanePanel.revalidate();
//	                  	complexPlanePanel.repaint();
//                    }
//
//                });
//                pcWindow.setVisible(true);
            	
            	
            	 final JLabel previewLabel = new JLabel("I Love Swing", JLabel.CENTER);
            	    previewLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
            	    previewLabel.setSize(previewLabel.getPreferredSize());
            	    previewLabel.setBorder(BorderFactory.createEmptyBorder(0,0,1,0));
            	 
            	    JColorChooser colorChooser = new JColorChooser();
            	    colorChooser.setPreviewPanel(new JLabel());

            	    AbstractColorChooserPanel[] oldPanels = colorChooser.getChooserPanels();
            	    
            	    for (int i = 0; i < oldPanels.length; i++) {
            	        String clsName = oldPanels[i].getClass().getName();
            	        if (clsName.equals("javax.swing.colorchooser.DefaultHSVChooserPanel")) {
            	        	colorChooser.removeChooserPanel(oldPanels[i]);
            	        } else if (clsName.equals("javax.swing.colorchooser.DefaultRGBChooserPanel")) {
            	        	colorChooser.removeChooserPanel(oldPanels[i]);
            	        } else if (clsName.equals("javax.swing.colorchooser.DefaultHSBChooserPanel")) {
            	        	colorChooser.removeChooserPanel(oldPanels[i]);
            	        } else if (clsName.equals("javax.swing.colorchooser.DefaultHSLChooserPanel")) {
            	        	colorChooser.removeChooserPanel(oldPanels[i]);
            	        } else if (clsName.equals("javax.swing.colorchooser.DefaultCYMKChooserPanel")) {
            	        	colorChooser.removeChooserPanel(oldPanels[i]);
            	        }
            	      }
            	    
            	    
            	    JDialog d = colorChooser.createDialog(null,"Bound Color",true,colorChooser,null,null);
            	    
            	    d.addComponentListener(new ComponentListener(){

						@Override
						public void componentResized(ComponentEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void componentMoved(ComponentEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void componentShown(ComponentEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void componentHidden(ComponentEvent e) {
							// TODO Auto-generated method stub
							mandelbrotSet.setBoundColor(colorChooser.getColor());
							complexPlanePanel.revalidate();
							complexPlanePanel.repaint();
						}
            	    	
            	    });
            	    
            	    d.setVisible(true);
            	
            	
               
            	    
                
            }
        });
        
        
        
        settings.add(mathMenuItem);
        settings.add(colorMenuItem);
        settings.add(boundColorMenuItem);
       
        menubar.add(file);
        menubar.add(capture);
        menubar.add(settings);
        
        setJMenuBar(menubar);
        
    }
	
	
} // end class
