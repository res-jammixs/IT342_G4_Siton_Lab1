import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from 'react-router-dom';
import { Register, Login, Logout } from './auth';
import { Dashboard, Profile } from './dashboard';
import { isAuthenticated } from './auth/auth';

function ProtectedRoute({ children }) {
  if (!isAuthenticated()) {
    return <Navigate to="/login" replace />;
  }
  return children;
}

function AuthOnlyRoute({ children }) {
  if (isAuthenticated()) {
    return <Navigate to="/dashboard" replace />;
  }
  return children;
}

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-gradient-to-br from-blue-50 via-sky-100 to-blue-200 text-slate-900">
        <header className="mx-auto flex w-full max-w-6xl items-center justify-between px-6 py-6">
          <div className="flex items-center gap-3">
            <div className="h-10 w-10 rounded-2xl bg-blue-600 shadow-glow" />
            <div>
              <p className="text-xs font-semibold uppercase tracking-[0.35em] text-blue-600">It342</p>
              <p className="text-lg font-extrabold text-blue-950">Secure Portal</p>
            </div>
          </div>
          <nav className="flex flex-wrap items-center gap-3 text-sm font-semibold text-blue-900">
            <Link className="rounded-full px-4 py-2 transition hover:bg-blue-100" to="/login">Login</Link>
            <Link className="rounded-full px-4 py-2 transition hover:bg-blue-100" to="/register">Register</Link>
            <Link className="rounded-full px-4 py-2 transition hover:bg-blue-100" to="/dashboard">Dashboard</Link>
            <Link className="rounded-full px-4 py-2 transition hover:bg-blue-100" to="/profile">Profile</Link>
            <Link className="rounded-full bg-blue-600 px-4 py-2 text-white shadow-lg shadow-blue-500/40 transition hover:bg-blue-700" to="/logout">Logout</Link>
          </nav>
        </header>
        <main className="mx-auto w-full max-w-6xl px-6 pb-16">
          <Routes>
            <Route path="/" element={<Navigate to={isAuthenticated() ? "/dashboard" : "/login"} replace />} />
            <Route path="/login" element={<AuthOnlyRoute><Login /></AuthOnlyRoute>} />
            <Route path="/register" element={<AuthOnlyRoute><Register /></AuthOnlyRoute>} />
            <Route path="/logout" element={<Logout />} />
            <Route path="/dashboard" element={<ProtectedRoute><Dashboard /></ProtectedRoute>} />
            <Route path="/profile" element={<ProtectedRoute><Profile /></ProtectedRoute>} />
            <Route path="*" element={<Navigate to="/" replace />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
