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
