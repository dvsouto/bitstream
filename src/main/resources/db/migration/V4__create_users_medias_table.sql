CREATE TABLE users_medias (
    user_media_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    user_profile_id UUID NOT NULL,

    user_media_type VARCHAR(50) NOT NULL,
    user_media_name VARCHAR(255) NOT NULL,
    useR_media_path VARCHAR(255) NOT NULL,

    user_media_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_media_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE users_medias
    ADD CONSTRAINT fk_users_medias_user_id FOREIGN KEY (user_id) REFERENCES users(user_id);

ALTER TABLE users_medias
    ADD CONSTRAINT fk_users_medias_user_profile_id FOREIGN KEY (user_profile_id) REFERENCES users_profiles(user_profile_id);