import MainPage from './components/MainPage';
import { Route, Routes } from 'react-router-dom';
import ElevatorPage from './components/ElevatorPage';


const App = () => {

    return (
      <>
        <Routes>
          <Route path='/' Component={MainPage} />
          <Route path='/elevatorPage/:id' Component={ElevatorPage} />
        </Routes>
      </>
    );
};

export default App;
