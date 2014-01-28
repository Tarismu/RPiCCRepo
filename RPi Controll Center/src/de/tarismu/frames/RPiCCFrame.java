package de.tarismu.frames;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.tarismu.panels.RPiCCTempPanel;

@SuppressWarnings("serial")
public class RPiCCFrame extends JFrame{
	
	
	
	public void initFrame(){
		
		/**
		 * Sets windows properties
		 */
		
		this.setSize(new Dimension(900, 480));
		this.setPreferredSize(new Dimension(900, 480));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("RPiCC - Raspberry Pi Control Center (by Tarismu)");
		this.setResizable(false);
		this.setLayout(null);
		
		/**
		 * Sets windows builder
		 */
		
		try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
            this.pack();
            this.getContentPane().repaint();
		}catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
            JOptionPane.showMessageDialog(null, "An Exception occurred (P101): " + e.getLocalizedMessage());
		}
		
		/**
		 * Configures components
		 */
		
		//TempPanel
		RPiCCTempPanel temperaturePanel = new RPiCCTempPanel();
		temperaturePanel.setBounds(0, -5, 450, 240);
		
		/**
		 * Adds components
		 */
		
		this.add(temperaturePanel);
		
		/**
		 * Packs
		 */
		
		this.pack();
		
		/**
		 * Visible
		 */
		
		this.setVisible(true);
		
		
	}
	
}
