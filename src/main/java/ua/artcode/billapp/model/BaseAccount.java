package ua.artcode.billapp.model;

import javax.persistence.*;

/**
 * Created by serhii on 03.12.17.
 */
@Entity
@Inheritance
@DiscriminatorColumn(name="ACCOUNT_TYPE")
@Table
public class BaseAccount  extends IdEntity {

    @Column(nullable = false, unique = true, length = 12)
    protected String phone;

    @Column(nullable = false)
    protected String password;

    @Column(nullable = false)
    protected boolean activated = false;


    public BaseAccount() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
