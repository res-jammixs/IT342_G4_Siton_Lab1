import React from 'react';
import { Link } from 'react-router-dom';

export default function Dashboard() {
  return (
    <div className="grid gap-8">
      <section className="rounded-3xl bg-white/90 p-10 shadow-xl shadow-blue-500/20">
        <p className="text-xs font-semibold uppercase tracking-[0.3em] text-blue-500">Dashboard</p>
        <h1 className="mt-3 text-4xl font-extrabold text-blue-950">Welcome back</h1>
        <p className="mt-4 max-w-2xl text-sm text-blue-700">
          You are now connected to the backend. Use the profile panel to review your details.
        </p>
        <div className="mt-6 flex flex-wrap gap-4">
          <Link
            to="/profile"
            className="rounded-2xl bg-blue-600 px-6 py-3 text-sm font-semibold text-white shadow-lg shadow-blue-500/40 transition hover:bg-blue-700"
          >
            View profile
          </Link>
          <Link
            to="/logout"
            className="rounded-2xl border border-blue-200 bg-white px-6 py-3 text-sm font-semibold text-blue-700 transition hover:border-blue-300 hover:text-blue-900"
          >
            Sign out
          </Link>
        </div>
      </section>
      <section className="grid gap-6 md:grid-cols-3">
        {["Token secured", "Profile ready", "Backend connected"].map((item) => (
          <div key={item} className="rounded-3xl bg-white/80 p-6 shadow-lg shadow-blue-500/10">
            <p className="text-sm font-semibold text-blue-900">{item}</p>
            <p className="mt-2 text-xs text-blue-700">
              Your session stays secure and synced with the API.
            </p>
          </div>
        ))}
      </section>
    </div>
  );
}
