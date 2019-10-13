export const fetchVariableDetails = id => ({
  type: "FETCH_VARIABLE",
  payload: {
    request: {
      url: `/variables/${id}`
    }
  }
});

export const createValue = (variableId, valueName) => ({
  type: "CREATE_VALUE",
  payload: {
    request: {
      method: "POST",
      url: `/values/`,
      data: { variableId, name: valueName }
    }
  }
});

export const displayAddValueDialog = display => ({
  type: "DISPLAY_CREATE_VALUE_DIALOG",
  payload: display
});
