CREATE DATABASE CrimeManagement;

USE CrimeManagement;

-- Table for storing crimes and punishmentsuser_id
CREATE TABLE Punishments (
    crime_id INT AUTO_INCREMENT PRIMARY KEY,
    crime_name VARCHAR(100) NOT NULL,
    punishment_description TEXT NOT NULL
);
insert into Punishments(crime_id,crime_name,punishment_description) values
(1,'Murder','Death Penalty'),
(2,'Gunfire','5 years imprisonment'),
(3,'Robbery','1-3 years imprisonment'),
(4,'Harassment','5-10 years imprisonment'),
(5,'public unrest','Depends upon Severity');

select*from Punishments;
-- Table for storing criminal details
CREATE TABLE Criminals (
    criminal_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    crime VARCHAR(100) NOT NULL,
    bounty DECIMAL(10, 2) DEFAULT 0.00,
    image_path VARCHAR(255), -- Stores image file path
    is_imprisoned BOOLEAN DEFAULT FALSE
);
select*from Criminals; 

-- Table for reports made by the public
CREATE TABLE Reports (
    report_id INT AUTO_INCREMENT PRIMARY KEY,
    reporter_name VARCHAR(100) NOT NULL,
    reporter_cnic VARCHAR(15) NOT NULL,
    crime_details TEXT NOT NULL,
    possible_suspects TEXT,
    status VARCHAR(50) DEFAULT 'Pending'
);
select*from Reports;

-- Table for executor and officer credentials
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role ENUM('Executor', 'Officer') NOT NULL
);
select*from Users;

-- Table for squads
CREATE TABLE Squads (
    squad_id INT AUTO_INCREMENT PRIMARY KEY,
    squad_name VARCHAR(100) NOT NULL
);


-- Table to map officers to squads
CREATE TABLE SquadAssignments (
    assignment_id INT AUTO_INCREMENT PRIMARY KEY,
    officer_id INT NOT NULL,
    squad_id INT NOT NULL,
    FOREIGN KEY (officer_id) REFERENCES Users(user_id),
    FOREIGN KEY (squad_id) REFERENCES Squads(squad_id)
);
