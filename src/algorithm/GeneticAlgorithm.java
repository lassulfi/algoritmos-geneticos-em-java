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
		Double raffledValue = Math.random() * evaluationSum; //roullete
		Double sum = 0.0;
		int i = 0;
		while(i < this.population.size() && sum < raffledValue) {
			sum += this.population.get(i).getEvalutationGrade();
			father++;
			i++;
		}
		
		return father;
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
