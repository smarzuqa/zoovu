DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id VARCHAR(250) NOT NULL PRIMARY KEY,
  username VARCHAR(250) NOT NULL,
  score INT,
  login_date DATE NOT NULL
);

INSERT INTO user (id, username, score, login_date) VALUES   ('100', 'Sami', 10, CURRENT_DATE());
  INSERT INTO user (id, username, score, login_date) VALUES ('101', 'Klau', 100, CURRENT_DATE());
  INSERT INTO user (id, username, score, login_date) VALUES ('102', 'Nino', 200, CURRENT_DATE());
  INSERT INTO user (id, username, score, login_date) VALUES ('103', 'Kate', 300, to_DATE('01/05/2020','dd/mm/yyyy'));
  INSERT INTO user (id, username, score, login_date) VALUES ('104', 'Tshuli', 300, to_DATE('01/06/2020','dd/mm/yyyy'));
  INSERT INTO user (id, username, score, login_date) VALUES ('105', 'Ma', 400, to_DATE('01/09/2020','dd/mm/yyyy'));
  INSERT INTO user (id, username, score, login_date) VALUES ('106', 'Pa', 100, to_DATE('01/09/2019','dd/mm/yyyy'));
  INSERT INTO user (id, username, score, login_date) VALUES ('107', 'Ali', 100, to_DATE('01/09/2019','dd/mm/yyyy'));
  INSERT INTO user (id, username, score, login_date) VALUES ('108', 'Kobe', 100, to_DATE('01/10/2018','dd/mm/yyyy'));