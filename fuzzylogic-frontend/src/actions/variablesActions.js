export const fetchVariables = systemId => ({
  type: "FETCH_VARIABLES",
  payload: {
    request: {
      url: `systems/${systemId}/variables/`
    }
  }
});

export const createVariable = (systemId, name, isInput) => ({
  type: "CREATE_VARIABLE",
  payload: {
    request: {
      url: `systems/${systemId}/variables/`,
      method: "POST",
      data: { name, input: isInput }
    }
  }
});

export const displayDialog = display => ({
  type: "DISPLAY_DIALOG",
  payload: display
});
