import { authenticatedFetch } from '../../utils/apiUtils';

const API_BASE_URL = 'http://localhost:8089/api/v1';

export const authApi = {
  login: async (username, password) => {
    try {
      const response = await fetch(`${API_BASE_URL}/auth/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) {
        throw new Error('Login failed');
      }

      const data = await response.json();
      return data;
    } catch (error) {
      throw new Error(error.message || 'Network error');
    }
  },

  health: async () => {
    try {
      const response = await authenticatedFetch(`${API_BASE_URL}/auth/health`);
      return response.ok;
    } catch (error) {
      return false;
    }
  }
}; 