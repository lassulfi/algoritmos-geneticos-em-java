package jgap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jfree.ui.RefineryUtilities;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.DefaultFitnessEvaluator;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;
import org.jgap.event.EventManager;
import org.jgap.impl.BestChromosomesSelector;
import org.jgap.impl.CrossoverOperator;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.StockRandomGenerator;
import org.jgap.impl.SwappingMutationOperator;

import entities.Product;
import specimen.Specimen;
import view.Chart;

public class JGapGeneticAlgorithm {

	private Configuration configuration;
	private int numberOfGenerations = 100;
	private double maxValue = 3.0;
	private int popularionSize = 20;
	private int mutationRate = 100;

	private List<IChromosome> bestChromosomes = new ArrayList<>();
	private List<Product> products = new ArrayList<>();
	private IChromosome bestChromosome;

	public void loadData() {
		products = Arrays.asList(new Product("Geladeira Dako", 0.751, 999.90),
				new Product("Iphone 6", 0.000089, 2911.12), new Product("TV 55'", 0.400, 4346.99),
				new Product("TV 50'", 0.290, 3999.90), new Product("TV 42'", 0.200, 2999.00),
				new Product("Notebook Dell", 0.00350, 2499.90), new Product("Ventilador Panassonic", 0.496, 199.90),
				new Product("Microondas electrolux", 0.0424, 308.66), new Product("Microondas LG", 0.0544, 429.90),
				new Product("Microondas Panasonic", 0.0319, 299.29), new Product("Geladeira Brastemp", 0.635, 849.00),
				new Product("Geladeira Consul", 0.870, 1199.89), new Product("Notebook Lenovo", 0.498, 1999.90),
				new Product("Notebook Asus", 0.527, 3999.00));
	}

	public Double sumVolumes(IChromosome chromosome) {
		Double sum = 0.0;
		for (int i = 0; i < chromosome.size(); i++) {
			if (chromosome.getGene(i).getAllele().toString().equals("1")) {
				sum += this.products.get(i).getVolume();
			}
		}

		return sum;
	}

	public void showGeneration(IChromosome chromosome, int genetation) {
		List<String> list = new ArrayList<>();

		Gene[] genes = chromosome.getGenes();
		for (int i = 0; i < chromosome.size(); i++) {
			list.add(genes[i].getAllele().toString() + " ");
		}

		System.out.println("G: " + genetation + " Total value: " + chromosome.getFitnessValue() + " Total volume: "
				+ sumVolumes(chromosome) + " Chromosome: " + list);
	}
	
	public IChromosome createChromosome() throws InvalidConfigurationException {
		Gene[] genes = new Gene[products.size()];
		for(int i = 0; i < genes.length; i++) {
			genes[i] = new IntegerGene(configuration, 0, 1);
			genes[i].setAllele(i);
		}
		
		IChromosome model = new Chromosome(configuration, genes);
		
		return model;
	}
	
	public FitnessFunction getFitnessFunction() {
		return new Evaluation(this);
	}
	
	public Configuration setupConfiguration() throws InvalidConfigurationException {
		Configuration configuration = new Configuration();
		configuration.removeNaturalSelectors(true);
		configuration.addNaturalSelector(new BestChromosomesSelector(configuration, 0.4), true);
		configuration.setRandomGenerator(new StockRandomGenerator());
		configuration.addGeneticOperator(new CrossoverOperator(configuration));
		configuration.addGeneticOperator(new SwappingMutationOperator(configuration, mutationRate));
		configuration.setKeepPopulationSizeConstant(true);
		configuration.setEventManager(new EventManager());
		configuration.setFitnessEvaluator(new DefaultFitnessEvaluator());
		
		return configuration;
	}
	
	public void findBestSolution() throws InvalidConfigurationException {
		this.configuration = setupConfiguration();
		
		FitnessFunction fitnessFunction = getFitnessFunction();
		this.configuration.setFitnessFunction(fitnessFunction);
		
		IChromosome chromosomeModel = createChromosome();
		this.configuration.setSampleChromosome(chromosomeModel);
		
		this.configuration.setPopulationSize(popularionSize);
		
		IChromosome[] chromosomes = new IChromosome[this.popularionSize];
		for(int i = 0; i < this.popularionSize; i++) {
			chromosomes[i] = createChromosome();
		}
		
		Genotype population = new Genotype(this.configuration, new Population(this.configuration, chromosomes));
		for(int i = 0; i < this.numberOfGenerations; i++) {
			this.showGeneration(population.getFittestChromosome(), i);
			this.bestChromosomes.add(population.getFittestChromosome());
			population.evolve();
		}
	}
	
	public static void main(String args[]) {
		JGapGeneticAlgorithm ga = new JGapGeneticAlgorithm();
		ga.loadData();
		try {
			ga.findBestSolution();
			int generation = 0;
			for(int i = 0; i < ga.getBestChromosomes().size(); i++) {
				if(ga.bestChromosome == null) {
					ga.bestChromosome = ga.bestChromosomes.get(i);
				} else if(ga.bestChromosome.getFitnessValue() < ga.getBestChromosomes().get(i).getFitnessValue()) {
					ga.bestChromosome = ga.bestChromosomes.get(i);
					generation = i;
				}
			}
			System.out.println("\nMelhor solução: ");
			ga.showGeneration(ga.bestChromosome, generation);
			for(int i = 0; i < ga.products.size(); i++) {
				if(ga.bestChromosome.getGene(i).getAllele().equals("1")) {
					System.out.println("Nome: " + ga.products.get(i).getName());
				}
			}
			Chart<IChromosome> chart = new Chart<>("Genetic Algorithm", "Evolution of solutions", ga.getBestChromosomes());
			chart.pack();
			RefineryUtilities.centerFrameOnScreen(chart);
			chart.setVisible(true);
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public List<IChromosome> getBestChromosomes() {
		return bestChromosomes;
	}

	public void setBestChromosomes(List<IChromosome> bestChromosomes) {
		this.bestChromosomes = bestChromosomes;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public IChromosome getBestChromosome() {
		return bestChromosome;
	}

	public void setBestChromosome(IChromosome bestChromosome) {
		this.bestChromosome = bestChromosome;
	}
}
