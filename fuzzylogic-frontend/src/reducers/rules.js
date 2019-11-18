export default function rules(state = { data: {} }, action) {
  switch (action.type) {
    case "FETCH_RULES_SUCCESS":
      return {
        ...state,
        rules: action.payload.data,
      };
    default:
      return state;
  }
}
