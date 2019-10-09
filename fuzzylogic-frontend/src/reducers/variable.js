export default function variable(state = {}, action) {
    switch (action.type) {
      case "FETCH_VARIABLE_SUCCESS":
        return action.payload.data
      default:
        return state;
    }
  }
  