export const process = (inputs, systemId) => ({
  type: "ENGINE_PROCESS",
  payload: {
    request: {
      method: "POST",
      url: `/system/${systemId}/process`,
      data: inputs
    }
  }
});

export const queueVariableChange = (name, newValue) => ({
  type: "ENGINE_VARIABLE_CHANGE",
  payload: {
    name,
    newValue
  }
});

export const clearQueue = () => ({
  type: "ENGINE_CLEAR_QUEUE"
});
