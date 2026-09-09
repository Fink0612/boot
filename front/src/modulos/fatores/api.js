import {http} from '../compartilhado/api/http.js';
export const fatores={listar:a=>http(`/agendas/${a}/fatores`),criar:(a,body)=>http(`/agendas/${a}/fatores`,{method:'POST',body}),editar:(id,body)=>http(`/fatores/${id}`,{method:'PUT',body})};
