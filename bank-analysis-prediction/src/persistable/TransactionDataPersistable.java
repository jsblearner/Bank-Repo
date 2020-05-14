package persistable;

import domain.Transaction;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionDataPersistable extends DataPersistable {

  HashMap<String, ArrayList<Transaction>> transactionData;

  public TransactionDataPersistable(HashMap<String, ArrayList<Transaction>> transactionData) {
    this.transactionData = transactionData;
  }
}
