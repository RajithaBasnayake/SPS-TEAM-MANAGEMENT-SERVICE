import React, { useState, useEffect } from 'react';
import './App.css';
import teamService from './services/teamService';
import TeamForm from './components/TeamForm';
import TeamList from './components/TeamList';
import SearchBar from './components/SearchBar';
import Statistics from './components/Statistics';

function App() {
  const [teams, setTeams] = useState([]);
  const [filteredTeams, setFilteredTeams] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const [editingTeam, setEditingTeam] = useState(null);
  const [showForm, setShowForm] = useState(false);
  const [statistics, setStatistics] = useState({ count: 0 });

  // Fetch all teams on component mount
  useEffect(() => {
    fetchTeams();
    fetchStatistics();
  }, []);

  // Fetch all teams
  const fetchTeams = async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await teamService.getAllTeams();
      setTeams(data);
      setFilteredTeams(data);
    } catch (err) {
      setError('Failed to load teams. Please try again.');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  // Fetch statistics
  const fetchStatistics = async () => {
    try {
      const data = await teamService.getTeamCount();
      setStatistics(data);
    } catch (err) {
      console.error('Failed to fetch statistics:', err);
    }
  };

  // Handle create team
  const handleCreate = async (teamData) => {
    try {
      await teamService.createTeam(teamData);
      setSuccess('Team created successfully!');
      fetchTeams();
      fetchStatistics();
      setShowForm(false);
      clearMessages();
    } catch (err) {
      const errorMsg = err.response?.data?.message || 'Failed to create team';
      setError(errorMsg);
      clearMessages();
    }
  };

  // Handle update team
  const handleUpdate = async (id, teamData) => {
    try {
      await teamService.updateTeam(id, teamData);
      setSuccess('Team updated successfully!');
      fetchTeams();
      setEditingTeam(null);
      setShowForm(false);
      clearMessages();
    } catch (err) {
      const errorMsg = err.response?.data?.message || 'Failed to update team';
      setError(errorMsg);
      clearMessages();
    }
  };

  // Handle delete team
  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this team?')) {
      try {
        await teamService.deleteTeam(id);
        setSuccess('Team deleted successfully!');
        fetchTeams();
        fetchStatistics();
        clearMessages();
      } catch (err) {
        setError('Failed to delete team');
        clearMessages();
      }
    }
  };

  // Handle edit button click
  const handleEdit = (team) => {
    setEditingTeam(team);
    setShowForm(true);
  };

  // Handle search
  const handleSearch = async (searchTerm) => {
    if (!searchTerm.trim()) {
      setFilteredTeams(teams);
      return;
    }

    try {
      const data = await teamService.searchTeams(searchTerm);
      setFilteredTeams(data);
    } catch (err) {
      setError('Search failed');
      clearMessages();
    }
  };

  // Clear success/error messages after 3 seconds
  const clearMessages = () => {
    setTimeout(() => {
      setSuccess(null);
      setError(null);
    }, 3000);
  };

  // Cancel form
  const handleCancel = () => {
    setShowForm(false);
    setEditingTeam(null);
  };

  return (
      <div className="App">
        <header className="app-header">
          <h1>🏏 SPS Cricket Club - Team Management</h1>
          <p>Microservice for Managing Cricket Teams</p>
        </header>

        <main className="app-main">
          <Statistics statistics={statistics} />

          {error && (
              <div className="alert alert-error">
                ❌ {error}
              </div>
          )}

          {success && (
              <div className="alert alert-success">
                ✅ {success}
              </div>
          )}

          <div className="actions-bar">
            <button
                className="btn btn-primary"
                onClick={() => setShowForm(!showForm)}
            >
              {showForm ? '❌ Cancel' : '➕ Add New Team'}
            </button>
          </div>

          {showForm && (
              <TeamForm
                  team={editingTeam}
                  onSubmit={editingTeam ? handleUpdate : handleCreate}
                  onCancel={handleCancel}
              />
          )}

          <SearchBar onSearch={handleSearch} />

          {loading ? (
              <div className="loading">Loading teams...</div>
          ) : (
              <TeamList
                  teams={filteredTeams}
                  onEdit={handleEdit}
                  onDelete={handleDelete}
              />
          )}
        </main>

        <footer className="app-footer">
          <p>Team Management Microservice v1.0 | Port: 8081</p>
        </footer>
      </div>
  );
}

export default App;
