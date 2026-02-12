import React, { useState, useEffect } from 'react';

function TeamForm({ team, onSubmit, onCancel }) {
  const [formData, setFormData] = useState({
    teamName: '',
    coachName: '',
    ageLimit: ''
  });

  const [errors, setErrors] = useState({});

  useEffect(() => {
    if (team) {
      setFormData({
        teamName: team.teamName || '',
        coachName: team.coachName || '',
        ageLimit: team.ageLimit || ''
      });
    }
  }, [team]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
    // Clear error for this field when user starts typing
    if (errors[name]) {
      setErrors(prev => ({ ...prev, [name]: '' }));
    }
  };

  const validate = () => {
    const newErrors = {};

    if (!formData.teamName.trim()) {
      newErrors.teamName = 'Team name is required';
    } else if (formData.teamName.length < 2) {
      newErrors.teamName = 'Team name must be at least 2 characters';
    } else if (formData.teamName.length > 50) {
      newErrors.teamName = 'Team name cannot exceed 50 characters';
    }

    if (formData.coachName && formData.coachName.length > 100) {
      newErrors.coachName = 'Coach name cannot exceed 100 characters';
    }

    if (formData.ageLimit && (formData.ageLimit < 1 || formData.ageLimit > 100)) {
      newErrors.ageLimit = 'Age limit must be between 1 and 100';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!validate()) {
      return;
    }

    const submitData = {
      teamName: formData.teamName,
      coachName: formData.coachName || null,
      ageLimit: formData.ageLimit ? parseInt(formData.ageLimit) : null
    };

    if (team) {
      onSubmit(team.teamId, submitData);
    } else {
      onSubmit(submitData);
    }
  };

  return (
      <div className="team-form-container">
        <h2>{team ? '✏️ Edit Team' : '➕ Add New Team'}</h2>
        <form onSubmit={handleSubmit} className="team-form">
          <div className="form-group">
            <label htmlFor="teamName">Team Name *</label>
            <input
                type="text"
                id="teamName"
                name="teamName"
                value={formData.teamName}
                onChange={handleChange}
                placeholder="e.g., U11 Team, Senior Team"
                className={errors.teamName ? 'error' : ''}
            />
            {errors.teamName && <span className="error-message">{errors.teamName}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="coachName">Coach Name</label>
            <input
                type="text"
                id="coachName"
                name="coachName"
                value={formData.coachName}
                onChange={handleChange}
                placeholder="e.g., John Smith"
                className={errors.coachName ? 'error' : ''}
            />
            {errors.coachName && <span className="error-message">{errors.coachName}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="ageLimit">Age Limit</label>
            <input
                type="number"
                id="ageLimit"
                name="ageLimit"
                value={formData.ageLimit}
                onChange={handleChange}
                placeholder="e.g., 11, 13, 15"
                min="1"
                max="100"
                className={errors.ageLimit ? 'error' : ''}
            />
            {errors.ageLimit && <span className="error-message">{errors.ageLimit}</span>}
            <small>Leave empty for senior teams</small>
          </div>

          <div className="form-actions">
            <button type="submit" className="btn btn-success">
              {team ? '💾 Update Team' : '➕ Create Team'}
            </button>
            <button type="button" className="btn btn-secondary" onClick={onCancel}>
              ❌ Cancel
            </button>
          </div>
        </form>
      </div>
  );
}

export default TeamForm;
