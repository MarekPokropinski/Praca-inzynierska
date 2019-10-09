export const fetchVariableDetails = id => ({
    type: "FETCH_VARIABLE",
    payload: {
        request : {
            url:`/variables/${id}`
        }
    }
})