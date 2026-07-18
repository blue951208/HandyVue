import axios from "@/api/axios";

axios.defaults.baseURL = import.meta.env.VITE_API_URL;

export const getMenuList = () => {
    console.log('getMenuList');
    return axios.get('/api/cmMenuMst/list');
}