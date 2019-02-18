package algorithm;

import java.util.ArrayList;
import java.util.List;

import specimen.Specimen;

public class GeneticAlgorithm {

	private int populationSize;
	private List<Specimen> population = new ArrayList<>();
	private int generation;
	private Specimen bestSolution;

	public GeneticAlgorithm(int populationSize) {
		this.populationSize = populationSize;
	}

	public void createPopulation(List<Double> volumes, List<Double> values, double limitVolume) {
		for (int i = 0; i < this.populationSize; i++) {
			this.population.add(new Specimen(volumes, values, limitVolume));
		}
		this.bestSolution = this.population.get(0);

	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public List<Specimen> getPopulation() {
		return population;
	}

	public void setPopulation(List<Specimen> population) {
		this.population = population;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public Specimen getBestSolution() {
		return bestSolution;
	}

	public void setBestSolution(Specimen bestSolution) {
		this.bestSolution = bestSolution;
	}

}
