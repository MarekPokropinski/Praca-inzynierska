export default function value(state = { data: {} }, action) {
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
    default:
      return state;
  }
}
