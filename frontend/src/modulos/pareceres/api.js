import {http} from '../compartilhado/api/http.js';
export const pareceres={listar:a=>http(`/agendas/${a}/pareceres/meus`),salvar:(f,body)=>http(`/fatores/${f}/pareceres/meu`,{method:'PUT',body})};
