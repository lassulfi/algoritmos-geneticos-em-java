package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import specimen.Specimen;

public class GeneticAlgorithm {

	private int populationSize;
	private List<Specimen> population = new ArrayList<>();
	private int generation;
	private Specimen bestSolution;
	private List<Specimen> bestChromosomes = new ArrayList<>();

	public GeneticAlgorithm(int populationSize) {
		this.populationSize = populationSize;
	}

	public void createPopulation(List<Double> volumes, List<Double> values, double limitVolume) {
		for (int i = 0; i < this.populationSize; i++) {
			this.population.add(new Specimen(volumes, values, limitVolume));
		}
		this.bestSolution = this.population.get(0);
	}

	/**
	 * Sort the population to a desc order
	 */
	public void sortPopulation() {
		Collections.sort(this.population);
	}

	/**
	 * Indentify the best solution for the problem
	 * 
	 * @param specimen Specimen
	 */
	public void bestSpecimen(Specimen specimen) {
		if (specimen.getEvalutationGrade() > this.bestSolution.getEvalutationGrade()) {
			this.bestSolution = specimen;
		}
	}

	/**
	 * Calculates the sum of all evaluations
	 * 
	 * @return Double
	 */
	public Double evaluationSum() {
		Double sum = 0.0;
		for (Specimen specimen : this.population) {
			sum += specimen.getEvalutationGrade();
		}

		return sum;
	}

	/**
	 * Selects the father based on the sum of evaluations
	 * 
	 * @param evaluationSum
	 * @return Integer
	 */
	public Integer fatherSelection(Double evaluationSum) {
		Integer father = -1;
		Double raffledValue = Math.random() * evaluationSum; // roullete
		Double sum = 0.0;
		int i = 0;
		while (i < this.population.size() && sum < raffledValue) {
			sum += this.population.get(i).getEvalutationGrade();
			father++;
			i++;
		}

		return father;
	}

	public void showGeneration() {
		Specimen best = this.population.get(0);
		this.bestChromosomes.add(best);
		System.out.println("G: " + best.getGeneration() + " Total Value: " + best.getEvalutationGrade()
				+ " Total volume: " + best.getTotalVolume() + " Chromosome: " + best.getChromosomes());
	}

	public List<Integer> solve(double mutationRatio, int numberOfGenerations, List<Double> volumes, List<Double> values,
			double maxLimit) {

		this.createPopulation(volumes, values, maxLimit);
		this.population.forEach(specimen -> specimen.evaluation());
		this.sortPopulation();
		this.showGeneration();

		for (int generation = 0; generation < numberOfGenerations; generation++) {
			Double evaluationSum = this.evaluationSum();
			List<Specimen> newPopulation = new ArrayList<>();
			for (int i = 0; i < this.population.size() / 2; i++) {
				int father1 = this.fatherSelection(evaluationSum);
				int father2 = this.fatherSelection(evaluationSum);
				while (father1 == father2) {
					father1 = this.fatherSelection(evaluationSum);
					father2 = this.fatherSelection(evaluationSum);
				}
				List<Specimen> children = this.getPopulation().get(father1)
						.crossover(this.getPopulation().get(father2));
				newPopulation.add(children.get(0).mutation(mutationRatio));
				newPopulation.add(children.get(1).mutation(mutationRatio));
			}
			this.setPopulation(newPopulation);
			this.getPopulation().forEach(specimen -> specimen.evaluation());
			this.sortPopulation();
			this.showGeneration();
			Specimen bestSpecimen = this.population.get(0);
			this.bestSpecimen(bestSpecimen);
		}

		System.out.println("Best solution G -> " + this.bestSolution.getGeneration() + " Total value: "
				+ this.bestSolution.getEvalutationGrade() + " Total volume: " + this.bestSolution.getTotalVolume()
				+ " Chromosome: " + this.bestSolution.getChromosomes());

		return this.getBestSolution().getChromosomes();
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

	public List<Specimen> getBestChromosomes() {
		return bestChromosomes;
	}

	public void setBestChromosomes(List<Specimen> bestChromosomes) {
		this.bestChromosomes = bestChromosomes;
	}
}
