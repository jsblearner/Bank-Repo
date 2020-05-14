package persistable;

import domain.Customer;

import java.util.Map;

public class CustomerDataPersistable extends DataPersistable {

  Map<Double, Customer> customerData;

  public CustomerDataPersistable(Map<Double, Customer> customerData) {
    this.customerData = customerData;
  }
}
