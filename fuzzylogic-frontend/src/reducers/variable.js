export default function variable(
  state = { data: {}, displayCreateValueDialog: false },
  action
) {
  switch (action.type) {
    case "FETCH_VARIABLE_SUCCESS":
      return { ...state, data: action.payload.data };
    case "CREATE_VALUE_SUCCESS":
      const values = [...state.data.values, action.payload.data]
      return {...state, data: {...state.data, values}}
    case "DISPLAY_CREATE_VALUE_DIALOG":
      return { ...state, displayCreateValueDialog: action.payload };
    case "FETCH_VARIABLE_PLOT_SUCCESS":
      return { ...state, plot: action.payload.data };
    default:
      return state;
  }
}
