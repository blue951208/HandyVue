import axios from "axios";

export const getMenuList = () => {
    console.log('getMenuList');
    return axios.get('http://localhost:8080/api/cmMenuMst/list');
}