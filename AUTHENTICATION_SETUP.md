# Authentication Setup Guide

This guide explains how to set up authentication with Keycloak for your Personne Management application.

## Prerequisites

- Docker and Docker Compose installed
- Node.js and npm (for frontend development)

## Quick Start

1. **Start all services (Keycloak will be automatically initialized):**
   ```bash
   docker-compose up --build
   ```

2. **Access the application:**
   - Frontend: http://localhost:3000
   - Backend API: http://localhost:8089
   - Keycloak Admin: http://localhost:8080

## Default Credentials

### Keycloak Admin
- Username: `admin`
- Password: `admin`

### Test Users
- Username: `user`
- Password: `user123`

- Username: `admin`
- Password: `admin123`

## How It Works

### Frontend Authentication Flow
1. User visits any protected route
2. If not authenticated, redirected to `/signin`
3. User enters credentials
4. On successful login, redirected to home page
5. User can now access all protected routes

### Protected Routes
- `/` - Personne List (requires authentication)
- `/add` - Add Person (requires authentication)
- `/update/:id` - Update Person (requires authentication)

### Public Routes
- `/signin` - Sign In page

## Keycloak Configuration

### Automatic Initialization
Keycloak is automatically initialized with the `personne-realm` configuration when the container starts. The realm configuration is located in `keycloak-config/personne-realm.json`.

### Realm: `personne-realm`
- Contains users, roles, and client configurations

### Clients
1. **personne-frontend** (Public Client)
   - Used by the React frontend
   - Redirect URI: `http://localhost:3000/*`
   - Web Origin: `http://localhost:3000`

2. **personne-backend** (Confidential Client)
   - Used by the Spring Boot backend
   - Service account enabled for backend-to-backend communication

### Roles
- `user` - Regular user role
- `admin` - Administrator role

## Development

### Adding New Users
1. Access Keycloak Admin Console: http://localhost:8080
2. Login with admin credentials
3. Select `personne-realm`
4. Go to Users → Add User
5. Set username, email, and password
6. Assign roles in the Role Mappings tab

### Customizing Authentication
- Modify `frontend/src/features/auth/SignIn.jsx` for custom login UI
- Update `frontend/src/features/auth/authSlice.js` for authentication logic
- Modify `backend/src/main/java/com/org/backend/config/SecurityConfig.java` for backend security

### Modifying Realm Configuration
- Edit `keycloak-config/personne-realm.json`
- Restart Keycloak container: `docker-compose restart keycloak`

## Troubleshooting

### Keycloak Not Starting
- Check if port 8080 is available
- Verify PostgreSQL is running
- Check Docker logs: `docker-compose logs keycloak`

### Authentication Issues
- Verify realm configuration is imported (check Keycloak logs)
- Check client redirect URIs match your frontend URL
- Ensure backend JWT configuration matches Keycloak realm

### Frontend Build Issues
- Install dependencies: `cd frontend && npm install`
- Check for missing packages: `npm install keycloak-js`

### Realm Not Imported
- Check if `keycloak-config/personne-realm.json` exists
- Verify file permissions
- Restart Keycloak: `docker-compose restart keycloak`

## Security Notes

- Change default passwords in production
- Use HTTPS in production
- Configure proper CORS settings
- Set up proper client secrets for backend
- Enable audit logging in Keycloak

# JWT Authentication Setup

## Overview
This application uses JWT (JSON Web Token) authentication for securing API endpoints. The authentication flow works as follows:

## Frontend Authentication Flow

1. **Login Process**:
   - User enters credentials in the SignIn component
   - Frontend calls `/api/v1/auth/login` endpoint
   - Backend returns a JWT token along with user information
   - Token is stored in Redux state (`state.auth.token`)

2. **Token Usage**:
   - All subsequent API calls use the `authenticatedFetch` utility
   - This utility automatically includes the JWT token in the `Authorization` header
   - Format: `Authorization: Bearer <token>`

3. **Token Storage**:
   - Token is stored in Redux state
   - Accessible via `state.auth.token`
   - Automatically included in all authenticated API calls

## Backend Authentication

### Development Mode (Current Setup)
- Uses mock JWT tokens for development
- Valid tokens: `mock-jwt-token-for-user`, `mock-jwt-token-for-admin`
- Configured via `DevelopmentJwtConfig` and `MockJwtAuthenticationFilter`
- Profile: `dev`

### Production Mode
- Uses Keycloak for JWT validation
- Configured via `SecurityConfig`
- Profile: `!dev` (any profile except dev)

## API Endpoints

### Public Endpoints (No Authentication Required)
- `POST /api/v1/auth/login` - User login
- `GET /api/v1/auth/health` - Health check

### Protected Endpoints (Authentication Required)
- `GET /api/v1/personnes/findAll` - Get all persons
- `POST /api/v1/personnes/save` - Create new person
- `GET /api/v1/personnes/{id}` - Get person by ID
- `PUT /api/v1/personnes/{id}` - Update person
- `DELETE /api/v1/personnes/{id}` - Delete person
- All other personne endpoints

## How to Use

1. **Login**: Use the SignIn component with credentials:
   - Username: `user`, Password: `user123` (user role)
   - Username: `admin`, Password: `admin123` (admin role)

2. **API Calls**: All API calls automatically include the JWT token when using the `authenticatedFetch` utility

3. **Token Expiration**: If a 401 response is received, the user is automatically logged out and redirected to the login page

## Files Modified

### Frontend
- `frontend/src/utils/apiUtils.js` - Authentication utility functions
- `frontend/src/features/auth/authApi.js` - Updated to use authenticated fetch
- `frontend/src/features/addPersonne/personApiSlice.js` - Updated to use authenticated fetch
- `frontend/src/features/updatePersone/updatePersonneSlice.js` - Updated to use authenticated fetch
- `frontend/src/features/listPersonne/personnelistSlice.js` - Updated to use authenticated fetch

### Backend
- `backend/src/main/java/com/org/backend/config/DevelopmentJwtConfig.java` - Development JWT configuration
- `backend/src/main/java/com/org/backend/config/filter/MockJwtAuthenticationFilter.java` - Mock JWT filter
- `backend/src/main/java/com/org/backend/config/SecurityConfig.java` - Updated with profile annotation
- `backend/src/main/resources/application.properties` - Added development profile

## Testing

To test the authentication:

1. Start the backend with development profile
2. Login with valid credentials
3. Try accessing protected endpoints - they should work with the JWT token
4. Try accessing protected endpoints without login - should return 401
5. Try accessing protected endpoints with invalid token - should return 401 