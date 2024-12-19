CREATE TABLE users_profiles (
    user_profile_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    user_profile_name VARCHAR(255) NOT NULL,
    user_profile_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_profile_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE users_profiles
    ADD CONSTRAINT fk_users_profiles_user_id FOREIGN KEY (user_id) REFERENCES users(user_id);

CREATE INDEX idx_users_profiles_user_id
    ON users_profiles(user_id);