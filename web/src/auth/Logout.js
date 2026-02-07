import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { apiFetch } from '../api';
import { clearToken, getToken } from './auth';

export default function Logout() {
  const navigate = useNavigate();

  useEffect(() => {
    async function handleLogout() {
      try {
        const token = getToken();
        if (token) {
          await apiFetch('/api/auth/logout', { method: 'POST', token });
        }
      } catch (err) {
        // Ignore backend logout errors.
      } finally {
        clearToken();
        navigate('/login');
      }
    }

    handleLogout();
  }, [navigate]);

  return (
    <div className="rounded-3xl bg-white/90 p-10 text-center shadow-xl shadow-blue-500/20">
      <h1 className="text-2xl font-bold text-blue-950">Signing out...</h1>
      <p className="mt-3 text-sm text-blue-700">We are closing your session.</p>
    </div>
  );
}
