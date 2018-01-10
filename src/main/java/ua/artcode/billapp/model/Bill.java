package ua.artcode.billapp.model;

import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * Created by serhii on 03.12.17.
 */
@Entity
public class Bill extends IdEntity {

    @Column(unique = true, nullable = false)
    private String billId;

    @ManyToOne()
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    private Company provider;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column
    private int warrantyPeriodDays;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private OffsetDateTime start;

    @Column
    private OffsetDateTime end;

    @Enumerated(value = EnumType.STRING)
    private BillStatus billStatus;

    public Bill() {
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Company getProvider() {
        return provider;
    }

    public void setProvider(Company provider) {
        this.provider = provider;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getWarrantyPeriodDays() {
        return warrantyPeriodDays;
    }

    public void setWarrantyPeriodDays(int warrantyPeriodDays) {
        this.warrantyPeriodDays = warrantyPeriodDays;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OffsetDateTime getStart() {
        return start;
    }

    public void setStart(OffsetDateTime start) {
        this.start = start;
    }

    public OffsetDateTime getEnd() {
        return end;
    }

    public void setEnd(OffsetDateTime end) {
        this.end = end;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }
}
