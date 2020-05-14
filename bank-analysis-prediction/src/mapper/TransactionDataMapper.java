package mapper;

import domain.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionDataMapper implements DataMapper {
  @Override
  public Map<String, ?> run(List<String> dataFromFile) {
    List<Transaction> transactionList = new ArrayList<>();
    this.createTransactionListFromResult(dataFromFile, transactionList);
    Map<String, Transaction> transactionMap =
        this.formTransactionPersistableFromList(transactionList);
    return transactionMap;
  }

  public Map<String, Transaction> formTransactionPersistableFromList(
      List<Transaction> transactionList) {
    HashMap<String, Transaction> transactionMap = new HashMap<String, Transaction>();
    transactionList.forEach(
        transaction -> {
          transactionMap.put(transaction.getTransactionId(), transaction);
        });
    return transactionMap;
  }

  public void createTransactionListFromResult(
      List<String> resultList, List<Transaction> transactionList) {
    resultList.forEach(
        transactionElement -> {
          String fields[] = transactionElement.split("\\|");
          Transaction transaction = new Transaction();
          try {
            this.mapTransactionToPersistable(transaction, fields);
          } catch (ParseException e) {
            e.printStackTrace();
          }
          transactionList.add(transaction);
        });
  }

  public void mapTransactionToPersistable(Transaction transaction, String dataFields[])
      throws ParseException {
    transaction.setTransactionId(dataFields[0]);
    transaction.setTransactionDate(this.dateFormatter(dataFields[1]));
    transaction.setSenderAccountNo(Double.parseDouble(dataFields[2]));
    transaction.setReceiverAccountNo(Double.parseDouble(dataFields[3]));
    transaction.setAmount(Double.parseDouble(dataFields[4]));
  }

  public Date dateFormatter(String dateString) throws ParseException {
    SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MMM-yyyy");
    Date date = formatter2.parse(dateString);
    return date;
  }
}
