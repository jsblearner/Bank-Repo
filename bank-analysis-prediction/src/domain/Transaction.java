package domain;

import java.util.Date;
import java.util.Objects;

public class Transaction implements Comparable<Transaction> {

  private String transactionId;

  private Date transactionDate;

  private Double senderAccountNo;

  private Double receiverAccountNo;

  private Double amount;

  public Transaction() {}

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public Date getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
  }

  public Double getSenderAccountNo() {
    return senderAccountNo;
  }

  public void setSenderAccountNo(Double senderAccountNo) {
    this.senderAccountNo = senderAccountNo;
  }

  public Double getReceiverAccountNo() {
    return receiverAccountNo;
  }

  public void setReceiverAccountNo(Double receiverAccountNo) {
    this.receiverAccountNo = receiverAccountNo;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Transaction)) return false;
    Transaction that = (Transaction) o;
    return Objects.equals(getTransactionId(), that.getTransactionId())
        && Objects.equals(getTransactionDate(), that.getTransactionDate())
        && Objects.equals(getSenderAccountNo(), that.getSenderAccountNo())
        && Objects.equals(getReceiverAccountNo(), that.getReceiverAccountNo())
        && Objects.equals(getAmount(), that.getAmount());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getTransactionId(),
        getTransactionDate(),
        getSenderAccountNo(),
        getReceiverAccountNo(),
        getAmount());
  }

  @Override
  public int compareTo(Transaction transaction) {
    if (this.getTransactionDate().getMonth() < transaction.getTransactionDate().getMonth()) {
      return -1;
    }
    return 1;
  }
}
