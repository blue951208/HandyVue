import axios from "axios";

const isProd = import.meta.env.PROD;
axios.defaults.baseURL = isProd
    ? 'https://handyvue.onrender.com/'
    : 'http://localhost:8080';

export const getMenuList = () => {
    console.log('getMenuList');
    return axios.get('/api/cmMenuMst/list');
}