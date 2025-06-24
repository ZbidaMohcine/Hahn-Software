import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

export const fetchPersonnelist = createAsyncThunk(
  'personnelist/fetchPersonnelist',
  async (_, { rejectWithValue }) => {
    try {
      const response = await fetch('http://localhost:8089/api/v1/personnes/findAll');
      if (!response.ok) {
        throw new Error('Failed to fetch personnelist');
      }
      return await response.json();
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);

const personnelistSlice = createSlice({
  name: 'personnelist',
  initialState: {
    list: [],
    loading: false,
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchPersonnelist.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchPersonnelist.fulfilled, (state, action) => {
        state.loading = false;
        state.list = action.payload;
      })
      .addCase(fetchPersonnelist.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  },
});

export default personnelistSlice.reducer; 