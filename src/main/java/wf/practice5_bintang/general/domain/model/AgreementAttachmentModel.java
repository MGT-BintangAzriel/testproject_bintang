package wf.practice5_bintang.general.domain.model;

public class AgreementAttachmentModel {

	private int id;
	private String system_matter_id;
	private String user_data_id;
	private String created_at;
	private String updated_at;

	private String file_name;
	private String file_path;
	private String file_real_name;
	private String file_type;
	private String file_size;
	private String file_extension;

	private String file_size_convert;
	private String file_extension_convert;
	private String file_extension_category;

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

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_real_name() {
		return file_real_name;
	}

	public void setFile_real_name(String file_real_name) {
		this.file_real_name = file_real_name;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
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

	public String getFile_size() {
		return file_size;
	}

	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}

	public String getFile_size_convert() {
		return file_size_convert;
	}

	public void setFile_size_convert(String file_size_convert) {
		this.file_size_convert = file_size_convert;
	}

	public String getFile_extension() {
		return file_extension;
	}

	public void setFile_extension(String file_extension) {
		this.file_extension = file_extension;
	}

	public String getFile_extension_convert() {
		return file_extension_convert;
	}

	public void setFile_extension_convert(String file_extension_convert) {
		this.file_extension_convert = file_extension_convert;
	}

	public String getFile_extension_category() {
		return file_extension_category;
	}

	public void setFile_extension_category(String file_extension_category) {
		this.file_extension_category = file_extension_category;
	}

}
