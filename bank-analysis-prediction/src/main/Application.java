package main;

import domain.Customer;
import domain.Transaction;
import mapper.CustomerDataMapper;
import mapper.TransactionDataMapper;
import persistable.PersistableService;
import persistable.PersistableServiceImpl;
import validation.ValidationService;
import validation.ValidationServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Application {

  public static void main(String[] args) throws IOException {

    PersistableService persistable = new PersistableServiceImpl();

    /*  **Fetching Data from customer.txt** */
    List<String> customerDataFromFile =
        persistable.getData("C:\Users\sbendict\Desktop\Bank-Repo\bank-analysis-prediction\src\storage\files\customer.txt");
    CustomerDataMapper customerMapper = new CustomerDataMapper();
    Map<Double, Customer> customerMap =
        (Map<Double, Customer>) customerMapper.run(customerDataFromFile);

    /*  **Fetching Data from transaction.txt** */
    List<String> transactionDataFromFile =
        persistable.getData(
            "C:\Users\sbendict\Desktop\Bank-Repo\bank-analysis-prediction\src\storage\files\transaction.txt");
    TransactionDataMapper transactionMapper = new TransactionDataMapper();
    Map<String, Transaction> transactionMap =
        (Map<String, Transaction>) transactionMapper.run(transactionDataFromFile);

    /*  **Show the Results** */
    ValidationService validationService = new ValidationServiceImpl(customerMap, transactionMap);
    validationService.getResults();
  }
}
