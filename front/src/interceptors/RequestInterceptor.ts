import axios from "axios";


export function addTokenToRequestInterceptor (token: string) { 
  console.log("Hey from interceptor, setting token ", token)
  const id = axios.interceptors.request.use(config => {
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  });
  return id;
}

export function ejectInterceptor(id: number) {
  axios.interceptors.request.eject(id);
}
