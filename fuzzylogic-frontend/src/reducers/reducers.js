import { combineReducers } from "redux";
import variables from "./variables";
import variable from "./variable";
import value from "./value";
import rules from "./rules";
import ruleDetails from "./ruleDetails";

export default combineReducers({
  variables,
  variable,
  value,
  rules,
  ruleDetails
});
