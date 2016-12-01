package net.steinkirch.Mandelbrot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import java.awt.Canvas;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import java.awt.Font;

import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class PreferencesColor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Gradient gr = new Gradient();
	private final Action action = new SwingAction();
	private Color boundColor;
	
	
	private int gradientId;
		
	

	public Color getBoundColor() {
		return boundColor;
	}

	public void setBoundColor(Color boundColor) {
		this.boundColor = boundColor;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PreferencesColor dialog = new PreferencesColor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/** Listens to the radio buttons. */
	private class RadioGradientListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			gradientId = Integer.parseInt(e.getActionCommand());
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public PreferencesColor() {
		MandelbrotSet mandelbrotSet = MandelbrotSet.getInstance();
		gradientId = mandelbrotSet.getGradientId();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("/home/sskirch/workspace/Mdblt2/bin/Icons/color.png"));
		setTitle("Color Prefernces");
		setBounds(100, 100, 700, 810);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(200dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(16dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		{
			JLabel lblBindingColorGradient = new JLabel("Binding Color Gradient");
			lblBindingColorGradient.setFont(new Font("Bitstream Vera Sans", Font.BOLD, 18));
			contentPanel.add(lblBindingColorGradient, "2, 2");
		}
		
		

		ButtonGroup groupGradient = new ButtonGroup();

		
		
		//Gradient Buttons****************************************************************************************************************************
		
		RadioGradientListener radioGradientListener = new RadioGradientListener();
		
		{
			JRadioButton rdbtnMaroonToGold = new JRadioButton("Maroon to Gold");
			
			if(gradientId==Gradient.ID_MAROON_TO_GOLD){
				rdbtnMaroonToGold.setSelected(true);
			}
			
			rdbtnMaroonToGold.setActionCommand(String.valueOf(Gradient.ID_MAROON_TO_GOLD));
			rdbtnMaroonToGold.addActionListener(radioGradientListener);
			contentPanel.add(rdbtnMaroonToGold, "2, 4");
			groupGradient.add(rdbtnMaroonToGold);
		}
		
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(PreferencesColor.class.getResource("/net/steinkirch/Mandelbrot/Icons/GRADIENT_MAROON_TO_GOLD.png")));
			contentPanel.add(label, "2, 6");
		}
		{
			JCheckBox chckbxReverseMaroonToGold = new JCheckBox("Reverse");
			contentPanel.add(chckbxReverseMaroonToGold, "4, 6");
		}
		
		//************************************************
		
		{
			JRadioButton rdbtnBlueToRed = new JRadioButton("Blue to Red");

			if(gradientId==Gradient.ID_BLUE_TO_RED){
				rdbtnBlueToRed.setSelected(true);
			}
			
			
			rdbtnBlueToRed.setActionCommand(String.valueOf(Gradient.ID_BLUE_TO_RED));
			rdbtnBlueToRed.addActionListener(radioGradientListener);

			contentPanel.add(rdbtnBlueToRed, "2, 8");
			groupGradient.add(rdbtnBlueToRed);
			
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(PreferencesColor.class.getResource("/net/steinkirch/Mandelbrot/Icons/GRADIENT_BLUE_TO_RED.png")));
			contentPanel.add(label, "2, 10");
		}
		{
			JCheckBox chckbxReverseBlueToRed = new JCheckBox("Reverse");
			contentPanel.add(chckbxReverseBlueToRed, "4, 10");
		}
		
		//************************************************
		{
			JRadioButton rdbtnBlackToWhite = new JRadioButton("Black to White");
			
			if(gradientId==Gradient.ID_BLACK_TO_WHITE){
				rdbtnBlackToWhite.setSelected(true);
			}
			
			rdbtnBlackToWhite.setActionCommand(String.valueOf(Gradient.ID_BLACK_TO_WHITE));
			rdbtnBlackToWhite.addActionListener(radioGradientListener);
			
			contentPanel.add(rdbtnBlackToWhite, "2, 12");
			groupGradient.add(rdbtnBlackToWhite);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(PreferencesColor.class.getResource("/net/steinkirch/Mandelbrot/Icons/GRADIENT_BLACK_TO_WHITE.png")));
			contentPanel.add(label, "2, 14");
		}
		{
			JCheckBox chckbxReverseBlackToRed = new JCheckBox("Reverse");
			contentPanel.add(chckbxReverseBlackToRed, "4, 14");
		}
		
		//************************************************				
		{
			JRadioButton rdbtnGreenYellowOrangeRed = new JRadioButton("Green Yellow Orange Red");

			if(gradientId==Gradient.ID_GREEN_YELLOW_ORANGE_RED){
				rdbtnGreenYellowOrangeRed.setSelected(true);
			}
			
			rdbtnGreenYellowOrangeRed.setActionCommand(String.valueOf(Gradient.ID_GREEN_YELLOW_ORANGE_RED));
			rdbtnGreenYellowOrangeRed.addActionListener(radioGradientListener);
			
			contentPanel.add(rdbtnGreenYellowOrangeRed, "2, 16");
			groupGradient.add(rdbtnGreenYellowOrangeRed);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(PreferencesColor.class.getResource("/net/steinkirch/Mandelbrot/Icons/GRADIENT_GREEN_YELLOW_ORANGE_RED.png")));
			contentPanel.add(label, "2, 18");
		}
		{
			JCheckBox chckbxReverseGreenYellowRed = new JCheckBox("Reverse");
			contentPanel.add(chckbxReverseGreenYellowRed, "4, 18");
		}
		
		//************************************************
		{
			JRadioButton rdbtnRainbow = new JRadioButton("Rainbow");
			
			if(gradientId==Gradient.ID_RAINBOW){
				rdbtnRainbow.setSelected(true);
			}			
			
			rdbtnRainbow.setActionCommand(String.valueOf(Gradient.ID_GREEN_YELLOW_ORANGE_RED));
			rdbtnRainbow.addActionListener(radioGradientListener);
			
			contentPanel.add(rdbtnRainbow, "2, 28");

			groupGradient.add(rdbtnRainbow);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(PreferencesColor.class.getResource("/net/steinkirch/Mandelbrot/Icons/GRADIENT_RAINBOW.png")));
			contentPanel.add(label, "2, 30");
		}
		//************************************************
		{
			JRadioButton rdbtnHot = new JRadioButton("Hot");
			
			if(gradientId==Gradient.ID_HOT){
				rdbtnHot.setSelected(true);
			}
			

			rdbtnHot.setActionCommand(String.valueOf(Gradient.ID_HOT));
			rdbtnHot.addActionListener(radioGradientListener);
			
			
			contentPanel.add(rdbtnHot, "2, 20");
			groupGradient.add(rdbtnHot);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(PreferencesColor.class.getResource("/net/steinkirch/Mandelbrot/Icons/GRADIENT_HOT.png")));
			contentPanel.add(label, "2, 22");
		}
		{
			JCheckBox chckbxReverseHot = new JCheckBox("Reverse");
			contentPanel.add(chckbxReverseHot, "4, 22");
		}
		//************************************************
		{
			JRadioButton rdbtnHeat = new JRadioButton("Heat");
			
			if(gradientId==Gradient.ID_HEAT){
				rdbtnHeat.setSelected(true);
			}
			

			rdbtnHeat.setActionCommand(String.valueOf(Gradient.ID_HEAT));
			rdbtnHeat.addActionListener(radioGradientListener);
			
			contentPanel.add(rdbtnHeat, "2, 24");
			groupGradient.add(rdbtnHeat);
		}
		{
			JCheckBox chckbxReverseHeat = new JCheckBox("Reverse");
			contentPanel.add(chckbxReverseHeat, "4, 26");
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(PreferencesColor.class.getResource("/net/steinkirch/Mandelbrot/Icons/GRADIENT_HEAT.png")));
			contentPanel.add(label, "2, 26");
		}
		
		{
			JCheckBox chckbxReverseRainbow = new JCheckBox("Reverse");
			contentPanel.add(chckbxReverseRainbow, "4, 30");
		}		
		//************************************************
		{
			JRadioButton rdbtnRainbowWhiteTo = new JRadioButton("Rainbow White to Black");
			
			if(gradientId==Gradient.ID_WHITE_TO_BLACK_RAINBOW){
				rdbtnRainbowWhiteTo.setSelected(true);
			}
			

			rdbtnRainbowWhiteTo.setActionCommand(String.valueOf(Gradient.ID_WHITE_TO_BLACK_RAINBOW));
			rdbtnRainbowWhiteTo.addActionListener(radioGradientListener);
			
			contentPanel.add(rdbtnRainbowWhiteTo, "2, 32");
			groupGradient.add(rdbtnRainbowWhiteTo);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(PreferencesColor.class.getResource("/net/steinkirch/Mandelbrot/Icons/GRADIENT_WHITE_TO_BLACK_RAINBOW.png")));
			contentPanel.add(label, "2, 34");
		}
		{
			JCheckBox chckbxReverseRainbowWhiteToBlack = new JCheckBox("Reverse");
			contentPanel.add(chckbxReverseRainbowWhiteToBlack, "4, 34");
		}
		//************************************************
		{
			JRadioButton rdbtnRainbowBlackTo = new JRadioButton("Rainbow Black to White");

			if(gradientId==Gradient.ID_BLACK_TO_WHITE_RAINBOW){
				rdbtnRainbowBlackTo.setSelected(true);
			}
			
			
			rdbtnRainbowBlackTo.setActionCommand(String.valueOf(Gradient.ID_BLACK_TO_WHITE_RAINBOW));
			rdbtnRainbowBlackTo.addActionListener(radioGradientListener);
			
			groupGradient.add(rdbtnRainbowBlackTo);
			contentPanel.add(rdbtnRainbowBlackTo, "2, 36");
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(PreferencesColor.class.getResource("/net/steinkirch/Mandelbrot/Icons/GRADIENT_BLACK_TO_WHITE_RAINBOW.png")));
			contentPanel.add(label, "2, 38");
		}
		{
			JCheckBox chckbxReverseBlackToWhite = new JCheckBox("Reverse");
			contentPanel.add(chckbxReverseBlackToWhite, "4, 38");
		}
		
		
		
		//*****************************************************************************************************************************************************************
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mandelbrotSet.setGradientId(gradientId);
						setVisible(false);
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						mandelbrotSet.setGradientId(gradientId);
					}
					
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
		
		
		
	}

		
	
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
