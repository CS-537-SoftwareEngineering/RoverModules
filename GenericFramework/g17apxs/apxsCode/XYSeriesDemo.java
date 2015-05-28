package apxsCode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class XYSeriesDemo extends ApplicationFrame {

	public XYSeriesDemo(final String title) throws IOException, ParseException {

		super(title);
		final XYSeries series = new XYSeries("Count per Sec Vs keV");
		FileReader reader= new FileReader("18.json");
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
		
		JSONArray keV = (JSONArray) jsonObject.get("keV");
		Iterator i = keV.iterator();
		
		
		JSONArray countPerSec = (JSONArray) jsonObject.get("countPerSec");
		Iterator j = countPerSec.iterator();
		
		while (i.hasNext() && j.hasNext()){
			Double k = (Double) i.next();
			Double h = (Double) j.next();
			
			series.add(k, h);
		}
		
		
		final XYSeriesCollection data = new XYSeriesCollection(series);
		final JFreeChart chart = ChartFactory.createXYLineChart(
				"CPS Vs keV", "keV", "CPS", data, PlotOrientation.VERTICAL,
				true, true, false);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);

	}

}
