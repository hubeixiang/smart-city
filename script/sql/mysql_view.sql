DROP VIEW IF EXISTS v_sc_organization;
CREATE VIEW v_sc_organization AS SELECT
	sc_c. NAME AS community_id_ext,
	sc_g.grid_no AS grid_id_ext,
	sc.*
FROM
	sc_organization sc
LEFT JOIN sc_community sc_c ON sc.community_id = sc_c.id
LEFT JOIN sc_grid sc_g ON sc.grid_id = sc_g.id;