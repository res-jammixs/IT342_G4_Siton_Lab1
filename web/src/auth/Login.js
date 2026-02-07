import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { apiFetch } from '../api';
import { setToken } from './auth';

export default function Login() {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  async function handleSubmit(event) {
    event.preventDefault();
    setLoading(true);
    setError('');
    try {
      const result = await apiFetch('/api/auth/login', {
        method: 'POST',
        body: { email, password },
      });
      setToken(result.token);
      navigate('/dashboard');
    } catch (err) {
      setError(err.message || 'Login failed');
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="grid gap-10 lg:grid-cols-[1.1fr_0.9fr]">
      <section className="rounded-3xl bg-white/80 p-10 shadow-xl shadow-blue-500/20 backdrop-blur">
        <h1 className="text-3xl font-extrabold text-blue-950">Welcome back</h1>
        <p className="mt-3 text-sm text-blue-700">Sign in to access your dashboard and profile.</p>
        <form onSubmit={handleSubmit} className="mt-8 grid gap-5">
          <div>
            <label className="text-xs font-semibold uppercase tracking-wider text-blue-800">Email</label>
            <input
              type="email"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
              className="mt-2 w-full rounded-2xl border border-blue-100 bg-white px-4 py-3 text-sm shadow-sm outline-none ring-blue-500 focus:ring-2"
              placeholder="you@example.com"
              required
            />
          </div>
          <div>
            <label className="text-xs font-semibold uppercase tracking-wider text-blue-800">Password</label>
            <input
              type="password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
              className="mt-2 w-full rounded-2xl border border-blue-100 bg-white px-4 py-3 text-sm shadow-sm outline-none ring-blue-500 focus:ring-2"
              placeholder="Enter your password"
              required
            />
          </div>
          {error ? (
            <div className="rounded-2xl border border-red-200 bg-red-50 px-4 py-3 text-sm text-red-700">
              {error}
            </div>
          ) : null}
          <button
            type="submit"
            disabled={loading}
            className="mt-2 rounded-2xl bg-blue-600 px-6 py-3 text-sm font-semibold text-white shadow-lg shadow-blue-500/40 transition hover:bg-blue-700 disabled:cursor-not-allowed disabled:opacity-70"
          >
            {loading ? 'Signing in...' : 'Sign in'}
          </button>
        </form>
        <p className="mt-6 text-sm text-blue-800">
          New here?{' '}
          <Link to="/register" className="font-semibold text-blue-600 hover:text-blue-700">Create an account</Link>
        </p>
      </section>
      <aside className="rounded-3xl bg-gradient-to-br from-blue-600 via-blue-700 to-blue-900 p-10 text-white shadow-2xl">
        <p className="text-xs font-semibold uppercase tracking-[0.3em] text-blue-200">Secure Access</p>
        <h2 className="mt-4 text-3xl font-bold">Blue-powered identity</h2>
        <p className="mt-4 text-sm leading-relaxed text-blue-100">
          Keep your access secure with token-based sessions. View your profile instantly after login.
        </p>
        <div className="mt-8 grid gap-4">
          <div className="rounded-2xl bg-white/10 p-4">
            <p className="text-sm font-semibold">Fast sign in</p>
            <p className="mt-1 text-xs text-blue-100">Connect to your backend with one click.</p>
          </div>
          <div className="rounded-2xl bg-white/10 p-4">
            <p className="text-sm font-semibold">Profile ready</p>
            <p className="mt-1 text-xs text-blue-100">See your account data right away.</p>
          </div>
        </div>
      </aside>
    </div>
  );
}
