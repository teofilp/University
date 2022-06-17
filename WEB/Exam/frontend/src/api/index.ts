import axios from 'axios';

const api = axios.create({
  baseURL: 'http://192.168.64.2/web-exam/',
});

export default api;
