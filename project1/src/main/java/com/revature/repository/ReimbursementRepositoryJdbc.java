package com.revature.repository;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

//import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementRepositoryJdbc implements ReimbursementRepository {

	private static final Logger LOGGER = Logger.getLogger(ReimbursementRepositoryJdbc.class);
	
	public long getMaxReimbursementId() {
		LOGGER.trace("Entering getMaxReimbursementId method without parameter.");
		try (Connection connect = ConnectionUtil.getConnection()) {
			String sql = "SELECT MAX(REIMBURSEMENT_ID) FROM REIMBURSEMENT";
			PreparedStatement statement = connect.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			long maxId = 0;
			while (result.next()) {
				maxId = result.getLong("MAX(REIMBURSEMENT_ID)");
			}
			return maxId;
		}
		catch (SQLException exc) {
			LOGGER.error("Could not get max reimbursement ID.", exc);
		}
		return 0;
	}
	@Override
	public boolean submitReimbursement(long eId, double amount, String title) {
		LOGGER.trace("Entering submitReimbursement method with parameter.");
		try (Connection connect = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO REIMBURSEMENT (REIMBURSEMENT_ID,REIMBURSEMENT_EMPLOYEE_ID,REIMBURSEMENT_AMOUNT,REIMBURSEMENT_STATUS,REIMBURSEMENT_TITLE) VALUES (?,?,?,?,?)";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setLong(1, getMaxReimbursementId() + 1);
			statement.setLong(2, eId);
			statement.setDouble(3, amount);
			statement.setString(4, "PENDING");
			statement.setString(5, title);
			if (statement.executeUpdate() > 0) {
				System.out.println("Reimbursement Request Success!");
				return true;
			}
		}
		catch (SQLException exc) {
			LOGGER.error("Could not submit reimbursement request.", exc);
		}
		return false;
	}
	@Override
	public List<Reimbursement> findEmployeePendingReimbursement(long eId) {
		LOGGER.trace("Entering findEmployeePendingReimburement method with parameter.");
		try (Connection connect = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE REIMBURSEMENT_EMPLOYEE_ID = ? AND REIMBURSEMENT_STATUS = ?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setLong(1, eId);
			statement.setString(2, "PENDING");
			ResultSet result = statement.executeQuery();
			List<Reimbursement> reimbursementList = new ArrayList<>();
			while (result.next()) {
				reimbursementList.add(new Reimbursement (
						result.getLong("REIMBURSEMENT_ID"),
						result.getLong("REIMBURSEMENT_EMPLOYEE_ID"),
						result.getDouble("REIMBURSEMENT_AMOUNT"),
						result.getLong("REIMBURSEMENT_MANAGER_ID"),
						result.getString("REIMBURSEMENT_STATUS"),
						result.getString("REIMBURSEMENT_TITLE"),
						result.getString("REIMBURSEMENT_DECISION")
						));
			}
			System.out.println("finding employee PENDING reimbursements success!!");
			return reimbursementList;
		}
		catch (SQLException exc) {
			LOGGER.error("Could not get all pending reimbursement for employee.", exc);
		}
		return null;
	}

	@Override
	public List<Reimbursement> findEmployeeResolvedReimbursement(long eId) {
		LOGGER.trace("Entering findEmployeeResolvedReimburement method with parameter.");
		try (Connection connect = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE REIMBURSEMENT_EMPLOYEE_ID = ? AND REIMBURSEMENT_STATUS = ?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setLong(1, eId);
			statement.setString(2, "RESOlVED");
			ResultSet result = statement.executeQuery();
			List<Reimbursement> rList = new ArrayList<>();
			while (result.next()) {
				rList.add(new Reimbursement (
						result.getLong("REIMBURSEMENT_ID"),
						result.getLong("REIMBURSEMENT_EMPLOYEE_ID"),
						result.getDouble("REIMBURSEMENT_AMOUNT"),
						result.getLong("REIMBURSEMENT_MANAGER_ID"),
						result.getString("REIMBURSEMENT_STATUS"),
						result.getString("REIMBURSEMENT_TITLE"),
						result.getString("REIMBURSEMENT_DECISION")
						));
			}
			System.out.println("finding employee RESOLVED reimbursements success!!");
			for (Reimbursement c : rList) System.out.println(c);
			return rList;
		}
		catch (SQLException exc) {
			LOGGER.error("Could not find all resolved reimbursement for employee.", exc);
		}
		return null;
	}

	@Override
	public boolean approveReimbursement(long mId, long rId) {
		LOGGER.trace("Entering approveReimbursement method with parameter.");
		try (Connection connect = ConnectionUtil.getConnection()) {
			String sql = "UPDATE REIMBURSEMENT SET REIMBURSEMENT_MANAGER_ID = ?, REIMBURSEMENT_STATUS = ?, REIMBURSEMENT_DECISION = ? WHERE REIMBURSEMENT_ID  = ?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setLong(1, mId);
			statement.setString(2, "RESOLVED");
			statement.setString(3, "APPROVED");
			statement.setLong(4, rId);
			if (statement.executeUpdate() > 0) {
				System.out.println("Reimbursement Request Approved Success!");
				return true;
			}
		}
		catch (SQLException exc) {
			LOGGER.error("Could not approved reimbursement request.", exc);
		}
		return false;
	}

	@Override
	public boolean denyReimbursement(long mId, long rId) {
		LOGGER.trace("Entering denyReimbursement method with parameter.");
		try (Connection connect = ConnectionUtil.getConnection()) {
			String sql = "UPDATE REIMBURSEMENT SET REIMBURSEMENT_MANAGER_ID = ?, REIMBURSEMENT_STATUS = ?, REIMBURSEMENT_DECISION = ? WHERE REIMBURSEMENT_ID  = ?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setLong(1, mId);
			statement.setString(2, "RESOLVED");
			statement.setString(3, "DENIED");
			statement.setLong(4, rId);
			if (statement.executeUpdate() > 0) {
				System.out.println("Reimbursement Request Deny Success!");
				return true;
			}
		}
		catch (SQLException exc) {
			LOGGER.error("Could not deny reimbursement request.", exc);
		}
		return false;
	}

	@Override
	public List<Reimbursement> findAllPendingReimbursement() {
		LOGGER.trace("Entering findAllPendingReimbursement method with no parameter.");
		try (Connection connect = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE REIMBURSEMENT_STATUS = ?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, "PENDING");
			ResultSet result = statement.executeQuery();
			List<Reimbursement> rList = new ArrayList<>();
			while (result.next()) {
				rList.add(new Reimbursement (
						result.getLong("REIMBURSEMENT_ID"),
						result.getLong("REIMBURSEMENT_EMPLOYEE_ID"),
						result.getDouble("REIMBURSEMENT_AMOUNT"),
						result.getLong("REIMBURSEMENT_MANAGER_ID"),
						result.getString("REIMBURSEMENT_STATUS"),
						result.getString("REIMBURSEMENT_TITLE"),
						result.getString("REIMBURSEMENT_DECISION")
						));
			}
			System.out.println("finding all pending reimbursements success!!");
			return rList;
		}
		catch (SQLException exc) {
			LOGGER.error("Could not find all pending reimbursements.", exc);
		}
		return null;
	}

	@Override
	public List<Reimbursement> findAllResolvedReimbursement() {
		LOGGER.trace("Entering findAllResolvedReimbursement method with no parameter.");
		try (Connection connect = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE REIMBURSEMENT_STATUS = ?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, "RESOLVED");
			ResultSet result = statement.executeQuery();
			List<Reimbursement> rList = new ArrayList<>();
			while (result.next()) {
				rList.add(new Reimbursement (
						result.getLong("REIMBURSEMENT_ID"),
						result.getLong("REIMBURSEMENT_EMPLOYEE_ID"),
						result.getDouble("REIMBURSEMENT_AMOUNT"),
						result.getLong("REIMBURSEMENT_MANAGER_ID"),
						result.getString("REIMBURSEMENT_STATUS"),
						result.getString("REIMBURSEMENT_TITLE"),
						result.getString("REIMBURSEMENT_DECISION")
						));
			}
			System.out.println("finding all resolved reimbursements success!!");
			return rList;
		}
		catch (SQLException exc) {
			LOGGER.error("Could not find all resolved reimbursements.", exc);
		}
		return null;
	}

	@Override
	public List<Reimbursement> findEmployeeReimbursement(long eId) {
		LOGGER.trace("Entering findEmployeeReimbursement method with parameter.");
		try (Connection connect = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE REIMBURSEMENT_EMPLOYEE_ID = ?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setLong(1, eId);
			ResultSet result = statement.executeQuery();
			List<Reimbursement> rList = new ArrayList<>();
			while (result.next()) {
				rList.add(new Reimbursement (
						result.getLong("REIMBURSEMENT_ID"),
						result.getLong("REIMBURSEMENT_EMPLOYEE_ID"),
						result.getDouble("REIMBURSEMENT_AMOUNT"),
						result.getLong("REIMBURSEMENT_MANAGER_ID"),
						result.getString("REIMBURSEMENT_STATUS"),
						result.getString("REIMBURSEMENT_TITLE"),
						result.getString("REIMBURSEMENT_DECISION")
						));
			}
			System.out.println("finding employee reimbursements success!!");
			return rList;
		}
		catch (SQLException exc) {
			LOGGER.error("Could not find all employee reimbursement.", exc);
		}
		return null;
	}
	/*
	public static void main(String[] args) {
		ReimbursementRepositoryJdbc reimbursementJdbc = new ReimbursementRepositoryJdbc();
		//reimbursementJdbc.submitReimbursement(1,100.00,"new request22222");
		//List<Reimbursement> eList = reimbursementJdbc.findEmployeeReimbursement(1);
		//for (Reimbursement a : eList) System.out.println(a);
		//reimbursementJdbc.approveReimbursement(1, 4);
		//reimbursementJdbc.denyReimbursement(1, 4);
		List<Reimbursement> pList = reimbursementJdbc.findAllPendingReimbursement();
		for (Reimbursement b : pList) System.out.println(b);
		List<Reimbursement> rList = reimbursementJdbc.findAllResolvedReimbursement();
		for (Reimbursement b : rList) System.out.println(b);
		
	}
	*/
}
