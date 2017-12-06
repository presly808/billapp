package ua.artcode.billapp.model;

import javax.persistence.*;

/**
 * Created by serhii on 03.12.17.
 */
@Entity
@DiscriminatorValue("COMPANY")
public class Company extends BaseAccount {

    @Column()
    private String companyName;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column
    private String additionalInfo;


    @Column
    // may be enum
    private String type;


    public Company() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
