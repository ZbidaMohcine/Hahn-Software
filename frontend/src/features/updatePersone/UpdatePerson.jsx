import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useParams, useNavigate } from 'react-router-dom';
import { updatePerson } from '../addPersonne/addPersonSlice';
import { setNotification, clearNotification } from '../../app/notificationSlice';
import { Box, TextField, Button, Alert, Typography, Paper } from '@mui/material';

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
    return <Alert severity="error">Person not found.</Alert>;
  }

  return (
    <Box
      component={Paper}
      elevation={3}
      sx={{
        p: { xs: 2, sm: 4 },
        maxWidth: 400,
        width: '90%',
        mx: 'auto',
        mt: { xs: 3, sm: 6 },
        display: 'flex',
        flexDirection: 'column',
        gap: 2,
        borderRadius: 2,
      }}
    >
      <Typography variant="h5" align="center" gutterBottom>
        Update Person
      </Typography>
      <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: 16 }}>
        <TextField
          label="Nom"
          name="nom"
          value={form.nom}
          onChange={handleChange}
          required
        />
        <TextField
          label="Prénom"
          name="prenom"
          value={form.prenom}
          onChange={handleChange}
          required
        />
        <TextField
          label="Email"
          name="email"
          type="email"
          value={form.email}
          onChange={handleChange}
          required
        />
        <TextField
          label="Age"
          name="age"
          type="number"
          value={form.age}
          onChange={handleChange}
          required
          inputProps={{ min: 0 }}
        />
        <Button
          type="submit"
          variant="contained"
          color="primary"
          disabled={loading}
          sx={{ mt: 2 }}
        >
          {loading ? 'Updating...' : 'Update Person'}
        </Button>
        {error && <Alert severity="error">{error}</Alert>}
      </form>
    </Box>
  );
}

export default UpdatePerson; 