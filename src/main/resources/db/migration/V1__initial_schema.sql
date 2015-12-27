CREATE TABLE songs
(
  song_id serial NOT NULL,
  song_name character varying(100),
  CONSTRAINT song_id PRIMARY KEY (song_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE songs
  OWNER TO toneuser;


CREATE TABLE urls
(
  url_id serial NOT NULL,
  url_str character varying(100),
  url_content character varying(200),
  CONSTRAINT url_id PRIMARY KEY (url_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE urls
  OWNER TO toneuser;

