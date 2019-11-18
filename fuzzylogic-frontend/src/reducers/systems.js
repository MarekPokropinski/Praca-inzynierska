export default function rules(state = { data: null, system: null }, action) {
  switch (action.type) {
    case "FETCH_SYSTEMS_SUCCESS":
      return {
        ...state,
        systems: action.payload.data
      };
    case "SELECT_SYSTEM":
      return {
        ...state,
        system: null
      };
    case "SELECT_SYSTEM_SUCCESS":
      return {
        ...state,
        system: action.payload.data
      };
    default:
      return state;
  }
}
