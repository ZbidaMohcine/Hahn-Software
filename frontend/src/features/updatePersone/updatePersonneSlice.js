import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

// Async thunk for deleting a personne
export const deletePersonne = createAsyncThunk(
  'updatePersonne/deletePersonne',
  async (id, { rejectWithValue }) => {
    try {
      const response = await fetch(`http://localhost:8089/api/v1/personnes/delete/${id}`, {
        method: 'DELETE',
      });
      if (!response.ok) {
        throw new Error('Failed to delete personne');
      }
      return id;
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);

// Async thunk for updating a personne
export const updatePersonne = createAsyncThunk(
  'updatePersonne/updatePersonne',
  async ({ id, data }, { rejectWithValue }) => {
    try {
      const response = await fetch(`http://localhost:8089/api/v1/personnes/update/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      });
      if (!response.ok) {
        throw new Error('Failed to update personne');
      }
      return await response.json();
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);

const updatePersonneSlice = createSlice({
  name: 'updatePersonne',
  initialState: {
    loading: false,
    error: null,
    success: false,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      // Delete
      .addCase(deletePersonne.pending, (state) => {
        state.loading = true;
        state.error = null;
        state.success = false;
      })
      .addCase(deletePersonne.fulfilled, (state) => {
        state.loading = false;
        state.success = true;
      })
      .addCase(deletePersonne.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
        state.success = false;
      })
      // Update
      .addCase(updatePersonne.pending, (state) => {
        state.loading = true;
        state.error = null;
        state.success = false;
      })
      .addCase(updatePersonne.fulfilled, (state) => {
        state.loading = false;
        state.success = true;
      })
      .addCase(updatePersonne.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
        state.success = false;
      });
  },
});

export default updatePersonneSlice.reducer; 