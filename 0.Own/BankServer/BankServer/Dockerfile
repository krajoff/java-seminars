FROM postgres:13.2-alpine
ENV POSTGRES_DB banking
ENV POSTGRES_USER root
ENV POSTGRES_PASSWORD passwords
COPY init.sql /docker-entrypoint-initdb.d/