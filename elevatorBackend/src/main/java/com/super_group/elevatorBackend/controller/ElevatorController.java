package com.super_group.elevatorBackend.controller;

import com.super_group.elevatorBackend.service.ElevatorSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ElevatorController {

    private final ElevatorSystemService elevatorSystemService;
    private static final int MAX_FLOORS = 21;


    @Autowired
    public ElevatorController(ElevatorSystemService elevatorSystemService) {
        this.elevatorSystemService = elevatorSystemService;
    }

    @PostMapping("/{elevatorId}/pickup")
    public void pickupRequest(
            @PathVariable int elevatorId,
            @RequestParam int floor,
            @RequestParam int direction
    ) throws Exception {
        if (floor > MAX_FLOORS) {
            throw new Exception("Max floor is 15");
        }

        elevatorSystemService.pickup(elevatorId, floor, direction);
    }

    @PostMapping("/{elevatorId}/step")
    public void step(@PathVariable int elevatorId) {
        elevatorSystemService.step(elevatorId);
    }

    @GetMapping("/{elevatorId}/status")
    public String getStatus(@PathVariable int elevatorId) {
        return elevatorSystemService.status(elevatorId);
    }

    @PostMapping("/add")
    public void addElevator(@RequestParam int elevatorId) {
        elevatorSystemService.addElevator(elevatorId);
    }

    @GetMapping("/elevatorIds")
    public List<Integer> getElevatorIds() {
        return elevatorSystemService.getElevatorIds();
    }
}


