import {http} from '../compartilhado/api/http.js';
export const usuarios={criar:(e,body)=>http(`/empresas/${e}/usuarios`,{method:'POST',body}),editar:(e,id,body)=>http(`/empresas/${e}/usuarios/${id}`,{method:'PUT',body})};
