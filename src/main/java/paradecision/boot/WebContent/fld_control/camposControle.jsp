<!-- ----------------------------------------------------- -->
<!-- Campos de Controle - Geral para todas as Páginas -->
<!-- A01 - TABELA DE EMPRESA -->
<!-- A02 - TABELA DE USUÁRIO -->
<!-- A03 - TABELA DE EMPRESA USUÁRIO PERFIL -->
<!-- ----------------------------------------------------- -->
<!-- USUÁRIO -->
<div id="cmpControle" style="display:none;">
A02_COD:<input type="text" name="ct_A02_CODIGO" id="ct_A02_CODIGO" value="" />
A02_USU<input type="text" name="ct_A02_USUARIO" id="ct_A02_USUARIO" value="" />
<!-- EMPRESA -->
QTD_EMPR<input type="text" name="ct_QTD_EMPRESAS" id="ct_QTD_EMPRESAS" value="" />
A01_COD<input type="text" name="ct_A01_CODIGO" id="ct_A01_CODIGO" value="" />
A01_NOM<input type="text" name="ct_A01_NOME" id="ct_A01_NOME" value="" />
<!-- PERFIL DO USUÁRIO NA EMPRESA -->
A03_PRF_PVB<input type="text" name="ct_A03_PERFIL_PARAVIVERBEM" id="ct_A03_PERFIL_PARAVIVERBEM" value="" />
A03_PRF_ADM<input type="text" name="ct_A03_PERFIL_ADMINISTRADOR" id="ct_A03_PERFIL_ADMINISTRADOR" value="" />
A03_PRF_CHF<input type="text" name="ct_A03_PERFIL_CHEFE" id="ct_A03_PERFIL_CHEFE" value="" />
A03_PRF_PDR<input type="text" name="ct_A03_PERFIL_PADRAO" id="ct_A03_PERFIL_PADRAO" value="" />
<!-- CONTROLE DE FLUXO -->
ACAO<input type="text" name="pdAcao" id="pdAcao" value="" />
<!-- ----------------------------------------------------- -->
</div>
<%
String ct_A02_CODIGO = request.getParameter("ct_A02_CODIGO");
String ct_A02_USUARIO = request.getParameter("ct_A02_USUARIO");
String ct_QTD_EMPRESAS = request.getParameter("ct_QTD_EMPRESAS");
String ct_A01_CODIGO = request.getParameter("ct_A01_CODIGO");
String ct_A01_NOME = request.getParameter("ct_A01_NOME");
String ct_A03_PERFIL_PARAVIVERBEM = request.getParameter("ct_A03_PERFIL_PARAVIVERBEM");
String ct_A03_PERFIL_ADMINISTRADOR = request.getParameter("ct_A03_PERFIL_ADMINISTRADOR");
String ct_A03_PERFIL_CHEFE = request.getParameter("ct_A03_PERFIL_CHEFE");
String ct_A03_PERFIL_PADRAO = request.getParameter("ct_A03_PERFIL_PADRAO");
String ct_pdAcao = request.getParameter("pdAcao");
//----------------------------------------------------
if (ct_A02_CODIGO == null) ct_A02_CODIGO = "";
if (ct_A02_USUARIO == null) ct_A02_USUARIO = "";
if (ct_QTD_EMPRESAS == null) ct_QTD_EMPRESAS = "";
if (ct_A01_CODIGO == null) ct_A01_CODIGO = "";
if (ct_A01_NOME == null) ct_A01_NOME = "";
if (ct_A03_PERFIL_PARAVIVERBEM == null) ct_A03_PERFIL_PARAVIVERBEM = "";
if (ct_A03_PERFIL_ADMINISTRADOR == null) ct_A03_PERFIL_ADMINISTRADOR = "";
if (ct_A03_PERFIL_CHEFE == null) ct_A03_PERFIL_CHEFE = "";
if (ct_A03_PERFIL_PADRAO == null) ct_A03_PERFIL_PADRAO = "";
if (ct_pdAcao == null) ct_pdAcao = "";
%>
<script type="text/javascript">
document.getElementById("ct_A02_CODIGO").value = "<%= ct_A02_CODIGO %>";
document.getElementById("ct_A02_USUARIO").value = "<%= ct_A02_USUARIO %>";
document.getElementById("ct_QTD_EMPRESAS").value = "<%= ct_QTD_EMPRESAS %>";
document.getElementById("ct_A01_CODIGO").value = "<%= ct_A01_CODIGO %>";
document.getElementById("ct_A01_NOME").value = "<%= ct_A01_NOME %>";
document.getElementById("ct_A03_PERFIL_PARAVIVERBEM").value = "<%= ct_A03_PERFIL_PARAVIVERBEM %>";
document.getElementById("ct_A03_PERFIL_ADMINISTRADOR").value = "<%= ct_A03_PERFIL_ADMINISTRADOR %>";
document.getElementById("ct_A03_PERFIL_CHEFE").value = "<%= ct_A03_PERFIL_CHEFE %>";
document.getElementById("ct_A03_PERFIL_PADRAO").value = "<%= ct_A03_PERFIL_PADRAO %>";
document.getElementById("pdAcao").value = "<%= ct_pdAcao %>";
</script>
