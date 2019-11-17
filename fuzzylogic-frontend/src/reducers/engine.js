export default function engine(
  state = { busy: false, changes: new Map() },
  action
) {
  switch (action.type) {
    case "ENGINE_PROCESS":
      return {
        ...state,
        busy: true,
        changes: new Map()
      };
    case "ENGINE_PROCESS_SUCCESS":
      return {
        ...state,
        data: action.payload.data,
        busy: false
      };
    case "ENGINE_VARIABLE_CHANGE":
      const changes = new Map(state.changes);
      changes.set(action.payload.name, action.payload.newValue);
      return {
        ...state,
        changes
      };
    default:
      return state;
  }
}
