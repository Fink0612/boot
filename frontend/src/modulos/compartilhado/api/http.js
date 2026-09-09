const API = import.meta.env.VITE_API_URL || '';

export async function http(path, {method = 'GET', body, signal} = {}) {
  const url = `${API}/api${path}`;
  const opciones = {
    method,
    signal,
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    },
    body: body === undefined ? undefined : JSON.stringify(body)
  };

  let response;
  try {
    response = await fetch(url, opciones);
  } catch (error) {
    if (error.name === 'AbortError') throw error;
    throw new Error('Não foi possível conectar à API. Confira se o backend está iniciado e VITE_API_URL.');
  }

  if (!response.ok) {
    const error = await response.json().catch(() => ({}));
    if (response.status === 401 && !path.endsWith('/login')) {
      window.dispatchEvent(new Event('sessao-expirada'));
    }
    throw new Error(error.detail || `Não foi possível concluir a operação (${response.status}).`);
  }

  return response.status === 204 ? null : response.json();
}