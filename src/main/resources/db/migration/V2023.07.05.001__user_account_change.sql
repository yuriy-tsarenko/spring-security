ALTER TABLE user_account
    ADD COLUMN is_account_non_expired BOOLEAN;

ALTER TABLE user_account
    ADD COLUMN is_account_non_locked BOOLEAN;

ALTER TABLE user_account
    ADD COLUMN is_credentials_non_expired BOOLEAN;

ALTER TABLE user_account
    ADD COLUMN is_enabled BOOLEAN;

ALTER TABLE user_account
    ADD COLUMN authorities TEXT;