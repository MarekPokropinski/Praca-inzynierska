export default function variables(state = { isDialogOpen: false }, action) {
  switch (action.type) {
    case "FETCH_VARIABLES_SUCCESS":
      return { ...state, variables: action.payload.data };
    case "CREATE_VARIABLE_SUCCESS":
      return { ...state, variables: [...state.variables, action.payload.data] };
    case "DISPLAY_DIALOG":
      return { ...state, isDialogOpen: action.payload };
    default:
      return state;
  }
}
