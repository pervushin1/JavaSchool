package sbp.school.kafka.repository;

import sbp.school.kafka.model.Operation;

import java.util.HashMap;

public class OperationRepository {

    HashMap<String, Operation> operations = new HashMap<>();

    public void save(Operation operation) {
        operations.put(operation.getId(), operation);
    }

    public Operation get(String id) {
        return operations.get(id);
    }
}
