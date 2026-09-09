import {useEffect,useState} from 'react';
export function useConsulta(carregar,deps=[]) {
  const [dados,setDados]=useState(null),[erro,setErro]=useState(''),[versao,setVersao]=useState(0);
  useEffect(()=>{let ativo=true;setDados(null);setErro('');carregar().then(d=>{if(ativo)setDados(d)}).catch(e=>{if(ativo)setErro(e.message)});return()=>{ativo=false}},[...deps,versao]);
  return {dados,erro,recarregar:()=>setVersao(v=>v+1)};
}
export function Aviso({erro}){return erro?<div className="aviso" role="alert">{erro}</div>:null}
export function Consulta({consulta,children}){return consulta.erro?<><Aviso erro={consulta.erro}/><button onClick={consulta.recarregar}>Tentar novamente</button></>:consulta.dados===null?<p role="status">Carregando…</p>:children(consulta.dados)}
export function Formulario({onSubmit,children,texto='Salvar',onCancel}) {
  const [erro,setErro]=useState(''),[ocupado,setOcupado]=useState(false);
  async function enviar(event){event.preventDefault();setErro('');setOcupado(true);try{await onSubmit(Object.fromEntries(new FormData(event.currentTarget)))}catch(e){setErro(e.message)}finally{setOcupado(false)}}
  return <form onSubmit={enviar}><Aviso erro={erro}/><fieldset disabled={ocupado}>{children}<div className="acoes"><button className="primario" type="submit">{ocupado?'Salvando…':texto}</button>{onCancel&&<button type="button" onClick={onCancel}>Cancelar</button>}</div></fieldset></form>
}
export function Campo({label,name,type='text',...props}){return <label className="campo">{label}{type==='textarea'?<textarea name={name} {...props}/>:<input type={type} name={name} {...props}/>}</label>}
export function Check({label,name,defaultChecked=false}){return <label className="check"><input type="checkbox" name={name} defaultChecked={defaultChecked}/>{label}</label>}
export const statusAgenda={0:'Aguardando encaminhamento',1:'Aguardando fatores',2:'Recebendo pareceres',9:'Encerrada'};
export function Status({valor}){return <span className={`status status-${valor}`}>{statusAgenda[valor]||'Indefinido'}</span>}
