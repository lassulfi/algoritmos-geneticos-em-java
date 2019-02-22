package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jfree.ui.RefineryUtilities;

import entities.Product;
import entities.ProductBR;
import entities.ProductDAOImpl;
import specimen.Specimen;
import view.Chart;

public class Main {

	public static void main(String[] args) {

		ProductBR productBR = new ProductBR(new ProductDAOImpl());
		
		List<Product> products = productBR.findAll();

		List<Double> volumes = new ArrayList<>();
		List<Double> prices = new ArrayList<>();

		for (Product p : products) {
			volumes.add(p.getVolume());
			prices.add(p.getPrice());
		}

		double maxVolume = 10.0;
		int populationSize = 30;
		double mutationRatio = 0.01;
		int numberOfGenerations = 400;

		GeneticAlgorithm ga = new GeneticAlgorithm(populationSize);
		List<Integer> solution = ga.solve(mutationRatio, numberOfGenerations, volumes, prices, maxVolume);
		StringBuilder builder = new StringBuilder();
		builder.append("Relação de produtos:\n[ ");
		for (int i = 0; i < products.size(); i++) {
			if (solution.get(i) == 1) {
				builder.append( products.get(i).getName());
				builder.append("; ");
			}
		}
		builder.append(" ]");
		System.out.println(builder.toString());
		
		Chart chart = new Chart("Genetic Algorithm", "Evolution of solutions", ga.getBestChromosomes());
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);
	}

}
