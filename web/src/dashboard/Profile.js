import React, { useEffect, useState } from 'react';
import { apiFetch } from '../api';
import { getToken } from '../auth/auth';

export default function Profile() {
  const [profile, setProfile] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    let active = true;
    async function loadProfile() {
      setLoading(true);
      setError('');
      try {
        const data = await apiFetch('/api/profile', { token: getToken() });
        if (active) {
          setProfile(data);
        }
      } catch (err) {
        if (active) {
          setError(err.message || 'Unable to load profile');
        }
      } finally {
        if (active) {
          setLoading(false);
        }
      }
    }
    loadProfile();
    return () => {
      active = false;
    };
  }, []);

  return (
    <div className="grid gap-8">
      <section className="rounded-3xl bg-white/90 p-10 shadow-xl shadow-blue-500/20">
        <p className="text-xs font-semibold uppercase tracking-[0.3em] text-blue-500">Profile</p>
        <h1 className="mt-3 text-4xl font-extrabold text-blue-950">Your account</h1>
        <p className="mt-4 text-sm text-blue-700">Pulled straight from the backend.</p>
      </section>
      <section className="rounded-3xl bg-white/80 p-8 shadow-lg shadow-blue-500/10">
        {loading ? (
          <p className="text-sm text-blue-700">Loading profile...</p>
        ) : error ? (
          <div className="rounded-2xl border border-red-200 bg-red-50 px-4 py-3 text-sm text-red-700">
            {error}
          </div>
        ) : profile ? (
          <div className="grid gap-4 sm:grid-cols-2">
            <div>
              <p className="text-xs font-semibold uppercase tracking-wider text-blue-500">Name</p>
              <p className="mt-2 text-lg font-semibold text-blue-950">
                {profile.firstName} {profile.middleName ? `${profile.middleName} ` : ''}{profile.lastName}
              </p>
            </div>
            <div>
              <p className="text-xs font-semibold uppercase tracking-wider text-blue-500">Email</p>
              <p className="mt-2 text-sm text-blue-900">{profile.email}</p>
            </div>
            <div>
              <p className="text-xs font-semibold uppercase tracking-wider text-blue-500">Phone</p>
              <p className="mt-2 text-sm text-blue-900">{profile.phoneNumber || 'Not set'}</p>
            </div>
            <div>
              <p className="text-xs font-semibold uppercase tracking-wider text-blue-500">Status</p>
              <p className="mt-2 text-sm text-blue-900">{profile.isActive ? 'Active' : 'Inactive'}</p>
            </div>
            <div>
              <p className="text-xs font-semibold uppercase tracking-wider text-blue-500">Created</p>
              <p className="mt-2 text-sm text-blue-900">
                {profile.createdAt ? new Date(profile.createdAt).toLocaleString() : 'Unknown'}
              </p>
            </div>
          </div>
        ) : null}
      </section>
    </div>
  );
}
 