package wf.practice5_bintang.general.domain.model;

public class AgreementPaymentDetailModel {
	
	private int id;
	private String system_matter_id;
	private String user_data_id ;
	private String created_at;
	private String updated_at;
	
	private String row_no;
	private String brand;
	private String type;
	private String payment_amount;
	private String payment_date;
	
	private String category;
	
	private String recurring;
	private String recurring_yes;
	private String recurring_no;
	
	private String paid_by;
	private String paid_by_cash;
	private String paid_by_card;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSystem_matter_id() {
		return system_matter_id;
	}
	public void setSystem_matter_id(String system_matter_id) {
		this.system_matter_id = system_matter_id;
	}
	public String getUser_data_id() {
		return user_data_id;
	}
	public void setUser_data_id(String user_data_id) {
		this.user_data_id = user_data_id;
	}
	public String getRow_no() {
		return row_no;
	}
	public void setRow_no(String row_no) {
		this.row_no = row_no;
	}
	public String getPayment_amount() {
		return payment_amount;
	}
	public void setPayment_amount(String payment_amount) {
		this.payment_amount = payment_amount;
	}
	public String getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getRecurring() {
		return recurring;
	}
	public void setRecurring(String recurring) {
		this.recurring = recurring;
	}
	public String getRecurring_yes() {
		return recurring_yes;
	}
	public void setRecurring_yes(String recurring_yes) {
		this.recurring_yes = recurring_yes;
	}
	public String getRecurring_no() {
		return recurring_no;
	}
	public void setRecurring_no(String recurring_no) {
		this.recurring_no = recurring_no;
	}
	public String getPaid_by() {
		return paid_by;
	}
	public void setPaid_by(String paid_by) {
		this.paid_by = paid_by;
	}
	public String getPaid_by_cash() {
		return paid_by_cash;
	}
	public void setPaid_by_cash(String paid_by_cash) {
		this.paid_by_cash = paid_by_cash;
	}
	public String getPaid_by_card() {
		return paid_by_card;
	}
	public void setPaid_by_card(String paid_by_card) {
		this.paid_by_card = paid_by_card;
	}
}
