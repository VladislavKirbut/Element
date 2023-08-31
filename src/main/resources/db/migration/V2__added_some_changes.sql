ALTER TABLE authorization_data RENAME TO authentication_data;

ALTER TABLE authentication_data DROP COLUMN id;

ALTER TABLE authentication_data ADD PRIMARY KEY (users_id);