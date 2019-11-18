export const fetchRules = systemId => ({
  type: "FETCH_RULES",
  payload: {
    request: {
      url: `systems/${systemId}/rules/`
    }
  }
});
