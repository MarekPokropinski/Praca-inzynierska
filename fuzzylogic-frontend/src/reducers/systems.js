export default function rules(
  state = { data: null, system: null, displayDialog: false },
  action
) {
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
    case "GENERATE_SYSTEM_SUCCESS":
    case "CREATE_SYSTEM_SUCCESS":
      return {
        ...state,
        system: action.payload.data.id
      };
    case "UPDATE_SYSTEM_SUCCESS":
      return {
        ...state,
        system: action.payload.data
      };
    case "DISPLAY_CREATE_SYSTEM_DIALOG":
      return {
        ...state,
        displayDialog: action.payload
      };
    default:
      return state;
  }
}
