export default function ruleDetails(state = { rule: null }, action) {
  switch (action.type) {
    case "FETCH_RULE_DETAILS":
      return {
        ...state,
        rule: null
      };
    case "FETCH_RULE_DETAILS_SUCCESS":
      return {
        ...state,
        rule: action.payload.data
      };
    case "CLEAR_RULE_DETAILS":
      return {
        rule: {
          premises: [],
          conclusions: []
        }
      };
    case "MODIFY_PREMISE": {
      const premises = [...state.rule.premises];
      const { key, ...premise } = action.payload;
      premises[action.payload.key] = premise;
      return {
        ...state,
        rule: {
          ...state.rule,
          premises
        }
      };
    }
    case "ADD_PREMISE":
      return {
        ...state,
        rule: {
          ...state.rule,
          premises: [
            ...state.rule.premises,
            { variableName: "", valueName: "" }
          ]
        }
      };

    case "MODIFY_CONCLUSION": {
      const conclusions = [...state.rule.conclusions];
      const { key, ...conclusion } = action.payload;
      conclusions[action.payload.key] = conclusion;
      return {
        ...state,
        rule: {
          ...state.rule,
          conclusions
        }
      };
    }
    case "ADD_CONCLUSION":
      return {
        ...state,
        rule: {
          ...state.rule,
          conclusions: [
            ...state.rule.conclusions,
            { variableName: "", valueName: "" }
          ]
        }
      };
    default:
      return state;
  }
}
