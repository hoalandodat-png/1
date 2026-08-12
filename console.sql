-- Tạo database
CREATE DATABASE rikkei_erp_db
    WITH
    OWNER     = postgres
    ENCODING  = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE   = 'en_US.UTF-8'
    TEMPLATE  = template0;

-- Kết nối vào database vừa tạo
\c rikkei_erp_db;