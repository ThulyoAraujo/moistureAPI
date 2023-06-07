--UP
CREATE TABLE IF NOT EXISTS trs_rea_reader (
      rea_id SERIAL PRIMARY KEY NOT NULL,
      rea_moisture VARCHAR NOT NULL,
      rea_date VARCHAR,
      rea_time VARCHAR,
      rea_latitude VARCHAR,
      rea_longitude VARCHAR
);

--DOWN
DROP TABLE IF EXISTS trs_rea_reader;