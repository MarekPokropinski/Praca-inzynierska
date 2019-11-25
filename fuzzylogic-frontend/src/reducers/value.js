export default function value(
  state = { data: {}, displayEditValueDialog: false },
  action
) {
  switch (action.type) {
    case "FETCH_VALUE_SUCCESS":
      return {
        ...state,
        data: action.payload.data,
        newValue: action.payload.data
      };

    case "SET_TYPE":
      return {
        ...state,
        newValue: {
          ...state.newValue,
          number: { ...state.newValue.number, type: action.payload }
        }
      };
    case "SET_PARAMS":
      return {
        ...state,
        newValue: {
          ...state.newValue,
          number: { ...state.newValue.number, ...action.payload }
        }
      };
    case "DISPLAY_EDIT_VALUE_DIALOG":
      return {
        ...state,
        displayEditValueDialog: action.payload
      };
    default:
      return state;
  }
}
