import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { addPerson } from './addPersonSlice';
import { setNotification, clearNotification } from '../../app/notificationSlice';
import { useNavigate } from 'react-router-dom';

function AddPerson() {
  const [form, setForm] = useState({ nom: '', prenom: '', email: '', age: '' });
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { loading, error } = useSelector((state) => state.person);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (form.nom && form.prenom && form.email && form.age) {
      dispatch(addPerson({ ...form, age: Number(form.age) }))
        .unwrap()
        .then(() => {
          dispatch(setNotification({ message: 'Personne saved successfully!', type: 'success' }));
          setTimeout(() => {
            dispatch(clearNotification());
            navigate('/');
          }, 1500);
        });
      setForm({ nom: '', prenom: '', email: '', age: '' });
    }
  };

  return (
    <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '8px', maxWidth: 300 }}>
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
      <button type="submit" disabled={loading}>{loading ? 'Adding...' : 'Add Person'}</button>
      {error && <div style={{ color: 'red' }}>{error}</div>}
    </form>
  );
}

export default AddPerson; 