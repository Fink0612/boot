export async function http(path, {method='GET',body,signal}={}) {
  let response;
  try {response=await fetch(`/api${path}`,{method,signal,credentials:'same-origin',headers:{'Content-Type':'application/json','X-Requested-With':'XMLHttpRequest'},body:body===undefined?undefined:JSON.stringify(body)});}
  catch(error) {if(error.name==='AbortError')throw error;throw new Error('Não foi possível conectar à API. Confira se o backend está iniciado.');}
  if(!response.ok){const error=await response.json().catch(()=>({}));if(response.status===401&&!path.endsWith('/login'))window.dispatchEvent(new Event('sessao-expirada'));throw new Error(error.detail||`Não foi possível concluir a operação (${response.status}).`);}
  return response.status===204?null:response.json();
}
