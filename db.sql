-- Team Management Database Schema

CREATE DATABASE IF NOT EXISTS team_management_db;
USE team_management_db;

-- Teams Table
CREATE TABLE IF NOT EXISTS teams (
    team_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    team_name VARCHAR(50) NOT NULL UNIQUE,
    coach_name VARCHAR(100),
    age_limit INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_team_name (team_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert Sample Data
INSERT INTO teams (team_name, coach_name, age_limit) VALUES
('U11 Team', 'John Smith', 11),
('U13 Team', 'Sarah Johnson', 13),
('U15 Team', 'Michael Brown', 15),
('U17 Team', 'David Wilson', 17),
('Senior Team', 'Robert Taylor', NULL);
