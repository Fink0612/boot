package paradecision.boot.modulos.compartilhado.infra;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Component
@ConditionalOnProperty(name="app.banco",havingValue="mysql",matchIfMissing=true)
public class MysqlBancoDados implements BancoDados {
  private static String nome(String s) {
    if(!s.matches("[a-z][a-z0-9_]*")) throw new IllegalArgumentException("Identificador inválido");
    return s.toUpperCase(Locale.ROOT);
  }
  private static String onde(Map<String,Object> f) {
    if(f.isEmpty()) throw new IllegalArgumentException("Informe filtros para a operação");
    return f.keySet().stream().map(k->nome(k)+"=?").collect(Collectors.joining(" AND "," WHERE ",""));
  }
  private static void preencher(PreparedStatement s, Collection<Object> valores) throws SQLException {
    int i=1; for(var v:valores) s.setObject(i++,v);
  }
  private static List<Registro> ler(ResultSet rs) throws SQLException {
    List<Registro> dados=new ArrayList<>(); var meta=rs.getMetaData();
    while(rs.next()) { Map<String,Object> r=new LinkedHashMap<>(); for(int i=1;i<=meta.getColumnCount();i++) r.put(meta.getColumnLabel(i).toLowerCase(Locale.ROOT),rs.getObject(i)); dados.add(new Registro(r)); }
    return dados;
  }
  public List<Registro> listar(String tabela,Map<String,Object> filtros) {
    try(var c=new ConnectionFactory().getConnection();var s=c.prepareStatement("SELECT * FROM "+nome(tabela)+onde(filtros))) {
      preencher(s,filtros.values());try(var r=s.executeQuery()) { return ler(r); }
    } catch(SQLException e) { throw new FalhaPersistencia(e); }
  }
  public Registro inserir(String tabela,Map<String,Object> dados) {
    var colunas=dados.keySet().stream().map(MysqlBancoDados::nome).collect(Collectors.joining(","));
    try(var c=new ConnectionFactory().getConnection();var s=c.prepareStatement("INSERT INTO "+nome(tabela)+" ("+colunas+") VALUES ("+String.join(",",Collections.nCopies(dados.size(),"?"))+")",Statement.RETURN_GENERATED_KEYS)) {
      preencher(s,dados.values());s.executeUpdate(); var r=new LinkedHashMap<>(dados);
      try(var keys=s.getGeneratedKeys()) { if(keys.next()) {String pk=switch(tabela) {case "usuario_02"->"a02_codigo";case "agenda_04"->"a04_codigo";case "usuario_agenda_05"->"a05_codigo";case "fator_06"->"a06_codigo";case "parecer_fator_usuario_07"->"a07_codigo";default->null;};if(pk!=null) r.put(pk,keys.getLong(1));} }
      return new Registro(r);
    } catch(SQLException e) { throw new FalhaPersistencia(e); }
  }
  public void atualizar(String tabela,Map<String,Object> filtros,Map<String,Object> dados) {
    var valores=new ArrayList<>(dados.values());valores.addAll(filtros.values());
    executar("UPDATE "+nome(tabela)+" SET "+dados.keySet().stream().map(k->nome(k)+"=?").collect(Collectors.joining(","))+onde(filtros),valores);
  }
  public void excluir(String tabela,Map<String,Object> filtros) { executar("DELETE FROM "+nome(tabela)+onde(filtros),filtros.values()); }
  private void executar(String sql,Collection<Object> valores) {
    try(var c=new ConnectionFactory().getConnection();var s=c.prepareStatement(sql)) { preencher(s,valores);s.executeUpdate(); }
    catch(SQLException e) { throw new FalhaPersistencia(e); }
  }
}
