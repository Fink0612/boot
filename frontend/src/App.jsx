import {useEffect,useState} from 'react';
import Login from './modulos/autenticacao/Login.jsx';
import {autenticacao} from './modulos/autenticacao/api.js';
import Empresas from './modulos/empresas/Empresas.jsx';
import Agendas from './modulos/agendas/Agendas.jsx';
import AgendaDetalhe from './modulos/agendas/AgendaDetalhe.jsx';
import Usuarios from './modulos/usuarios/Usuarios.jsx';
import {Aviso} from './modulos/compartilhado/components.jsx';
export default function App() {
  const [usuario,setUsuario]=useState(null),[iniciando,setIniciando]=useState(true),[empresa,setEmpresa]=useState(null),[agenda,setAgenda]=useState(null),[modulo,setModulo]=useState('agendas'),[erro,setErro]=useState('');
  useEffect(()=>{autenticacao.me().then(setUsuario).catch(()=>{}).finally(()=>setIniciando(false));const expirar=()=>{setUsuario(null);setEmpresa(null);setAgenda(null)};window.addEventListener('sessao-expirada',expirar);return()=>window.removeEventListener('sessao-expirada',expirar)},[]);
  async function sair(){setErro('');try{await autenticacao.sair();setUsuario(null);setEmpresa(null);setAgenda(null)}catch(e){setErro(e.message)}}
  if(iniciando)return <main className="carregamento" role="status">Abrindo seu espaço de trabalho…</main>;
  if(!usuario)return <Login onLogin={setUsuario}/>;
  return <div className="app"><aside className="sidebar"><div className="logo"><span className="marca">P↗</span><strong>ParaDecision</strong></div><p className="eyebrow">ESPAÇO DE TRABALHO</p><nav aria-label="Navegação principal"><button aria-current={!empresa?'page':undefined} onClick={()=>{setEmpresa(null);setAgenda(null)}}>Empresas</button>{empresa&&<><button aria-current={modulo==='agendas'?'page':undefined} onClick={()=>{setModulo('agendas');setAgenda(null)}}>Agendas</button>{empresa.podeGerenciar&&<button aria-current={modulo==='usuarios'?'page':undefined} onClick={()=>{setModulo('usuarios');setAgenda(null)}}>Usuários</button>}</>}</nav><div className="perfil"><strong>{usuario.nome}</strong><small>{usuario.login}</small><button onClick={sair}>Sair da conta</button></div></aside><div className="conteudo"><header><span>{empresa?.nome||'Suas organizações'}</span><span>Decisões em conjunto <i/></span></header><main><Aviso erro={erro}/>{!empresa?<Empresas onSelect={e=>{setEmpresa(e);setModulo('agendas')}}/>:modulo==='usuarios'?<Usuarios empresa={empresa}/>:agenda?<AgendaDetalhe id={agenda} empresa={empresa} usuario={usuario} voltar={()=>setAgenda(null)}/>:<Agendas empresa={empresa} onSelect={setAgenda}/>}</main><footer>ParaDecision · Zeen Storm</footer></div></div>
}
