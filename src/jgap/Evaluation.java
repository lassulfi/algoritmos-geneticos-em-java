package jgap;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class Evaluation extends FitnessFunction {
	
	private JGapGeneticAlgorithm geneticAlgorithm;
	
	public Evaluation(JGapGeneticAlgorithm geneticAlgorithm) {
		this.geneticAlgorithm = geneticAlgorithm;
	}

	@Override
	protected double evaluate(IChromosome chromosome) {
		double evaluationGrade = 0.0;
		double volumeSum = 0.0;
		
		for(int i = 0; i < chromosome.size(); i++) {
			if(chromosome.getGene(i).getAllele().toString().equals("1")) {
				evaluationGrade += this.geneticAlgorithm.getProducts().get(i).getPrice();
				volumeSum += this.geneticAlgorithm.getProducts().get(i).getVolume();
			}
		}
		if(volumeSum > this.geneticAlgorithm.getMaxValue()) {
			evaluationGrade = 1.0;
		}
				
		return evaluationGrade;
	}

}
