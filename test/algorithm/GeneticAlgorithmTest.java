package algorithm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import entities.Product;
import specimen.Specimen;

public class GeneticAlgorithmTest {

	private GeneticAlgorithm algorithm;

	List<Double> volumes = new ArrayList<>();
	List<Double> prices = new ArrayList<>();
	List<String> names = new ArrayList<>();

	double limit = 3;

	int populationSize = 20;

	@Before
	public void setup() {

		List<Product> products = Arrays.asList(new Product("Geladeira Dako", 0.751, 999.90),
				new Product("Iphone 6", 0.000089, 2911.12), new Product("TV 55'", 0.400, 4346.99),
				new Product("TV 50'", 0.290, 3999.90), new Product("TV 42'", 0.200, 2999.00),
				new Product("Notebook Dell", 0.00350, 2499.90), new Product("Ventilador Panassonic", 0.496, 199.90),
				new Product("Microondas electrolux", 0.0424, 308.66), new Product("Microondas LG", 0.0544, 429.90),
				new Product("Microondas Panasonic", 0.0319, 299.29), new Product("Geladeira Brastemp", 0.635, 849.00),
				new Product("Geladeira Consul", 0.870, 1199.89), new Product("Notebook Lenovo", 0.498, 1999.90),
				new Product("Notebook Asus", 0.527, 3999.00));

		for (Product product : products) {
			volumes.add(product.getVolume());
			prices.add(product.getPrice());
			names.add(product.getName());
		}

	}

	@Test
	public void testGetPopulationSize() {
		this.algorithm = new GeneticAlgorithm(populationSize);

		assertThat(algorithm.getPopulationSize(), CoreMatchers.is(populationSize));
	}

	@Test
	public void testCreatePopulation() {
		this.algorithm = new GeneticAlgorithm(populationSize);

		this.algorithm.createPopulation(volumes, prices, limit);

		assertTrue(!this.algorithm.getPopulation().isEmpty());
	}

	@Test
	public void testSortPopulation() {
		this.algorithm = new GeneticAlgorithm(populationSize);

		this.algorithm.createPopulation(volumes, prices, limit);

		this.algorithm.getPopulation().forEach(sp -> sp.evaluation());

		this.algorithm.sortPopulation();

		Specimen best = algorithm.getPopulation().get(0);
		Specimen worst = algorithm.getPopulation().get(populationSize - 1);

		assertTrue(best.getEvalutationGrade() > worst.getEvalutationGrade());
	}

	@Test
	public void testBestSpecimen() {
		this.algorithm = new GeneticAlgorithm(populationSize);
		this.algorithm.createPopulation(volumes, prices, limit);
		this.algorithm.getPopulation().forEach(sp -> {
			sp.evaluation();
			algorithm.bestSpecimen(sp);
		});

		this.algorithm.sortPopulation();
		Specimen best = algorithm.getPopulation().get(0);
		
		Specimen bestSelected = algorithm.getBestSolution();

		assertEquals(best, bestSelected);
	}
	
	@Test
	public void testEvaluationSum() {
		this.algorithm = new GeneticAlgorithm(populationSize);
		this.algorithm.createPopulation(volumes, prices, limit);
		this.algorithm.getPopulation().forEach(sp -> sp.evaluation());
		
		Double evaluationSum = this.algorithm.evaluationSum();
		
		Double sum = 0.0;
		for(Specimen specimen : this.algorithm.getPopulation()) {
			sum += specimen.getEvalutationGrade();
		}
		
		assertThat(evaluationSum, CoreMatchers.is(sum));
	}
	
	@Test
	public void testFatherSelection() {
		this.algorithm = new GeneticAlgorithm(populationSize);
		this.algorithm.createPopulation(volumes, prices, limit);
		this.algorithm.getPopulation().forEach(sp -> sp.evaluation());		
		this.algorithm.sortPopulation();
		
		Integer father = 0;
		for(int i = 0; i < algorithm.getPopulation().size() /2; i++) {
			father = this.algorithm.fatherSelection(this.algorithm.evaluationSum());
		}
				
		assertTrue(father > 0);
	}
	
	@Test
	public void testSolve() {
		this.algorithm = new GeneticAlgorithm(populationSize);
		double mutationRatio = 0.01;
		int numberOfGenerations = 100;
		List<Integer> solution = this.algorithm.solve(mutationRatio, numberOfGenerations, volumes, prices, limit);
		
		assertTrue(!solution.isEmpty());
	}

}
