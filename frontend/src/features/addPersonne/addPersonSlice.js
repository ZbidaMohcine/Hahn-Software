import { createSlice } from '@reduxjs/toolkit';
import { addPerson, fetchPersons, deletePerson, updatePerson } from './personApiSlice';

const personSlice = createSlice({
  name: 'person',
  initialState: {
    persons: [],
    loading: false,
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(addPerson.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(addPerson.fulfilled, (state, action) => {
        state.loading = false;
        state.persons.push(action.payload);
      })
      .addCase(addPerson.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(fetchPersons.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchPersons.fulfilled, (state, action) => {
        state.loading = false;
        state.persons = action.payload;
      })
      .addCase(fetchPersons.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(deletePerson.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(deletePerson.fulfilled, (state, action) => {
        state.loading = false;
        state.persons = state.persons.filter(p => p.id !== action.payload);
      })
      .addCase(deletePerson.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(updatePerson.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(updatePerson.fulfilled, (state, action) => {
        state.loading = false;
        const idx = state.persons.findIndex(p => p.id === action.payload.id);
        if (idx !== -1) {
          state.persons[idx] = action.payload;
        }
      })
      .addCase(updatePerson.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  },
});

export default personSlice.reducer;
export { addPerson, fetchPersons, deletePerson, updatePerson }; 