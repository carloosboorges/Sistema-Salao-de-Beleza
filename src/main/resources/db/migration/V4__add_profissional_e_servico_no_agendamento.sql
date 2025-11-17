ALTER TABLE agendamento_tb
    ADD COLUMN profissional_id BIGINT;

ALTER TABLE agendamento_tb
    ADD COLUMN servico_id BIGINT;

ALTER TABLE agendamento_tb
    ADD CONSTRAINT fk_agendamento_profissional
        FOREIGN KEY (profissional_id)
            REFERENCES profissional_tb(id);

ALTER TABLE agendamento_tb
    ADD CONSTRAINT fk_agendamento_servico
        FOREIGN KEY (servico_id)
            REFERENCES servico_tb(id);
