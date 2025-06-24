import { configureStore } from '@reduxjs/toolkit';
import personReducer from '../features/addPersonne/addPersonSlice';
import notificationReducer from './notificationSlice';

const store = configureStore({
  reducer: {
    person: personReducer,
    notification: notificationReducer,
  },
});

export default store; 