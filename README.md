# IT342_G4_Siton_Lab1

This repository contains a Spring Boot backend and a React frontend for the IT342_G4_Siton_Lab1 project.

## Project structure

- `backend/` - Java Spring Boot application (Maven)
- `web/` - React frontend application (Create React App)

## Prerequisites

- Node.js (v18+ recommended) and npm
- Java JDK 17+ and Maven

## Frontend setup (web)

1. Open a terminal and change into the `web` folder:

```bash
cd web
```

2. Install dependencies:

```bash
npm install
```

3. Install router dependency used by the app:

```bash
npm install react-router-dom
```

4. Start the frontend dev server:

```bash
npm start
```

The app will open at `http://localhost:3000` by default.

## Backend setup (backend)

1. Change into the `backend` folder:

```bash
cd backend
```

2. Build and run with Maven:

```bash
./mvnw spring-boot:run    # Linux/macOS
mvnw.cmd spring-boot:run  # Windows
```

The backend will default to `http://localhost:8080` unless otherwise configured in `src/main/resources/application.properties`.

## Notes

- The frontend routes for authentication and dashboard are wired in `web/src/App.js` and components are in `web/src/auth` and `web/src/dashboard`.
- To protect routes or add real authentication, implement token handling and route guards (not included in the scaffold).

If you want, I can add a `Nav` component, protected routes, or example API calls next.