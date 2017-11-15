CREATE TABLE clinic.schedule
(
  schedule_id VARCHAR(255) PRIMARY KEY NOT NULL,
  doctor_id VARCHAR(255),
  patient_id VARCHAR(255) DEFAULT NULL,
  time_start TIMESTAMP,
  time_finish TIMESTAMP
);