export const fetchRuleDetails = id => ({
  type: "FETCH_RULE_DETAILS",
  payload: {
    request: {
      url: `/rules/${id}`
    }
  }
});

export const clearRuleDetails = () => ({
  type: "CLEAR_RULE_DETAILS"
});

export const modifyPremise = (key, variableName, valueName) => ({
  type: "MODIFY_PREMISE",
  payload: {
    key,
    variableName,
    valueName
  }
});

export const addPremise = () => ({
  type: "ADD_PREMISE"
});

export const modifyConclusion = (key, variableName, valueName) => ({
  type: "MODIFY_CONCLUSION",
  payload: {
    key,
    variableName,
    valueName
  }
});

export const addConclusion = () => ({
  type: "ADD_CONCLUSION"
});

export const createRule = (premisesIds, conclusionIds) => ({
  type: "CREATE_RULE",
  payload: {
    request: {
      url: "/rules/",
      method: "POST",
      data: {
        premises: premisesIds,
        conclusions: conclusionIds
      }
    }
  }
});

export const updateRule = (id, premisesIds, conclusionIds) => ({
  type: "UPDATE_RULE",
  payload: {
    request: {
      url: `/rules/${id}`,
      method: "PUT",
      data: {
        premises: premisesIds,
        conclusions: conclusionIds
      }
    }
  }
});
