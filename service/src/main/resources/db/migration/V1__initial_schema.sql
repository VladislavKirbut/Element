CREATE TABLE users (
    id UUID PRIMARY KEY,
    name TEXT NOT NULL,
    surname TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL
);

CREATE TABLE authorization_data (
    id UUID PRIMARY KEY,
    users_id UUID REFERENCES users(id) ON DELETE CASCADE,
    phone_number TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL
);
