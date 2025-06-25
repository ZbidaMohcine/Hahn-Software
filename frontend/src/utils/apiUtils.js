import store from '../app/store';

// Get the current token from Redux store
const getAuthToken = () => {
  const state = store.getState();
  return state.auth.token;
};

// Create headers with authentication token
export const createAuthHeaders = (additionalHeaders = {}) => {
  const token = getAuthToken();
  return {
    'Content-Type': 'application/json',
    ...(token && { 'Authorization': `Bearer ${token}` }),
    ...additionalHeaders
  };
};

// Make authenticated API call
export const authenticatedFetch = async (url, options = {}) => {
  const headers = createAuthHeaders(options.headers);
  
  const response = await fetch(url, {
    ...options,
    headers
  });

  if (response.status === 401) {
    // Token might be expired, redirect to login
    store.dispatch({ type: 'auth/logout' });
    window.location.href = '/signin';
    throw new Error('Authentication required');
  }

  return response;
}; 