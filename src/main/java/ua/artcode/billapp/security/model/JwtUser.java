package ua.artcode.billapp.security.model;

public class JwtUser {

    private String phone;
    private long id;
    private String role;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
