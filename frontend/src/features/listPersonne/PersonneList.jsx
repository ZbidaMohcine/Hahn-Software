import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchPersons, deletePerson } from '../addPersonne/addPersonSlice';
import { useNavigate } from 'react-router-dom';

function PersonneList() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { persons, loading, error } = useSelector((state) => state.person);

  useEffect(() => {
    dispatch(fetchPersons());
  }, [dispatch]);

  const handleDelete = (id) => {
    if (window.confirm('Are you sure you want to delete this person?')) {
      dispatch(deletePerson(id));
    }
  };

  const handleUpdate = (id) => {
    navigate(`/update/${id}`);
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div>
      <h2>Person List</h2>
      <table style={{ width: '100%', borderCollapse: 'collapse' }}>
        <thead>
          <tr>
            <th style={{ border: '1px solid #ccc', padding: '8px' }}>Nom</th>
            <th style={{ border: '1px solid #ccc', padding: '8px' }}>Prénom</th>
            <th style={{ border: '1px solid #ccc', padding: '8px' }}>Email</th>
            <th style={{ border: '1px solid #ccc', padding: '8px' }}>Age</th>
            <th style={{ border: '1px solid #ccc', padding: '8px' }}>Delete</th>
            <th style={{ border: '1px solid #ccc', padding: '8px' }}>Update</th>
          </tr>
        </thead>
        <tbody>
          {persons.map((person) => (
            <tr key={person.id}>
              <td style={{ border: '1px solid #ccc', padding: '8px' }}>{person.nom}</td>
              <td style={{ border: '1px solid #ccc', padding: '8px' }}>{person.prenom}</td>
              <td style={{ border: '1px solid #ccc', padding: '8px' }}>{person.email}</td>
              <td style={{ border: '1px solid #ccc', padding: '8px' }}>{person.age}</td>
              <td style={{ border: '1px solid #ccc', padding: '8px' }}>
                <button onClick={() => handleDelete(person.id)} style={{ color: 'white', background: 'red', border: 'none', padding: '6px 12px', borderRadius: '4px' }}>Delete</button>
              </td>
              <td style={{ border: '1px solid #ccc', padding: '8px' }}>
                <button onClick={() => handleUpdate(person.id)} style={{ color: 'white', background: 'orange', border: 'none', padding: '6px 12px', borderRadius: '4px' }}>Update</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default PersonneList; 