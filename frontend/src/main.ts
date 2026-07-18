import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from "./router/index.ts";

import store from "./store/inflearn/index.js";

const app = createApp(App)
app.use(router);
app.use(store);
app.mount('#app')