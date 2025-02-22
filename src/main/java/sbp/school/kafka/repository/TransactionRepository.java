package sbp.school.kafka.repository;

import sbp.school.kafka.model.Operation;
import sbp.school.kafka.model.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionRepository {

    List<Transaction> transactions = new ArrayList<>();

    public String saveTransaction(Operation operation) {
        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID().toString())
                .operationId(operation.getId())
                .time(LocalDateTime.now())
                .build();
        transactions.add(transaction);
        return transaction.getId();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
