import React, { useState } from 'react';
import axios from 'axios';
import { Button, Container, Form } from 'react-bootstrap';
import { useParams } from 'react-router-dom';

const ElevatorPage: React.FC = () => {
    const [floor, setFloor] = useState<number>(0);
    const [direction, setDirection] = useState<string>('Up');
    const [status, setStatus] = useState<string[]>([])
    const [pickup, setPickup] = useState<string>('')


    const {id} = useParams()

    const changeDirection = () => {
        direction == 'Up' ? setDirection('Down') : setDirection('Up')
    }
    const handleFloorChange = (event: React.ChangeEvent<{ value: unknown }>) => {
        const newFloor = event.target.value as number;
        setFloor(newFloor);
        console.log(newFloor); 
    };

    const handleMakingStep = async () => {

        if (status.length > 15) {
            setStatus([])
        }

        await axios.post(`http://localhost:8080/api/${id}/step`)
            .then(response => console.log(response.data))
            .catch(error => console.error('Error while making a step:', error));

        await axios.get(`http://localhost:8080/api/${id}/status`)
            .then(response => setStatus(prevStatus => [...prevStatus, response.data]))
            .catch(error => console.error('Error while making a step:', error));
    }


    const handlePickupRequest = (event: React.MouseEvent<HTMLButtonElement, MouseEvent>)=> {
        event.preventDefault();

        let dir = direction == 'Up' ? 1 : -1

        console.log(dir);
        

        axios.post(`http://localhost:8080/api/${id}/pickup?floor=${floor}&direction=${dir}`)
            .then(response => {
                console.log('Pickup request successful:', response.data);
                setPickup(`Ok, elevators going to floor ${floor}`)

            })
            .catch(error => {
                console.error('Error making pickup request:', error);
            });
    };

    return (
        <div className="app">
        <h1>#{id} Elevator interface</h1>
        <div className="appInterface">
            <Container className="elevator-form pl-4 text-center">
                <h2>Pickup</h2>
                <div className="direction">
                    <h3>I want to go: </h3>
                    <h3 style={{ color: "green"}}>{direction}</h3>
                    <Button style={{ fontSize: '0.9rem' }} onClick={changeDirection}>Change</Button>
                </div>
                <br />
                <div className="floor">
                    <h3>Floor: </h3>
                    <Form.Select className='select' value={floor} onChange={handleFloorChange}>
                    {[...Array(21).keys()].map(floor => (
                            <option key={floor} value={floor}>
                                {floor === 0 ? 'Ground floor' : `Floor ${floor}`}
                            </option>
                        ))}
                    </Form.Select>
                </div>
                <Button onClick={handlePickupRequest} style={{ marginTop: '2rem' }}>Pickup</Button>
                <div className="status">
                        <h3>{pickup}</h3>
                </div>
            </Container>

            <Container className="elevator-form pl-4 text-center box-right">
                <div className="steps">
                    <h2>Step</h2>
                    <Button onClick={handleMakingStep}>Make a step</Button>
                    <div className="status">
                        {status.map((status, index) => (
                                <p key={index}>{status}</p>
                            ))}
                    </div>
                </div>
            </Container>
            </div>
        </div>
    );
};

export default ElevatorPage;
