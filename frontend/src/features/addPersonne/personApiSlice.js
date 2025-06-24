import { createAsyncThunk } from '@reduxjs/toolkit';

export const addPerson = createAsyncThunk(
  'person/addPerson',
  async (personData, { rejectWithValue }) => {
    try {
      const response = await fetch('http://localhost:8089/api/v1/personnes/save', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(personData),
      });
      if (!response.ok) {
        throw new Error('Failed to add person');
      }
      return await response.json();
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);

export const fetchPersons = createAsyncThunk(
  'person/fetchPersons',
  async (_, { rejectWithValue }) => {
    try {
      const response = await fetch('http://localhost:8089/api/v1/personnes/findAll');
      if (!response.ok) {
        throw new Error('Failed to fetch persons');
      }
      return await response.json();
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);

export const deletePerson = createAsyncThunk(
  'person/deletePerson',
  async (id, { rejectWithValue }) => {
    try {
      const response = await fetch(`http://localhost:8089/api/v1/personnes/${id}`, {
        method: 'DELETE',
      });
      if (!response.ok) {
        throw new Error('Failed to delete person');
      }
      return id;
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);

export const updatePerson = createAsyncThunk(
  'person/updatePerson',
  async ({ id, personData }, { rejectWithValue }) => {
    try {
      const response = await fetch(`http://localhost:8089/api/v1/personnes/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(personData),
      });
      if (!response.ok) {
        throw new Error('Failed to update person');
      }
      return await response.json();
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
); 