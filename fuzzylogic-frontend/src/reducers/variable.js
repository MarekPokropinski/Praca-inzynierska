export default function variable(
  state = { data: {}, displayCreateValueDialog: false },
  action
) {
  switch (action.type) {
    case "FETCH_VARIABLE_SUCCESS":
      return {...state, data: action.payload.data};
    case "DISPLAY_CREATE_VALUE_DIALOG":
      return { ...state, displayCreateValueDialog: action.payload };
    default:
      return state;
  }
}
