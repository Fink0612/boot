import {http} from '../compartilhado/api/http.js';
export const autenticacao={login:body=>http('/autenticacao/login',{method:'POST',body}),me:()=>http('/autenticacao/me'),sair:()=>http('/autenticacao/logout',{method:'POST'})};
