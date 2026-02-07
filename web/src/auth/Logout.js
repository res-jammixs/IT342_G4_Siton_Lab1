import React from 'react';
import { useEffect } from 'react';

export default function Logout() {
  useEffect(() => {
    // Placeholder: clear auth tokens, redirect, etc.
    // e.g. localStorage.removeItem('authToken');
    // window.location.href = '/login';
  }, []);

  return (
    <div className="page logout-page">
      <h1>Logging out...</h1>
    </div>
  );
}
