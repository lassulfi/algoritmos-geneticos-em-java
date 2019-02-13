package specimen;

import java.util.ArrayList;
import java.util.List;

public class Specimen {
	
	private List<Double> volumes = new ArrayList<>(); //Store values for all volumes
	private List<Double> prices = new ArrayList<>(); //Store values for all prices
	
	private double limitVolume;
	private double evalutationGrade;
	private int generation;
	
	private List<Integer> chromosomes = new ArrayList<>();
	
	public Specimen(List<Double> volumes, List<Double> prices, double limitVolume) {
		this.volumes = volumes;
		this.prices = prices;
		this.limitVolume = limitVolume;
		
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
}
