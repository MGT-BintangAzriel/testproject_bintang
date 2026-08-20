package wf.path_test.general.domain.model;

public class HeaderModel {

	private int id;
	private String system_matter_id;
	private String user_data_id;
	private String status;
	private String created_at;
	private String updated_at;
	private String mail_status;

	public String getMail_status() {
		return mail_status;
	}

	public void setMail_status(String mail_status) {
		this.mail_status = mail_status;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}
