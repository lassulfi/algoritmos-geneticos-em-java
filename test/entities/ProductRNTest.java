package entities;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

public class ProductRNTest {

	@Test
	public void testFindAll() {
		ProductBR productBR = new ProductBR(new ProductDAOImpl());
		
		List<Product> products = productBR.findAll();
		
		assertNotNull(products);
	}
}
