export const fetchSystems = () => ({
  type: "FETCH_SYSTEMS",
  payload: {
    request: {
      url: "/systems/"
    }
  }
});

export const selectSystem = id => ({
  type: "SELECT_SYSTEM",
  payload: {
    request: {
      url: `/systems/${id}`
    }
  }
});

export const createSystem = name => ({
  type: "CREATE_SYSTEM",
  payload: {
    request: {
      url: "/systems/",
      method: "POST",
      data: name
    }
  }
});

export const generateSystem = system => ({
  type: "GENERATE_SYSTEM",
  payload: {
    request: {
      url: "/systems/learn",
      method: "POST",
      data: system
    }
  }
});

export const updateSystem = (id, data) => ({
  type: "UPDATE_SYSTEM",
  payload: {
    request: {
      method: "PATCH",
      url: `/systems/${id}`,
      data
    }
  }
});

export const deleteSystem = id => ({
  type: "DELETE_SYSTEM",
  payload: {
    request: {
      url: `/systems/${id}`,
      method: "DELETE"
    }
  }
});

export const displayCreateSystemDialog = display => ({
  type: "DISPLAY_CREATE_SYSTEM_DIALOG",
  payload: display
});
