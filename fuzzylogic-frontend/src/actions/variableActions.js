export const fetchVariableDetails = id => ({
  type: "FETCH_VARIABLE",
  payload: {
    request: {
      url: `/variables/${id}`
    }
  }
});

export const editVariable = (id, name, isInput) => ({
  type: "EDIT_VARIABLE",
  payload: {
    request: {
      url: `/variables/${id}`,
      method: "PUT",
      data: { name, input: isInput }
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

export const setDisplayEditVariableDialog = display => ({
  type: "DISPLAY_EDIT_VARIABLE_DIALOG",
  payload: display
});

export const fetchPlot = id => ({
  type: "FETCH_VARIABLE_PLOT",
  payload: {
    request: {
      url: `/variables/${id}/plot`
    }
  }
});

export const deleteValue = id => ({
  type: "DELETE_VALUE",
  payload: {
    request: {
      url: `/values/${id}`,
      method: "DELETE"
    }
  }
});
