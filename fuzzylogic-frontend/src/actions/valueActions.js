export const fetchValueDetails = id => ({
  type: "FETCH_VALUE",
  payload: {
    request: {
      url: `/values/${id}`
    }
  }
});

export const updateValue = (id, name) => ({
  type: "UPDATE_VALUE",
  payload: {
    request: {
      method: "PUT",
      url: `/values/${id}`,
      data: name
    }
  }
});

export const setType = type => ({
  type: "SET_TYPE",
  payload: type
});

export const setParams = params => ({
  type: "SET_PARAMS",
  payload: params
});

export const UpdateNumber = (id, type, parameters) => ({
  type: "UPDATE_NUMBER",
  payload: {
    request: {
      method: "PUT",
      url: `/values/${id}/number?type=${type}`,
      data: parameters
    }
  }
});

export const setDisplayEditValueDialog = display => ({
  type: "DISPLAY_EDIT_VALUE_DIALOG",
  payload: display
});
