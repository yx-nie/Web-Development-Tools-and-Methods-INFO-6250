package data;

import javax.management.relation.Role;

public class UserSession {

	private String username;
    private String email;
    private UserRegistration.Role role;
    private String currentPage;
    private String pwd;
    
    public UserSession() {
        // TODO Auto-generated constructor stub
    }

    public UserSession(String username, String email, String role, String pwd ){
        super();

        this.username=username;
        this.email=email;
        this.pwd=pwd;

        if("admin".equals(role)){
            this.role= UserRegistration.Role.ADMIN;
        }else if("user".equals(role)){
            this.role= UserRegistration.Role.USER;
        }
    }
    public UserSession(String username, String email, UserRegistration.Role role, String currentPage) {
        super();

        this.username = username;
        this.email = email;
        this.role = role;
        this.currentPage = currentPage;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
    public UserRegistration.Role getRole() {
        return role;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


}
