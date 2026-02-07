import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { apiFetch } from '../api';

export default function Register() {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    firstName: '',
    middleName: '',
    lastName: '',
    email: '',
    password: '',
    phoneNumber: '',
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  function updateField(event) {
    const { name, value } = event.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  }

  async function handleSubmit(event) {
    event.preventDefault();
    setLoading(true);
    setError('');
    setSuccess('');
    try {
      await apiFetch('/api/auth/register', {
        method: 'POST',
        body: form,
      });
      setSuccess('Account created. You can sign in now.');
      setTimeout(() => navigate('/login'), 900);
    } catch (err) {
      setError(err.message || 'Registration failed');
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="grid gap-10 lg:grid-cols-[1.1fr_0.9fr]">
      <section className="rounded-3xl bg-white/80 p-10 shadow-xl shadow-blue-500/20 backdrop-blur">
        <h1 className="text-3xl font-extrabold text-blue-950">Create your account</h1>
        <p className="mt-3 text-sm text-blue-700">Register to access your dashboard and profile.</p>
        <form onSubmit={handleSubmit} className="mt-8 grid gap-5">
          <div className="grid gap-4 sm:grid-cols-2">
            <div>
              <label className="text-xs font-semibold uppercase tracking-wider text-blue-800">First name</label>
              <input
                name="firstName"
                value={form.firstName}
                onChange={updateField}
                className="mt-2 w-full rounded-2xl border border-blue-100 bg-white px-4 py-3 text-sm shadow-sm outline-none ring-blue-500 focus:ring-2"
                placeholder="Jamie"
                required
              />
            </div>
            <div>
              <label className="text-xs font-semibold uppercase tracking-wider text-blue-800">Middle name</label>
              <input
                name="middleName"
                value={form.middleName}
                onChange={updateField}
                className="mt-2 w-full rounded-2xl border border-blue-100 bg-white px-4 py-3 text-sm shadow-sm outline-none ring-blue-500 focus:ring-2"
                placeholder="Lee"
              />
            </div>
          </div>
          <div>
            <label className="text-xs font-semibold uppercase tracking-wider text-blue-800">Last name</label>
            <input
              name="lastName"
              value={form.lastName}
              onChange={updateField}
              className="mt-2 w-full rounded-2xl border border-blue-100 bg-white px-4 py-3 text-sm shadow-sm outline-none ring-blue-500 focus:ring-2"
              placeholder="Reyes"
              required
            />
          </div>
          <div className="grid gap-4 sm:grid-cols-2">
            <div>
              <label className="text-xs font-semibold uppercase tracking-wider text-blue-800">Email</label>
              <input
                type="email"
                name="email"
                value={form.email}
                onChange={updateField}
                className="mt-2 w-full rounded-2xl border border-blue-100 bg-white px-4 py-3 text-sm shadow-sm outline-none ring-blue-500 focus:ring-2"
                placeholder="you@example.com"
                required
              />
            </div>
            <div>
              <label className="text-xs font-semibold uppercase tracking-wider text-blue-800">Phone number</label>
              <input
                name="phoneNumber"
                value={form.phoneNumber}
                onChange={updateField}
                className="mt-2 w-full rounded-2xl border border-blue-100 bg-white px-4 py-3 text-sm shadow-sm outline-none ring-blue-500 focus:ring-2"
                placeholder="+63 900 123 4567"
              />
            </div>
          </div>
          <div>
            <label className="text-xs font-semibold uppercase tracking-wider text-blue-800">Password</label>
            <input
              type="password"
              name="password"
              value={form.password}
              onChange={updateField}
              className="mt-2 w-full rounded-2xl border border-blue-100 bg-white px-4 py-3 text-sm shadow-sm outline-none ring-blue-500 focus:ring-2"
              placeholder="Create a password"
              required
            />
          </div>
          {error ? (
            <div className="rounded-2xl border border-red-200 bg-red-50 px-4 py-3 text-sm text-red-700">
              {error}
            </div>
          ) : null}
          {success ? (
            <div className="rounded-2xl border border-blue-200 bg-blue-50 px-4 py-3 text-sm text-blue-700">
              {success}
            </div>
          ) : null}
          <button
            type="submit"
            disabled={loading}
            className="mt-2 rounded-2xl bg-blue-600 px-6 py-3 text-sm font-semibold text-white shadow-lg shadow-blue-500/40 transition hover:bg-blue-700 disabled:cursor-not-allowed disabled:opacity-70"
          >
            {loading ? 'Creating account...' : 'Create account'}
          </button>
        </form>
        <p className="mt-6 text-sm text-blue-800">
          Already have an account?{' '}
          <Link to="/login" className="font-semibold text-blue-600 hover:text-blue-700">Sign in</Link>
        </p>
      </section>
      <aside className="rounded-3xl bg-gradient-to-br from-blue-600 via-blue-700 to-blue-900 p-10 text-white shadow-2xl">
        <p className="text-xs font-semibold uppercase tracking-[0.3em] text-blue-200">Onboarding</p>
        <h2 className="mt-4 text-3xl font-bold">Launch your profile</h2>
        <p className="mt-4 text-sm leading-relaxed text-blue-100">
          Provide your details and instantly connect to the secure backend.
        </p>
        <div className="mt-8 grid gap-4">
          <div className="rounded-2xl bg-white/10 p-4">
            <p className="text-sm font-semibold">Fast setup</p>
            <p className="mt-1 text-xs text-blue-100">Register in seconds, login right away.</p>
          </div>
          <div className="rounded-2xl bg-white/10 p-4">
            <p className="text-sm font-semibold">Secure profile</p>
            <p className="mt-1 text-xs text-blue-100">Token-driven access for your data.</p>
          </div>
        </div>
      </aside>
    </div>
  );
}
