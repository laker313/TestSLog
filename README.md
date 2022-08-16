для установки создаем в папке с jar application.properties файл фида(
db.driver=org.postgresql.Driver
db.username=root
db.password=root
db.url=jdbc:postgresql://localhost/dbname
hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
hibernate.show_sql=true
entitymanager.packagesToScan=com.example.restfulltest
management.endpoints.web.exposure.include=*
server.port=80
)
устанавливаем базу данных и используем 
скрипты для бд(BEGIN;


CREATE TABLE IF NOT EXISTS public.log
(
    log_id integer NOT NULL DEFAULT nextval('"Log_log_id_seq"'::regclass),
    log_message text COLLATE pg_catalog."default" NOT NULL,
    log_type text COLLATE pg_catalog."default" NOT NULL,
    log_level text COLLATE pg_catalog."default" NOT NULL,
    log_time timestamp without time zone,
    CONSTRAINT log_pk PRIMARY KEY (log_id)
);
END;)
запускаем jar
