package com.iiht.evaluation.coronokit.model;

import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.customException.CoronaKitException;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;

public class ProductMaster {

	private int id;
	private String productName;
	private String cost;
	private String productDescription;

	public ProductMaster() {
		// TODO Auto-generated constructor stub
	}

	public ProductMaster(int id, String productName, String cost, String productDescription) {
		super();
		this.id = id;
		this.productName = productName;
		this.cost = cost;
		this.productDescription = productDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	private boolean isIdValid(Integer id) {
		return id > 0;
	}

	private boolean isProductNameValid(String productName) {
		return productName != null && (productName.length() >= 3 || productName.length() <= 10);
	}

	private boolean isCostValid(String cost) {
		return Integer.parseInt(cost) >= 0;
	}

	private boolean isProductDescriptionValid(String productDescription) {
		return productDescription != null && (productDescription.length() >= 3 || productDescription.length() <= 20);
	}

	private boolean isValidProduct(ProductMaster productMaster) throws CoronaKitException {
		List<String> errMsg = new ArrayList<>();

		boolean isValid = true;

		if (!isIdValid(productMaster.getId())) {
			isValid = false;
			errMsg.add("ID can not be null or negative or zero");
		}

		if (!isProductNameValid(productMaster.getProductName())) {
			isValid = false;
			errMsg.add("Product Name can not be blank, it must be of 3 to 10 chars in length");
		}

		if (!isCostValid(productMaster.getCost())) {
			isValid = false;
			errMsg.add("Cost must be greater than zero.");
		}

		if (!isProductDescriptionValid(productMaster.getProductDescription())) {
			isValid = false;
			errMsg.add("Product Description can not be blank, it must be of 3 to 20 chars in length");
		}

		if (!isValid) {
			throw new CoronaKitException(errMsg.toString());
		}

		return isValid;
	}

	public ProductMaster validateAndAdd(ProductMaster ProductMaster) throws CoronaKitException {
		if (ProductMaster != null) {
			if (isValidProduct(ProductMaster)) {
				ProductMasterDao ProductMasterDao = new ProductMasterDao();
				ProductMasterDao.add(ProductMaster);
			}
		}
		return ProductMaster;
	}

	public ProductMaster validateAndSave(ProductMaster ProductMaster) throws CoronaKitException {
		if (ProductMaster != null) {
			if (isValidProduct(ProductMaster)) {
				ProductMasterDao ProductMasterDao = new ProductMasterDao();
				ProductMasterDao.save(ProductMaster);
			}
		}
		return ProductMaster;
	}

	public boolean deleteProduct(int icode) throws CoronaKitException {
		ProductMasterDao ProductMasterDao = new ProductMasterDao();
		return ProductMasterDao.deleteById(icode);
	}

	public ProductMaster getProductById(int icode) throws CoronaKitException {
		ProductMasterDao ProductMasterDao = new ProductMasterDao();
		return ProductMasterDao.getById(icode);
	}

	public List<ProductMaster> getAllProducts() throws CoronaKitException {
		ProductMasterDao ProductMasterDao = new ProductMasterDao();
		return ProductMasterDao.getAll();
	}

}
