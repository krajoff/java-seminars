FROM postgres:13.2-alpine
ENV POSTGRES_DB wallets
ENV POSTGRES_USER root
ENV POSTGRES_PASSWORD password
COPY init.sql /docker-entrypoint-initdb.d/