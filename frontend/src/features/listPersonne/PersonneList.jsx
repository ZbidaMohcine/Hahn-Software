import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchPersons, deletePerson } from '../addPersonne/addPersonSlice';
import { useNavigate } from 'react-router-dom';
import {
  Table, TableHead, TableRow, TableCell, TableBody, Paper, Button, Typography, Box, CircularProgress, Alert
} from '@mui/material';

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

  if (loading) return <Box display="flex" justifyContent="center" mt={4}><CircularProgress /></Box>;
  if (error) return <Alert severity="error">{error}</Alert>;

  return (
    <Box sx={{ maxWidth: '100%', p: { xs: 1, sm: 3 }, mt: 4 }}>
      <Paper sx={{ p: 2, borderRadius: 2 }}>
        <Typography variant="h5" align="center" gutterBottom>
          Person List
        </Typography>
        <Table>
          <TableHead>
            <TableRow sx={{ backgroundColor: 'primary.main' }}>
              <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>Nom</TableCell>
              <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>Prénom</TableCell>
              <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>Email</TableCell>
              <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>Age</TableCell>
              <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>Delete</TableCell>
              <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>Update</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {persons.map((person) => (
              <TableRow
                key={person.id}
                hover
                sx={{
                  transition: 'background 0.2s',
                  '&:hover': {
                    backgroundColor: 'primary.light',
                    cursor: 'pointer'
                  }
                }}
              >
                <TableCell>{person.nom}</TableCell>
                <TableCell>{person.prenom}</TableCell>
                <TableCell>{person.email}</TableCell>
                <TableCell>{person.age}</TableCell>
                <TableCell>
                  <Button
                    variant="contained"
                    color="error"
                    onClick={() => handleDelete(person.id)}
                  >
                    Delete
                  </Button>
                </TableCell>
                <TableCell>
                  <Button
                    variant="contained"
                    color="warning"
                    onClick={() => handleUpdate(person.id)}
                  >
                    Update
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Paper>
    </Box>
  );
}

export default PersonneList; 