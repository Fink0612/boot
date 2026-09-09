import {http} from '../compartilhado/api/http.js';
export const empresas={listar:()=>http('/empresas'),usuarios:id=>http(`/empresas/${id}/usuarios`)};
