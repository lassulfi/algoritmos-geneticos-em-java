package view;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jgap.IChromosome;

import specimen.Specimen;

public class Chart<T> extends ApplicationFrame {

	private List<T> bestChromosomes = new ArrayList<>();

	public Chart(String WindowTitle, String chartTitle, List<T> bestChromosomes) {
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
			if(bestChromosomes.get(i) instanceof Specimen) {
				Specimen specimen = (Specimen) bestChromosomes.get(i);
				dataSet.addValue(specimen.getEvalutationGrade(), "Best solution", "" + i);
			}
			if(bestChromosomes.get(i) instanceof IChromosome) {
				IChromosome chromosome = (IChromosome) bestChromosomes.get(i);
				dataSet.addValue(chromosome.getFitnessValue(), "Best solution", "" + i);
			}
			
		}

		return dataSet;
	}

}
