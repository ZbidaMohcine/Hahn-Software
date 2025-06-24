import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { addPerson } from './addPersonSlice';
import { setNotification, clearNotification } from '../../app/notificationSlice';
import { useNavigate } from 'react-router-dom';
import { Box, TextField, Button, Alert, Typography, Paper } from '@mui/material';

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
        Add New Person
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
          {loading ? 'Adding...' : 'Add Person'}
        </Button>
        {error && <Alert severity="error">{error}</Alert>}
      </form>
    </Box>
  );
}

export default AddPerson; 