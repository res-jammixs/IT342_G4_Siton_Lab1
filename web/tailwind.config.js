/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      boxShadow: {
        glow: "0 0 30px rgba(37, 99, 235, 0.25)",
      },
    },
  },
  plugins: [],
};
