export const fetchRules = systemId => ({
  type: "FETCH_RULES",
  payload: {
    request: {
      url: `systems/${systemId}/rules/`
    }
  }
});

export const deleteRule = id => ({
  type: "DELETE_RULE",
  payload: {
    request: {
      url: `/rules/${id}`,
      method: "DELETE"
    }
  }
});
