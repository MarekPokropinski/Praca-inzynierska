export const fetchValueDetails = id => ({
  type: "FETCH_VALUE",
  payload: {
    request: {
      url: `/values/${id}`
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
