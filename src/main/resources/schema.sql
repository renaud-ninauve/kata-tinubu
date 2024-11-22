DROP TABLE IF EXISTS insurance_policies;
CREATE TABlE insurance_policies (
    id                  BIGSERIAL PRIMARY KEY
);

DROP SEQUENCE IF EXISTS insurance_policies_seq;
CREATE SEQUENCE insurance_policies_seq INCREMENT 5;