

import React, { useState, useEffect } from 'react';
import axios from 'axios'; 
import { Button, Container } from 'react-bootstrap';
import { Link } from 'react-router-dom';



const MainPage = () => {
    const [elevators, setElevators] = useState<number[]>([]);
    const MAX_LENGTH = 15


    useEffect(() => {

        axios.get('http://localhost:8080/api/elevatorIds')
            .then(response => setElevators(response.data))
            .catch(error => console.error('Error fetching elevators:', error));
    });

    const addNewElevator = (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
      event.preventDefault()
      const newId = elevators.length
      console.log(newId);
      
      
      if (newId > MAX_LENGTH ){
        console.error('I cant add any more elevators');
        alert('I cant add any more elevators')
        return
      }

      axios.post(`http://localhost:8080/api/add?elevatorId=${newId}`)
          .then(response => setElevators(response.data))
          .catch(error => console.error('Error adding an elevator:', error));
    }


    const renderElevators = () => {
        const buttons = [];
        for (let i = 0; i < elevators.length; i++) {
            buttons.push(
                <Button  key={elevators[i]}><Link to={`/elevatorPage/${elevators[i]}`}>Go to elevator {elevators[i]}</Link></Button>
            );
        }
        return buttons;
    };

    return (
      <div className="app">
        <Container className='pl-4 w-100 text-center'>
          <header>
            <h1>Elevator Control System</h1>
            <Button onClick={addNewElevator}>Add new elevator</Button>
          </header>

          {elevators.length !== 1 ? (
              <h3>Hi! There are {elevators.length} elevators available.</h3>
          ) : (
              <h3>Hi! There is {elevators.length} elevator available.</h3>
          )}

          <div className="buttons">
            {renderElevators()}
          </div>
        </Container>
      </div>
    );
};

export default MainPage;
