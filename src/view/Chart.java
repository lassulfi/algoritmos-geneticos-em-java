package view;

import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import specimen.Specimen;

public class Chart extends ApplicationFrame {

	private List<Specimen> bestChromosomes = new ArrayList<>();

	public Chart(String WindowTitle, String chartTitle, List<Specimen> bestChromosomes) {
		super(WindowTitle);
		this.bestChromosomes = bestChromosomes;
		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "Generation", "Total Value", loadData(),
				PlotOrientation.VERTICAL, true, true, false);
		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
		setContentPane(chartPanel);
	}

	private DefaultCategoryDataset loadData() {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		for (int i = 0; i < bestChromosomes.size(); i++) {
			dataSet.addValue(bestChromosomes.get(i).getEvalutationGrade(), "Best solution", "" + i);
		}

		return dataSet;
	}

}
