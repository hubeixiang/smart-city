DROP VIEW IF EXISTS v_sc_organization;
CREATE VIEW v_sc_organization AS SELECT
	sc_c. NAME AS community_id_ext,
	sc_g.grid_no AS grid_id_ext,
	sc.*
FROM
	sc_organization sc
LEFT JOIN sc_community sc_c ON sc.community_id = sc_c.id
LEFT JOIN sc_grid sc_g ON sc.grid_id = sc_g.id;
CREATE VIEW v_sc_party_topic AS SELECT
	sc_p. NAME AS party_id_ext,
	sc.*
FROM
	sc_party_topic sc
LEFT JOIN sc_community_party sc_p ON sc.party_id = sc_p.id;

CREATE VIEW v_sc_party_activities AS SELECT
	sc_p. NAME AS party_id_ext,
	sc_t.description AS topic_id_ext,
	sc.*
FROM
	sc_party_activities sc
LEFT JOIN sc_community_party sc_p ON sc.party_id = sc_p.id
LEFT JOIN sc_party_topic sc_t ON sc.topic_id = sc_t.id;