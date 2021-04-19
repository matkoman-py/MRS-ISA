import axios from 'axios'

const VueAxios = axios.create({
  baseURL: 'http://web.local/api/',
  timeout: 10000
})

VueAxios.interceptors.request.use(config => {
    config.headers.Authorization = localStorage.getItem('token') ? `Bearer ${localStorage.getItem('token')}` : '';
    console.log(config.headers.Authorization)
    return config;
})

export default VueAxios