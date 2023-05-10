package data;


public class UserRegistration {
	String name;
	String password;
	String email;
	Role role;

	public enum Role{
		ADMIN("admin"),USER("user");
		private final String roleName;

		private Role(String roleName){
			this.roleName=roleName;
		}

		public String getRoleName(){
			return roleName;
		}
	}
	public UserRegistration(){

	}
	
	public UserRegistration(String name, String password, String email, Role role) {
		super();

		this.name = name;
		this.password = password;
		this.email = email;
		this.role=role;
	}

	public UserRegistration( String name, String email, String role, String password){

		this.name=name;
		this.email=email;
		this.password=password;
		if("admin".equals(role)){
			this.role=Role.ADMIN;
		}else if("user".equals(role)){
			this.role=Role.USER;
		}
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole(){
		return role;
	}
	

}
