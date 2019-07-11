package com.revature.repository;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementRepository {

	public boolean submitReimbursement(long eId, double amount, String title);
	public List<Reimbursement> findEmployeePendingReimbursement(long eId);
	public List<Reimbursement> findEmployeeResolvedReimbursement(long eId);
	public boolean approveReimbursement(long mId, long rId);
	public boolean denyReimbursement(long mId, long rId);
	public List<Reimbursement> findAllPendingReimbursement();
	public List<Reimbursement> findAllResolvedReimbursement();
	public List<Reimbursement> findEmployeeReimbursement(long eId);
}
