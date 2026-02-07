import React from 'react';

export default function Login() {
  return (
    <div className="page login-page">
      <h1>Login</h1>
      <form>
        <label>
          Email
          <input type="email" name="email" />
        </label>
        <label>
          Password
          <input type="password" name="password" />
        </label>
        <button type="submit">Sign in</button>
      </form>
    </div>
  );
}
