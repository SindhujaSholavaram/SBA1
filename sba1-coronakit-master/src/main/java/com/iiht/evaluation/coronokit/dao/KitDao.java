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
import com.iiht.evaluation.coronokit.model.KitDetail;



public class KitDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public KitDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
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
	
	public static final String INS_KitDetail_QRY = "INSERT INTO KitDetails (id,coronaKitId,productId,quantity,amount) values(?,?,?,?,?,?,?)";
	public static final String UPD_KitDetail_QRY = "UPDATE KitDetails SET productId=?,quantity=? WHERE id=?";
	public static final String DEL_KitDetail_QRY = "DELETE FROM KitDetails WHERE coronaKitId=?";
	public static final String SEL_KitDetail_QRY_BY_ID = "SELECT id,coronaKitId,productId,quantity,amount FROM KitDetails WHERE coronaKitId=?";
	public static final String SEL_ALL_KitDetail_QRY = "SELECT id,coronaKitId,productId,quantity,amount FROM KitDetails";

	public KitDetail add(KitDetail KitDetail) throws CoronaKitException {
		if (KitDetail != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(INS_KitDetail_QRY)) {

				pst.setInt(1, KitDetail.getId());
				pst.setInt(2, KitDetail.getCoronaKitId());
				pst.setInt(3, KitDetail.getProductId());
				pst.setInt(4, KitDetail.getQuantity());
				pst.setInt(5, KitDetail.getAmount());

				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				throw new CoronaKitException("Saving the KitDetail failed!");
			}
		}
		return KitDetail;
	}

	public KitDetail save(KitDetail KitDetail) throws CoronaKitException {
		if (KitDetail != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(UPD_KitDetail_QRY)) {

				pst.setInt(1, KitDetail.getId());
				pst.setInt(2, KitDetail.getCoronaKitId());
				pst.setInt(3, KitDetail.getProductId());
				pst.setInt(4, KitDetail.getQuantity());
				pst.setInt(5, KitDetail.getAmount());

				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				throw new CoronaKitException("Saving the KitDetail failed!");
			}
		}
		return KitDetail;
	}

	public boolean deleteById(Integer coronaKitId) throws CoronaKitException {
		boolean isDeleted = false;
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(DEL_KitDetail_QRY)) {

			pst.setInt(1, coronaKitId);

			int rowsCount = pst.executeUpdate();

			isDeleted = rowsCount > 0;

		} catch (SQLException | NamingException exp) {
			throw new CoronaKitException("Deleting the KitDetail failed!");
		}

		return isDeleted;
	}

	public KitDetail getById(Integer coronaKitId) throws CoronaKitException {
		KitDetail KitDetail = null;

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_KitDetail_QRY_BY_ID)) {

			pst.setInt(2, coronaKitId);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				KitDetail = new KitDetail();
				KitDetail.setId(rs.getInt(1));
				KitDetail.setCoronaKitId(rs.getInt(2));
				KitDetail.setProductId(rs.getInt(3));
				KitDetail.setQuantity(rs.getInt(4));
				KitDetail.setAmount(rs.getInt(5));
			}

		} catch (SQLException | NamingException exp) {
			throw new CoronaKitException("Retrival the KitDetail failed!");
		}

		return KitDetail;
	}

	public List<KitDetail> getAll() throws CoronaKitException {
		List<KitDetail> KitDetails = new ArrayList<>();

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ALL_KitDetail_QRY)) {

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				KitDetail KitDetail = new KitDetail();
				pst.setInt(1, KitDetail.getId());
				pst.setInt(2, KitDetail.getCoronaKitId());
				pst.setInt(3, KitDetail.getProductId());
				pst.setInt(4, KitDetail.getQuantity());
				pst.setInt(5, KitDetail.getAmount());
				
				KitDetails.add(KitDetail);
			}
			
			if(KitDetails.isEmpty()) {
				KitDetails=null;
			}
		} catch (SQLException | NamingException exp) {
			throw new CoronaKitException("Retrival the KitDetail failed!");
		}
		return KitDetails;
	}
}