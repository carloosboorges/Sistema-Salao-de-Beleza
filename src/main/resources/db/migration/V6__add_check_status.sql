ALTER TABLE agendamento_tb
    ADD CONSTRAINT chk_status
        CHECK (status IN ('AGENDADO', 'CANCELADO', 'CONCLUIDO'));
