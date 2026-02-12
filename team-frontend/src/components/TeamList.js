import React from 'react';

function TeamList({ teams, onEdit, onDelete }) {
  if (!teams || teams.length === 0) {
    return (
        <div className="no-data">
          <p>📋 No teams found. Add your first team!</p>
        </div>
    );
  }

  const formatDate = (dateString) => {
    if (!dateString) return 'N/A';
    const date = new Date(dateString);
    return date.toLocaleDateString('en-US', {
      year: 'numeric',
      month: 'short',
      day: 'numeric'
    });
  };

  return (
      <div className="team-list-container">
        <h2> Teams List ({teams.length})</h2>
        <div className="table-responsive">
          <table className="team-table">
            <thead>
            <tr>
              <th>ID</th>
              <th>Team Name</th>
              <th>Coach Name</th>
              <th>Age Limit</th>
              <th>Created At</th>
              <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            {teams.map((team) => (
                <tr key={team.teamId}>
                  <td>{team.teamId}</td>
                  <td className="team-name">
                    <strong>{team.teamName}</strong>
                  </td>
                  <td>{team.coachName || '—'}</td>
                  <td>
                    {team.ageLimit ? (
                        <span className="age-badge">U{team.ageLimit}</span>
                    ) : (
                        <span className="senior-badge">Senior</span>
                    )}
                  </td>
                  <td>{formatDate(team.createdAt)}</td>
                  <td className="action-buttons">
                    <button
                        className="btn btn-edit"
                        onClick={() => onEdit(team)}
                        title="Edit team"
                    >
                      ✏ Edit
                    </button>
                    <button
                        className="btn btn-delete"
                        onClick={() => onDelete(team.teamId)}
                        title="Delete team"
                    >
                      🗑 Delete
                    </button>
                  </td>
                </tr>
            ))}
            </tbody>
          </table>
        </div>
      </div>
  );
}

export default TeamList;
