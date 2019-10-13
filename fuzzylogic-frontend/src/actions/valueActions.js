export const fetchValueDetails = id => ({
    type: "FETCH_VALUE",
    payload: {
        request : {
            url:`/values/${id}`
        }
    }
})