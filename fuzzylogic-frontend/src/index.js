import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import * as serviceWorker from "./serviceWorker";
import { createStore, applyMiddleware } from "redux";
import { Provider } from "react-redux";
import axios from "axios";
import axiosMiddleware from "redux-axios-middleware";
import reducers from "./reducers/reducers";
// import variables from "./reducers/variables"

const client = axios.create({
  baseURL: "http://localhost:8080/",
  responseType: "json",
  headers: {
    'Content-Type':'application/json'
  }
});
const store = createStore(reducers, applyMiddleware(axiosMiddleware(client)));

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById("root")
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
