package domain;

import java.util.Objects;

public class Customer {

  private Double accountNo;

  private String name;

  private String address;

  private double contactNo;

  public Customer() {}

  public double getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(Double accountNo) {
    this.accountNo = accountNo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public double getContactNo() {
    return contactNo;
  }

  public void setContactNo(double contactNo) {
    this.contactNo = contactNo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Customer)) return false;
    Customer customer = (Customer) o;
    return Double.compare(customer.getContactNo(), getContactNo()) == 0
        && Objects.equals(getAccountNo(), customer.getAccountNo())
        && Objects.equals(getName(), customer.getName())
        && Objects.equals(getAddress(), customer.getAddress());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getAccountNo(), getName(), getAddress(), getContactNo());
  }
}
