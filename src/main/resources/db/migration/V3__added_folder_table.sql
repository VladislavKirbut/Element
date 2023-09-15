CREATE TABLE folder (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    folder_name TEXT NOT NULL
);