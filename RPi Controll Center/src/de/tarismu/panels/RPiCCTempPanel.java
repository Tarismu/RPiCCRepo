package de.tarismu.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import de.tarismu.RPiCC;

@SuppressWarnings("serial")
public class RPiCCTempPanel extends JPanel{
	
	private JFreeChart temperatureChart;
	private RPiCC main = new RPiCC();
	
	public RPiCCTempPanel(){
		initPanel();
	}
	
	/**
	 * Usses JFreeChart Library to create a new chart with a given dataset
	 * @param dataset
	 * @return
	 */
	
    private JFreeChart createChart(final XYDataset dataset) {
        
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Temperature View",       // chart title
            "Interval'",             // x axis label
            "Temp in C'",               // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            false,                     // include legend
            false,                     // tooltips
            false                     // urls
        );

        chart.setBackgroundPaint(Color.gray);
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());        
        
        return chart;
        
    }
    
    /**
     * Initiates the panel and adds the panel
     */
    
    private void initPanel(){
    	temperatureChart = createChart(createDataset());
    	ChartPanel chPanel = new ChartPanel(temperatureChart);
    	chPanel.setPreferredSize(new Dimension(450, 240));
    	chPanel.setMouseWheelEnabled(true);
    	this.add(chPanel);
    }
    
    /**
     * Creates a dataset with the temperature data from the MySQLAgent
     * @return
     */
    
	private XYDataset createDataset() {
        
    	ResultSet data = main.getMySQLAgent().getEntrys();
    	
        final XYSeries temperatureSeries = new XYSeries("First");
        final XYSeries clockSeries = new XYSeries("Second");
        
        try{
	        while(data.next()){
	        	temperatureSeries.add(Double.parseDouble(data.getString("ID")), Double.parseDouble(data.getString("temp")));
	        }
        }catch(SQLException ex){
        	JOptionPane.showMessageDialog(null, "An Exception occurred (P102): " + ex.getLocalizedMessage());
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(clockSeries);
        dataset.addSeries(temperatureSeries);
                
        return dataset;
        
    }
	
}
