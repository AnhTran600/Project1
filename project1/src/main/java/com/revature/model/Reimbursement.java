package com.revature.model;

public class Reimbursement implements Comparable<Reimbursement> {

	private long id;
	private long employee_id;
	private double amount;
	private long manager_id;
	private String status;
	private String title;
	private String decision;
	public Reimbursement() {}
	public Reimbursement(long id, long employee_id, double amount, long manager_id, String status, String title,
			String decision) {
		super();
		this.id = id;
		this.employee_id = employee_id;
		this.amount = amount;
		this.manager_id = manager_id;
		this.status = status;
		this.title = title;
		this.decision = decision;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(long employee_id) {
		this.employee_id = employee_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getManager_id() {
		return manager_id;
	}

	public void setManager_id(long manager_id) {
		this.manager_id = manager_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}
	
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", employee_id=" + employee_id + ", amount=" + amount + ", manager_id="
				+ manager_id + ", status=" + status + ", title=" + title + ", decision=" + decision + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((decision == null) ? 0 : decision.hashCode());
		result = prime * result + (int) (employee_id ^ (employee_id >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (manager_id ^ (manager_id >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (decision == null) {
			if (other.decision != null)
				return false;
		} else if (!decision.equals(other.decision))
			return false;
		if (employee_id != other.employee_id)
			return false;
		if (id != other.id)
			return false;
		if (manager_id != other.manager_id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public int compareTo(Reimbursement o) {
		// TODO Auto-generated method stub
		return new Long(this.id).compareTo(o.id);
	}
	
}
