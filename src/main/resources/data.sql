INSERT INTO TB_SESSAO_VOTACAO (SESSAO_VOTACAO_ID, DURACAO, INICIO_SESSAO, ENCERRA_SESSAO, STATUS, TITULO)  VALUES('53179d68-b26d-4a23-a3da-da0181fd8c55', '01:00', null, null, 'PENDING', 'Assembleia 1');
INSERT INTO TB_PAUTA(PAUTA_ID, SESSAO_VOTACAO_ID, TITULO, COUNT_APROVADOS, COUNT_REPROVADOS, DESCRICAO, RESULTADO) VALUES('f82293f9-ed93-45f0-8473-c2e13f9eb9e3', '53179d68-b26d-4a23-a3da-da0181fd8c55', 'TAXA DE JUROS', 0, 0, 'INFLAÇÃO...', 0);
INSERT INTO TB_ASSOCIADO (ASSOCIADO_ID, NOME, CPF) VALUES ('750d929d-1927-4da8-9d67-d5ef46270265', 'ASSOCIADO 1', '08223584093');
INSERT INTO TB_ASSOCIADO (ASSOCIADO_ID, NOME, CPF) VALUES ('f9171587-5b60-4212-bdd3-b13e83204982', 'ASSOCIADO 2', '94679109009');
INSERT INTO TB_ASSOCIADO (ASSOCIADO_ID, NOME, CPF) VALUES ('fd466649-1905-42f2-a4b6-9e515839f39c', 'ASSOCIADO 3', '70494564032');
INSERT INTO TB_ASSOCIADO (ASSOCIADO_ID, NOME, CPF) VALUES ('b62bc124-0108-4c50-a084-f7df28c26659', 'ASSOCIADO 4', '99927277059');
INSERT INTO TB_ASSOCIADO (ASSOCIADO_ID, NOME, CPF) VALUES ('acf5286f-14f2-4503-b9d0-5b36c89262a4', 'ASSOCIADO 5', '50461727048');
INSERT INTO TB_VOTO (VOTO_ID, PAUTA_ID, ASSOCIADO_ID, SITUACAO) VALUES ('9f5cc71b-4298-43f4-9b4f-6a7d4bb3338e', 'f82293f9-ed93-45f0-8473-c2e13f9eb9e3', '750d929d-1927-4da8-9d67-d5ef46270265', 'NAO');
INSERT INTO TB_VOTO (VOTO_ID, PAUTA_ID, ASSOCIADO_ID, SITUACAO) VALUES ('25b27a23-8024-48a9-b9a3-efe7b17e141d', 'f82293f9-ed93-45f0-8473-c2e13f9eb9e3', 'f9171587-5b60-4212-bdd3-b13e83204982', 'NAO');
INSERT INTO TB_VOTO (VOTO_ID, PAUTA_ID, ASSOCIADO_ID, SITUACAO) VALUES ('6ba3d17c-f6e7-477c-ae89-9d6af418edc4', 'f82293f9-ed93-45f0-8473-c2e13f9eb9e3', 'fd466649-1905-42f2-a4b6-9e515839f39c', 'NAO');
INSERT INTO TB_VOTO (VOTO_ID, PAUTA_ID, ASSOCIADO_ID, SITUACAO) VALUES ('1df43bc7-b84d-4939-adb4-d7f1b9754362', 'f82293f9-ed93-45f0-8473-c2e13f9eb9e3', 'b62bc124-0108-4c50-a084-f7df28c26659', 'NAO');
INSERT INTO TB_VOTO (VOTO_ID, PAUTA_ID, ASSOCIADO_ID, SITUACAO) VALUES ('6c6d4c3d-5fec-43a2-a063-f7a560e2e427', 'f82293f9-ed93-45f0-8473-c2e13f9eb9e3', 'acf5286f-14f2-4503-b9d0-5b36c89262a4', 'SIM');
