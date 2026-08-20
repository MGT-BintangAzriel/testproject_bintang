package wf.path_test.general.app;



import java.util.Collection;

import wf.path_test.general.domain.model.*;

public class ImartForm extends ImartWorkflowForm {
	
	private String f_id;
	private String f_system_matter_id;
	private String f_user_data_id;
	private String f_name;
	private String f_age;
	private String f_note;
	
	private Collection<DetailTableModel> d_list_detail_info;
	private Collection<AttachmentModel> d_list_attachment;
	
	public String getF_id() {
		return f_id;
	}
	public Collection<AttachmentModel> getD_list_attachment() {
		return d_list_attachment;
	}
	public void setD_list_attachment(Collection<AttachmentModel> d_list_attachment) {
		this.d_list_attachment = d_list_attachment;
	}
	public void setF_id(String f_id) {
		this.f_id = f_id;
	}
	public String getF_system_matter_id() {
		return f_system_matter_id;
	}
	public void setF_system_matter_id(String f_system_matter_id) {
		this.f_system_matter_id = f_system_matter_id;
	}
	public String getF_user_data_id() {
		return f_user_data_id;
	}
	public void setF_user_data_id(String f_user_data_id) {
		this.f_user_data_id = f_user_data_id;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getF_age() {
		return f_age;
	}
	public void setF_age(String f_age) {
		this.f_age = f_age;
	}
	public String getF_note() {
		return f_note;
	}
	public void setF_note(String f_note) {
		this.f_note = f_note;
	}
	public Collection<DetailTableModel> getD_list_detail_info() {
		return d_list_detail_info;
	}
	public void setD_list_detail_info(Collection<DetailTableModel> d_list_detail_info) {
		this.d_list_detail_info = d_list_detail_info;
	}
	
	
	

}


