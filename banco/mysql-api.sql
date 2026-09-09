-- Complemento do banco/schema.sql para a API REST
USE ssdparaviverbem;

CREATE OR REPLACE VIEW VW_USUARIO_EMPRESAS AS SELECT E.*, P.A02_CODIGO, P.A03_PERFIL_PARAVIVERBEM, P.A03_PERFIL_ADMINISTRADOR, P.A03_PERFIL_CHEFE, P.A03_PERFIL_PADRAO, P.A03_DT_CADASTRO FROM EMPRESA_01 E JOIN EMPRESA_USUARIO_PERFIL_03 P ON P.A01_CODIGO=E.A01_CODIGO;
CREATE OR REPLACE VIEW VW_EMPRESA_USUARIOS AS SELECT U.*, P.A01_CODIGO, P.A03_PERFIL_PARAVIVERBEM, P.A03_PERFIL_ADMINISTRADOR, P.A03_PERFIL_CHEFE, P.A03_PERFIL_PADRAO, P.A03_DT_CADASTRO FROM USUARIO_02 U JOIN EMPRESA_USUARIO_PERFIL_03 P ON P.A02_CODIGO=U.A02_CODIGO;

-- Somente ambiente de teste: usuário admin, senha admin. Não altera senha existente.
insert into empresa_01(a01_nome,a01_descricao) select 'Empresa de Testes','Ambiente de estudo ParaDecision' where not exists(select 1 from empresa_01 where a01_nome='Empresa de Testes');
insert into usuario_02(a02_nome,a02_usuario,a02_senha,a02_email,a02_status) values('Administrador','admin','pbkdf2$2k+spY21kcv8H1EZSz+NDw==$we2lgofoq8VILG5IKla78yMtBDk3MPJ6TSNIlCvjwwM=','admin@example.test',1) ON DUPLICATE KEY UPDATE A02_USUARIO=A02_USUARIO;
insert into empresa_usuario_perfil_03(a01_codigo,a02_codigo,a03_perfil_administrador,a03_perfil_chefe,a03_perfil_padrao) select e.a01_codigo,u.a02_codigo,1,1,1 from empresa_01 e cross join usuario_02 u where e.a01_nome='Empresa de Testes' and u.a02_usuario='admin' ON DUPLICATE KEY UPDATE A01_CODIGO=EMPRESA_USUARIO_PERFIL_03.A01_CODIGO;
