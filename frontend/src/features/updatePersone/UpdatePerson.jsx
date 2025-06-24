import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useParams, useNavigate } from 'react-router-dom';
import { updatePerson } from '../addPersonne/addPersonSlice';
import { setNotification, clearNotification } from '../../app/notificationSlice';

function UpdatePerson() {
  const { id } = useParams();
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { persons, loading, error } = useSelector((state) => state.person);
  const person = persons.find((p) => String(p.id) === String(id));

  const [form, setForm] = useState({ nom: '', prenom: '', email: '', age: '' });

  useEffect(() => {
    if (person) {
      setForm({
        nom: person.nom || '',
        prenom: person.prenom || '',
        email: person.email || '',
        age: person.age || '',
      });
    }
  }, [person]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(updatePerson({ id, personData: { ...form, age: Number(form.age) } }))
      .unwrap()
      .then(() => {
        dispatch(setNotification({ message: 'Personne updated successfully!', type: 'success' }));
        setTimeout(() => {
          dispatch(clearNotification());
          navigate('/');
        }, 1500);
      });
  };

  if (!person) {
    return <div>Person not found.</div>;
  }

  return (
    <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '8px', maxWidth: 300 }}>
      <h2>Update Person</h2>
      <input
        type="text"
        name="nom"
        placeholder="Nom"
        value={form.nom}
        onChange={handleChange}
        required
      />
      <input
        type="text"
        name="prenom"
        placeholder="Prénom"
        value={form.prenom}
        onChange={handleChange}
        required
      />
      <input
        type="email"
        name="email"
        placeholder="Email"
        value={form.email}
        onChange={handleChange}
        required
      />
      <input
        type="number"
        name="age"
        placeholder="Age"
        value={form.age}
        onChange={handleChange}
        required
        min="0"
      />
      <button type="submit" disabled={loading}>{loading ? 'Updating...' : 'Update Person'}</button>
      {error && <div style={{ color: 'red' }}>{error}</div>}
    </form>
  );
}

export default UpdatePerson; 