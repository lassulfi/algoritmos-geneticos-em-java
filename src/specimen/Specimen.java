package specimen;

import java.util.ArrayList;
import java.util.List;

public class Specimen implements Comparable<Specimen> {

	private List<Double> volumes = new ArrayList<>(); // Store values for all volumes
	private List<Double> prices = new ArrayList<>(); // Store values for all prices

	private Double limitVolume;
	private Double evalutationGrade;
	private Double totalVolume;
	private int generation;

	private List<Integer> chromosomes = new ArrayList<>();

	public Specimen(List<Double> volumes, List<Double> prices, Double limitVolume) {
		this.volumes = volumes;
		this.prices = prices;
		this.limitVolume = limitVolume;

		this.totalVolume = 0.0;
		this.evalutationGrade = 0.0;
		this.generation = 0;

		for (int i = 0; i < this.volumes.size(); i++) {
			if (Math.random() < 0.5) {
				this.chromosomes.add(0);
			} else {
				this.chromosomes.add(1);
			}
		}
	}
	

	public void evaluation() {
		double grade = 0.0;
		double volumeSum = 0.0;

		for (int i = 0; i < chromosomes.size(); i++) {
			if (chromosomes.get(i) == 1) {
				grade += this.prices.get(i);
				volumeSum += this.volumes.get(i);
			}
		}

		if (volumeSum > this.limitVolume)
			grade = 1.0;

		this.evalutationGrade = grade;
		this.totalVolume = volumeSum;
	}

	public List<Specimen> crossover(Specimen anotherSpecimen) {
		int cut = (int) Math.round(Math.random() * this.chromosomes.size());

		List<Integer> child1 = new ArrayList<>();
		child1.addAll(anotherSpecimen.getChromosomes().subList(0, cut));
		child1.addAll(this.chromosomes.subList(cut, this.chromosomes.size()));

		List<Integer> child2 = new ArrayList<>();
		child2.addAll(this.chromosomes.subList(0, cut));
		child2.addAll(anotherSpecimen.getChromosomes().subList(cut, anotherSpecimen.getChromosomes().size()));
		
		List<Specimen> children = new ArrayList<>();
		children.add(new Specimen(this.volumes, this.prices, this.limitVolume));
		children.add(new Specimen(this.volumes, this.prices, this.limitVolume));
		
		children.get(0).setChromosomes(child1);
		children.get(0).setGeneration(this.generation++);
		
		children.get(1).setChromosomes(child2);
		children.get(1).setGeneration(this.generation++);

		return children;
	}
	
	public Specimen mutation(double ratio) {
		for(Integer chromosome : this.chromosomes) {
			if(Math.random() < ratio) {
				chromosome = 1;
			} else {
				chromosome = 0;
			}
		}
		
		return this;
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

	public Double getLimitVolume() {
		return limitVolume;
	}

	public void setLimitVolume(Double limitVolume) {
		this.limitVolume = limitVolume;
	}

	public Double getEvalutationGrade() {
		return evalutationGrade;
	}

	public void setEvalutationGrade(Double evalutationGrade) {
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

	public Double getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(Double totalVolume) {
		this.totalVolume = totalVolume;
	}

	@Override
	public String toString() {
		return "Specimen [volumes=" + volumes + ", prices=" + prices + ", limitVolume=" + limitVolume
				+ ", evalutationGrade=" + evalutationGrade + ", totalVolume=" + totalVolume + ", generation="
				+ generation + ", chromosomes=" + chromosomes + "]";
	}

	@Override
	public int compareTo(Specimen sp) {
		return sp.getEvalutationGrade().compareTo(this.evalutationGrade);
	}
}
