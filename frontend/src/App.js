import './App.css';
import AddPerson from './features/addPersonne/AddPerson';
import PersonneList from './features/listPersonne/PersonneList';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import UpdatePerson from './features/updatePersone/UpdatePerson';
import { useSelector } from 'react-redux';
import Navbar from './components/Navbar';

function App() {
  const notification = useSelector((state) => state.notification);

  return (
    <Router>
      <div className="App">
        {notification.message && (
          <div style={{
            background: notification.type === 'success' ? '#4caf50' : '#f44336',
            color: 'white',
            padding: '12px',
            position: 'fixed',
            top: 0,
            left: 0,
            right: 0,
            zIndex: 1000,
            textAlign: 'center',
          }}>
            {notification.message}
          </div>
        )}
        <Navbar />
        <Routes>
          <Route path="/" element={<PersonneList />} />
          <Route path="/add" element={<AddPerson />} />
          <Route path="/update/:id" element={<UpdatePerson />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
