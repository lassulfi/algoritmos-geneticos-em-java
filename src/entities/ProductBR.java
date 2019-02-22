package entities;

import java.util.List;

public class ProductBR {
	
	ProductDAO productDAO;
	
	public ProductBR(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	public List<Product> findAll(){
		return this.productDAO.findAll();
	}

}
