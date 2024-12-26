package sbp.school.kafka.service;

import sbp.school.kafka.model.Operation;

import java.util.concurrent.ConcurrentHashMap;

public class ChekerService {

    ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, String> getMap() {
        return map;
    }

    public Operation retryIfExist() {

        return null;
    }
}
