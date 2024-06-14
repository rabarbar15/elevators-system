package com.super_group.elevatorBackend.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ElevatorModel {

    private final int id;
    private int currentFloor;
    private int direction;
    private List<Integer> floorPickupsUp;
    private List<Integer> floorPickupsDown;
    private Integer target;

    public ElevatorModel(@Value("${elevator.id}") int id) {
        this.id = id;
        this.currentFloor = 0;
        this.direction = 0;
        this.floorPickupsUp = new ArrayList<>();
        this.floorPickupsDown = new ArrayList<>();
        this.target = null;
    }

    public String status() {
        String status = (("ID: " + id + ",  floor: " + currentFloor + ",  direction: " + direction +
                ",  target_floor: " + target + ",  targets_up: " + floorPickupsUp + ",  targets_down: " + floorPickupsDown));
        System.out.println(status);
        return status;


    }

    public void openDoors() {
        System.out.println("##### " + id + " opening doors at " + currentFloor);
    }

    public void appendFloor(int floor, int direction) {
        if (direction == 1 && !floorPickupsUp.contains(floor)) {
            floorPickupsUp.add(floor);
        }
        if (direction == -1 && !floorPickupsDown.contains(floor)) {
            floorPickupsDown.add(floor);
        }
    }

    public void pickupRequest(int floor, int direction) {
        if (this.direction == 0) {
            if (floor > currentFloor) {
                this.direction = 1;
                appendFloor(floor, direction);
                this.target = floor;
            } else if (floor < currentFloor) {
                this.direction = -1;
                appendFloor(floor, direction);
                this.target = floor;
            }
        } else {
            if (direction == 1 && !floorPickupsUp.contains(floor)) {
                appendFloor(floor, direction);
            } else if (direction == -1 && !floorPickupsDown.contains(floor)) {
                appendFloor(floor, direction);
            }
        }
    }

    public Integer getNearestFloor() {
        List<Integer> sortedPickupsUp = new ArrayList<>(floorPickupsUp);
        List<Integer> sortedPickupsDown = new ArrayList<>(floorPickupsDown);
        Collections.sort(sortedPickupsUp);
        Collections.sort(sortedPickupsDown, Collections.reverseOrder());

        if (direction == 1) {
            if (!sortedPickupsUp.isEmpty()) {
                List<Integer> greaterFloors = new ArrayList<>();
                for (Integer x : sortedPickupsUp) {
                    if (x > currentFloor) {
                        greaterFloors.add(x);
                    }
                }
                if (!greaterFloors.isEmpty()) {
                    return greaterFloors.get(0);
                } else if (!sortedPickupsUp.isEmpty()) {
                    direction = -1;
                    return sortedPickupsUp.get(sortedPickupsUp.size() - 1);
                }
            } else if (!sortedPickupsDown.isEmpty()) {
                List<Integer> lesserFloors = new ArrayList<>();
                for (Integer x : sortedPickupsDown) {
                    if (x < currentFloor) {
                        lesserFloors.add(x);
                    }
                }
                if (!lesserFloors.isEmpty()) {
                    direction = -1;
                    return lesserFloors.get(0);
                } else if (!sortedPickupsDown.isEmpty()) {
                    direction = 1;
                    return sortedPickupsDown.get(sortedPickupsDown.size() - 1);
                }
            }
        } else if (direction == -1) {
            if (!sortedPickupsDown.isEmpty()) {
                List<Integer> lesserFloors = new ArrayList<>();
                for (Integer x : sortedPickupsDown) {
                    if (x < currentFloor) {
                        lesserFloors.add(x);
                    }
                }
                if (!lesserFloors.isEmpty()) {
                    return lesserFloors.get(0);
                }
            } else if (!sortedPickupsUp.isEmpty()) {
                List<Integer> greaterFloors = new ArrayList<>();
                for (Integer x : sortedPickupsUp) {
                    if (x > currentFloor) {
                        greaterFloors.add(x);
                    }
                }
                if (!greaterFloors.isEmpty()) {
                    direction = 1;
                    return greaterFloors.get(0);
                } else if (!sortedPickupsUp.isEmpty()) {
                    direction = -1;
                    return sortedPickupsUp.get(sortedPickupsUp.size() - 1);
                }
            }
        }
        return null;
    }

    public void move() {
        if (target == null) {
            direction = 0;
        }

        if (direction == 0) {
            if (!floorPickupsUp.isEmpty() || !floorPickupsDown.isEmpty()) {
                target = getNearestFloor();
            }
        }

        if (direction == 1) {
            currentFloor++;
            if (floorPickupsUp.contains(currentFloor)) {
                openDoors();
                floorPickupsUp.remove(Integer.valueOf(currentFloor));
                if (floorPickupsDown.contains(currentFloor)) {
                    floorPickupsDown.remove(Integer.valueOf(currentFloor));
                }
                target = getNearestFloor();
            } else if (floorPickupsDown.contains(currentFloor)) {
                openDoors();
                floorPickupsDown.remove(Integer.valueOf(currentFloor));
                if (floorPickupsUp.contains(currentFloor)) {
                    floorPickupsUp.remove(Integer.valueOf(currentFloor));
                }
                target = getNearestFloor();
            }
        }

        if (direction == -1) {
            currentFloor--;
            if (floorPickupsDown.contains(currentFloor)) {
                openDoors();
                floorPickupsDown.remove(Integer.valueOf(currentFloor));
                if (floorPickupsUp.contains(currentFloor)) {
                    floorPickupsUp.remove(Integer.valueOf(currentFloor));
                }
                target = getNearestFloor();
            } else if (floorPickupsUp.contains(currentFloor)) {
                openDoors();
                floorPickupsUp.remove(Integer.valueOf(currentFloor));
                if (floorPickupsDown.contains(currentFloor)) {
                    floorPickupsDown.remove(Integer.valueOf(currentFloor));
                }
                target = getNearestFloor();
            }
        }
    }
}

