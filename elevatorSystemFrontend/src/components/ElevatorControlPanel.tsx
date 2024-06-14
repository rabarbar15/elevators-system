// src/components/ElevatorControlPanel.tsx

import React, { useState } from 'react';

interface Elevator {
    id: number;
    currentFloor: number;
    direction: number;
}

interface Props {
    elevator: Elevator;
    onCallUp: (elevatorId: number, floor: number) => void;
    onCallDown: (elevatorId: number, floor: number) => void;
}

const ElevatorControlPanel: React.FC<Props> = ({ elevator, onCallUp, onCallDown }) => {
    const [floor, setFloor] = useState('');

    const handleCallUp = () => {
        if (floor !== '') {
            onCallUp(elevator.id, parseInt(floor));
            setFloor('');
        }
    };

    const handleCallDown = () => {
        if (floor !== '') {
            onCallDown(elevator.id, parseInt(floor));
            setFloor('');
        }
    };

    return (
        <div className="elevator-control-panel">
            <h2>Elevator {elevator.id}</h2>
            <p>Status: Floor {elevator.currentFloor}, Direction: {elevator.direction === 1 ? 'Up' : (elevator.direction === -1 ? 'Down' : 'Stationary')}</p>
            <div>
                <input type="number" value={floor} onChange={e => setFloor(e.target.value)} placeholder="Floor number" />
                <button onClick={handleCallUp}>Call Up</button>
                <button onClick={handleCallDown}>Call Down</button>
            </div>
        </div>
    );
};

export default ElevatorControlPanel;
