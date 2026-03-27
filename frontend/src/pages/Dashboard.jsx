import React from 'react';

const Dashboard = () => {
  return (
    <div className="container-custom py-8">
      <h1 className="text-3xl font-bold mb-8">Dashboard</h1>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
        <div className="card">
          <h3 className="text-xl font-semibold mb-2">Total Leave Balance</h3>
          <p className="text-3xl font-bold text-blue-600">45</p>
          <p className="text-sm text-gray-600">days remaining</p>
        </div>

        <div className="card">
          <h3 className="text-xl font-semibold mb-2">Pending Requests</h3>
          <p className="text-3xl font-bold text-yellow-600">3</p>
          <p className="text-sm text-gray-600">awaiting approval</p>
        </div>

        <div className="card">
          <h3 className="text-xl font-semibold mb-2">Approved Leaves</h3>
          <p className="text-3xl font-bold text-green-600">12</p>
          <p className="text-sm text-gray-600">this year</p>
        </div>
      </div>

      <div className="card">
        <h2 className="text-2xl font-bold mb-4">Your Recent Leaves</h2>
        <table className="table">
          <thead>
            <tr>
              <th>Leave Type</th>
              <th>Start Date</th>
              <th>End Date</th>
              <th>Days</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Casual</td>
              <td>2024-02-15</td>
              <td>2024-02-17</td>
              <td>3</td>
              <td><span className="badge badge-success">Approved</span></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Dashboard;
