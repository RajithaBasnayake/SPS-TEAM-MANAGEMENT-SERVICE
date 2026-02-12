import React from 'react';

function Statistics({ statistics }) {
    return (
        <div className="statistics">
            <div className="stat-card">
                <div className="stat-icon">🏏</div>
                <div className="stat-content">
                    <h3>{statistics.count || 0}</h3>
                    <p>Total Teams</p>
                </div>
            </div>
        </div>
    );
}

export default Statistics;
