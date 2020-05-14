package mapper;

import domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDataMapper implements DataMapper {

  @Override
  public Map<Double, ?> run(List<String> dataFromFile) {
    List<Customer> customerList = new ArrayList<>();
    this.createCustomerListFromResult(dataFromFile, customerList);
    Map<Double, Customer> customerMap = this.formCustomerPersistableFromList(customerList);
    return customerMap;
  }

  public Map<Double, Customer> formCustomerPersistableFromList(List<Customer> customerList) {
    HashMap<Double, Customer> customerMap = new HashMap<Double, Customer>();
    customerList.forEach(
        customer -> {
          customerMap.put(customer.getAccountNo(), customer);
        });
    return customerMap;
  }

  public void createCustomerListFromResult(List<String> resultList, List<Customer> customerList) {
    resultList.forEach(
        customerElement -> {
          String fields[] = customerElement.split("\\|");
          Customer customer = new Customer();
          this.mapCustomerToPersistable(customer, fields);
          customerList.add(customer);
        });
  }

  public void mapCustomerToPersistable(Customer customer, String dataFields[]) {
    customer.setAccountNo(Double.parseDouble(dataFields[0]));
    customer.setName(dataFields[1]);
    customer.setAddress(dataFields[2]);
    customer.setContactNo(Double.parseDouble(dataFields[3]));
  }
}
