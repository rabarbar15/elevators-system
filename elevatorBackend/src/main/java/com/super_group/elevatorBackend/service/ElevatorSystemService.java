package com.super_group.elevatorBackend.service;


import com.super_group.elevatorBackend.model.ElevatorModel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ElevatorSystemService {
    private final Map<Integer, ElevatorModel> elevators = new HashMap<>();
    private static final int MAX_FLOORS = 21;

    public ElevatorSystemService() {
        addElevator(0);
    }


    public void addElevator(int id) {
        if (!elevators.containsKey(id)) {
            ElevatorModel elevator = new ElevatorModel(id);
            elevators.put(id, elevator);
        }
    }

    public void pickup(int elevatorId, int floor, int direction) throws Exception {
        if (floor > MAX_FLOORS) {
            throw new Exception("Max floor is 15");
        }
//        elevator.pickupRequest(floor, direction);
        ElevatorModel elevator = elevators.get(elevatorId);
        if (elevator != null) {
            elevator.pickupRequest(floor, direction);
        } else {
            throw new Exception("Elevator with id " + elevatorId + " not found.");
        }
    }

    public void step(int elevatorId) {
        ElevatorModel elevator = elevators.get(elevatorId);
        if (elevator != null) {
            status(elevatorId);
            elevator.move();
        }

    }

    public String status(int elevatorId) {
        ElevatorModel elevator = elevators.get(elevatorId);
        if (elevator != null) {
            return elevator.status();
        }
        return "Elevator with id " + elevatorId + " not found.";
    }

    public List<Integer> getElevatorIds() {
        return List.copyOf(elevators.keySet());
    }
}

