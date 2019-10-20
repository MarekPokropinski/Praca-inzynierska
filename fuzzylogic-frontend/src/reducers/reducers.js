import { combineReducers } from "redux";
import variables from "./variables";
import variable from "./variable";
import value from "./value";

export default combineReducers({
  variables,
  variable,
  value
});
