package entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;

public class ProductDAOImpl implements ProductDAO {

	public ProductDAOImpl() {
	}

	@Override
	public List<Product> findAll() {
		List<Product> products = new ArrayList<>();

		Connection connection = DBConnection.conector();
		try {
			Statement st = connection.createStatement();
			String sql = "select name_product, volume_product, price_product, quantity_product from products";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString(1);
				Double volume = rs.getDouble(2);
				Double price = rs.getDouble(3);
				Integer quantity = rs.getInt(4);
				for (int i = 0; i < quantity; i++) {
					products.add(new Product(name, volume, price));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error executing query: " + e.getMessage());
			e.printStackTrace();
		}

		return products;
	}

}
