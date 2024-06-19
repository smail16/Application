import axios from 'axios';

const axiosInstanceBackEnd = axios.create({
    baseURL: '/api',
});

export default axiosInstanceBackEnd;
