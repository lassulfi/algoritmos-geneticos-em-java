package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.Product;

public class Main {

	public static void main(String[] args) {
		
		List<Product> products = Arrays.asList(new Product("Geladeira Dako", 0.751, 999.90), 
			new Product("Iphone 6", 0.000089, 2911.12),
			new Product("TV 55'", 0.400, 4346.99), 
			new Product("TV 50'", 0.290, 3999.90),
			new Product("TV 42'", 0.200, 2999.00), 
			new Product("Notebook Dell", 0.00350, 2499.90), 
			new Product("Ventilador Panassonic", 0.496, 199.90),
			new Product("Microondas electrolux", 0.0424, 308.66), 
			new Product("Microondas LG", 0.0544, 429.90),
			new Product("Microondas Panasonic", 0.0319, 299.29),
			new Product("Geladeira Brastemp", 0.635, 849.00), 
			new Product("Geladeira Consul", 0.870, 1199.89), 
			new Product("Notebook Lenovo", 0.498, 1999.90),
			new Product("Notebook Asus", 0.527, 3999.00));
		
		for(Product p : products) {
			System.out.println(p);
		}
	}

}
