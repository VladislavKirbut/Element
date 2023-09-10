CREATE TABLE folder (
    id INT PRIMARY KEY,
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    folder_name TEXT NOT NULL
);