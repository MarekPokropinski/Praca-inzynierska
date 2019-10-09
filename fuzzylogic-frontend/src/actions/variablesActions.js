export const fetchVariables = () => ({
  type: "FETCH_VARIABLES",
  payload: {
    request: {
      url: "/variables/"
    }
  }
});

export const createVariable = name => ({
  type: "CREATE_VARIABLE",
  payload: {
    request: {
      url: "/variables/",
      method: "POST",
      data : name
    }
  }
});

export const displayDialog = display => ({
  type: "DISPLAY_DIALOG",
  payload: display
});
