package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.iiht.evaluation.coronokit.customException.CoronaKitException;
import com.iiht.evaluation.coronokit.model.ProductMaster;



public class ProductMasterDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ProductMasterDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	public ProductMasterDao() {
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	// add DAO methods as per requirements
	
	public static final String INS_Products_QRY = "INSERT INTO Products (id,productName,cost,productDescription) values(?,?,?,?,?,?,?)";
	public static final String UPD_Products_QRY = "UPDATE Products SET productName=?,cost=?,productDescription=? WHERE id=?";
	public static final String DEL_Products_QRY = "DELETE FROM Products WHERE id=?";
	public static final String SEL_Products_QRY_BY_ID = "SELECT id,productName,cost,productDescription FROM Products WHERE id=?";
	public static final String SEL_ALL_Products_QRY = "SELECT id,productName,cost,productDescription FROM Products";

	public ProductMaster add(ProductMaster ProductMaster) throws CoronaKitException {
		if (ProductMaster != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(INS_Products_QRY)) {

				pst.setInt(1, ProductMaster.getId());
				pst.setString(2, ProductMaster.getProductName());
				pst.setString(3, ProductMaster.getCost());
				pst.setString(4, ProductMaster.getProductDescription());

				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				throw new CoronaKitException("Saving the ProductMaster failed!");
			}
		}
		return ProductMaster;
	}

	public ProductMaster save(ProductMaster ProductMaster) throws CoronaKitException {
		if (ProductMaster != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(UPD_Products_QRY)) {

				pst.setInt(1, ProductMaster.getId());
				pst.setString(2, ProductMaster.getProductName());
				pst.setString(3, ProductMaster.getCost());
				pst.setString(4, ProductMaster.getProductDescription());

				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				throw new CoronaKitException("Saving the ProductMaster failed!");
			}
		}
		return ProductMaster;
	}

	public boolean deleteById(Integer id) throws CoronaKitException {
		boolean isDeleted = false;
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(DEL_Products_QRY)) {

			pst.setInt(1, id);

			int rowsCount = pst.executeUpdate();

			isDeleted = rowsCount > 0;

		} catch (SQLException | NamingException exp) {
			throw new CoronaKitException("Deleting the ProductMaster failed!");
		}

		return isDeleted;
	}

	public ProductMaster getById(Integer id) throws CoronaKitException {
		ProductMaster ProductMaster = null;

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_Products_QRY_BY_ID)) {

			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				ProductMaster = new ProductMaster();
				ProductMaster.setId(rs.getInt(1));
				ProductMaster.setProductName(rs.getString(2));
				ProductMaster.setCost(rs.getString(3));
				ProductMaster.setProductDescription(rs.getString(4));
			}

		} catch (SQLException | NamingException exp) {
			throw new CoronaKitException("Retrival the ProductMaster failed!");
		}

		return ProductMaster;
	}

	public List<ProductMaster> getAll() throws CoronaKitException {
		List<ProductMaster> ProductMasters = new ArrayList<>();

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ALL_Products_QRY)) {

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				ProductMaster ProductMaster = new ProductMaster();
				pst.setInt(1, ProductMaster.getId());
				pst.setString(2, ProductMaster.getProductName());
				pst.setString(3, ProductMaster.getCost());
				pst.setString(4, ProductMaster.getProductDescription());
				
				ProductMasters.add(ProductMaster);
			}
			
			if(ProductMasters.isEmpty()) {
				ProductMasters=null;
			}
		} catch (SQLException | NamingException exp) {
			throw new CoronaKitException("Retrival the ProductMaster failed!");
		}
		return ProductMasters;
	}
	
}