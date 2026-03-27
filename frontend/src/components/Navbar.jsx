import React from 'react';

const Navbar = () => {
  return (
    <nav className="bg-blue-600 text-white shadow-md">
      <div className="container-custom py-4">
        <div className="flex justify-between items-center">
          <h1 className="text-2xl font-bold">Leave Management System</h1>
          <ul className="flex gap-6">
            <li><a href="/" className="hover:text-blue-200">Home</a></li>
            <li><a href="/dashboard" className="hover:text-blue-200">Dashboard</a></li>
            <li><a href="/profile" className="hover:text-blue-200">Profile</a></li>
            <li><a href="/logout" className="hover:text-blue-200">Logout</a></li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
