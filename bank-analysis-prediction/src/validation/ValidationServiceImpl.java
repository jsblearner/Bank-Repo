package validation;

import domain.Customer;
import domain.MonthUtilService;
import domain.Transaction;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ValidationServiceImpl implements ValidationService {
  Map<Double, Customer> customerMap;
  Map<String, Transaction> transactionMap;
  private static int INDEX_OF_ID = 1;

  public ValidationServiceImpl(
      Map<Double, Customer> customerMap, Map<String, Transaction> transactionMap) {
    this.customerMap = customerMap;
    this.transactionMap = transactionMap;
  }

  @Override
  public void getResults() {
    ArrayList<Pair<Double, HashSet<Double>>> suspiciousAccountList =
        this.getSuspiciousAccount(customerMap);

    ArrayList<Transaction> suspiciousTransactions =
        this.getSuspiciousTransactions(transactionMap, suspiciousAccountList);
    this.showResultsAccordingToMonth(suspiciousTransactions);

    this.showResultsOfSuspiciousAccounts(suspiciousAccountList);
  }

  private void showResultsOfSuspiciousAccounts(
      ArrayList<Pair<Double, HashSet<Double>>> suspiciousAccountList) {

    System.out.println("\n\nSuspicious Accounts");
    suspiciousAccountList.forEach(
        account -> {
          List<Double> accountList = new ArrayList<>(account.getValue());
          List<String> accountListString = new ArrayList<>();

          accountList.forEach(
              act -> {
                accountListString.add(String.format("%.0f", act));
              });

          System.out.println(accountListString);
        });
  }

  private ArrayList<Transaction> getSuspiciousTransactions(
      Map<String, Transaction> transactionMap,
      ArrayList<Pair<Double, HashSet<Double>>> suspiciousAccountList) {
    ArrayList<Transaction> transactionSuspicious = new ArrayList<>();
    ArrayList<Pair<Double, Double>> pairedAccounts =
        this.pairedSuspiciousAccounts(suspiciousAccountList);
    transactionMap.forEach(
        (transactionId, transaction) -> {
          Pair<Double, Double> transactionData =
              new Pair<>(transaction.getSenderAccountNo(), transaction.getReceiverAccountNo());
          for (int i = 0; i < pairedAccounts.size(); i++) {
            if (pairedAccounts.get(i).equals(transactionData)) {
              transactionSuspicious.add(transaction);
            }
          }
        });
    return transactionSuspicious;
  }

  private void showResultsAccordingToMonth(ArrayList<Transaction> transactionSuspicious) {
    int month = transactionSuspicious.get(0).getTransactionDate().getMonth();
    System.out.println("For the Month of " + MonthUtilService.convertToMonth(month + 1));
    Collections.sort(transactionSuspicious);
    System.out.println(transactionSuspicious.get(0).getTransactionId());
    int status = month;
    for (int i = 1; i < transactionSuspicious.size(); i++) {
      Transaction transaction = transactionSuspicious.get(i);
      int a = transaction.getTransactionDate().getMonth();
      if (status == a) {
        System.out.println(transaction.getTransactionId());
      } else {
        System.out.println("\nFor the Month of " + MonthUtilService.convertToMonth(a + 1));
        System.out.println(transaction.getTransactionId());
        status = a;
      }
    }
  }

  private ArrayList<Pair<Double, Double>> pairedSuspiciousAccounts(
      ArrayList<Pair<Double, HashSet<Double>>> suspiciousAccountList) {

    ArrayList<Pair<Double, Double>> resultList = new ArrayList<>();
    suspiciousAccountList.forEach(
        account -> {
          List<Double> list = new ArrayList<>(account.getValue());
          Pair<Double, Double> pair1 = new Pair<>(list.get(0), list.get(1));
          Pair<Double, Double> pair2 = new Pair<>(list.get(1), list.get(0));
          resultList.add(pair1);
          resultList.add(pair2);
        });
    return resultList;
  }

  private ArrayList<Pair<Double, HashSet<Double>>> getSuspiciousAccount(
      Map<Double, Customer> customerMap) {
    ArrayList<Pair<Double, HashSet<Double>>> accountNumber = new ArrayList<>();

    customerMap.forEach(
        (accNo, customer) -> {
          for (Map.Entry cust : customerMap.entrySet()) {
            if (cust.getKey() != accNo) {
              HashSet<Double> suspiciousIdList = new HashSet<>();
              if (isSameContactAndAddress(cust, customer)) {
                this.populateData(suspiciousIdList, cust, customer, accountNumber);
              }
            }
          }
        });

    this.removeDuplicatePairs(accountNumber);
    return accountNumber;
  }

  private void populateData(
      HashSet<Double> suspiciousIdList,
      Map.Entry cust,
      Customer customer,
      ArrayList<Pair<Double, HashSet<Double>>> accountNos) {
    suspiciousIdList.add((Double) cust.getKey());
    suspiciousIdList.add(customer.getAccountNo());
    Pair<Double, HashSet<Double>> suspiciousAccountPair =
        new Pair<>((Double) cust.getKey(), suspiciousIdList);
    accountNos.add(suspiciousAccountPair);
  }

  private boolean isSameContactAndAddress(Map.Entry cust, Customer customer) {
    return ((Customer) cust.getValue()).getContactNo() == customer.getContactNo()
        && ((Customer) cust.getValue()).getAddress().equals(customer.getAddress());
  }

  private void removeDuplicatePairs(ArrayList<Pair<Double, HashSet<Double>>> accountNumber) {

    for (int i = 0; i < accountNumber.size(); i++) {
      for (int j = i + 1; j < accountNumber.size(); j++) {
        if (accountNumber.get(j).getValue().containsAll(accountNumber.get(i).getValue())) {
          accountNumber.remove(j);
        }
      }
    }
  }
}
