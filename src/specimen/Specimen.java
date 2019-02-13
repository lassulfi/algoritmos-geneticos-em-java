package specimen;

import java.util.ArrayList;
import java.util.List;

public class Specimen {
	
	private List<Double> volumes = new ArrayList<>(); //Store values for all volumes
	private List<Double> prices = new ArrayList<>(); //Store values for all prices
	
	private double limitVolume;
	private double evalutationGrade;
	private double totalVolume;
	private int generation;
	
	private List<Integer> chromosomes = new ArrayList<>();
	
	public Specimen(List<Double> volumes, List<Double> prices, double limitVolume) {
		this.volumes = volumes;
		this.prices = prices;
		this.limitVolume = limitVolume;
		
		this.totalVolume = 0.0;
		this.evalutationGrade = 0.0;
		this.generation = 0;
		
		for(int i = 0; i < this.volumes.size(); i++) {
			if(Math.random() < 0.5) {
				this.chromosomes.add(0);
			} else {
				this.chromosomes.add(1);
			}
		}
	}
	
	public void evaluation() {
		double grade = 0.0;
		double volumeSum = 0.0;
		
		for(int i = 0; i < chromosomes.size(); i++) {
			if(chromosomes.get(i) == 1) {
				grade += this.prices.get(i);
				volumeSum += this.volumes.get(i);
			}
		}
		
		if(volumeSum > this.limitVolume) grade = 1.0;
		
		this.evalutationGrade = grade;
		this.totalVolume = volumeSum;
	}

	public List<Double> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<Double> volumes) {
		this.volumes = volumes;
	}

	public List<Double> getPrices() {
		return prices;
	}

	public void setPrices(List<Double> prices) {
		this.prices = prices;
	}

	public double getLimitVolume() {
		return limitVolume;
	}

	public void setLimitVolume(double limitVolume) {
		this.limitVolume = limitVolume;
	}

	public double getEvalutationGrade() {
		return evalutationGrade;
	}

	public void setEvalutationGrade(double evalutationGrade) {
		this.evalutationGrade = evalutationGrade;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public List<Integer> getChromosomes() {
		return chromosomes;
	}

	public void setChromosomes(List<Integer> chromosomes) {
		this.chromosomes = chromosomes;
	}

	public double getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(double totalVolume) {
		this.totalVolume = totalVolume;
	}
}
