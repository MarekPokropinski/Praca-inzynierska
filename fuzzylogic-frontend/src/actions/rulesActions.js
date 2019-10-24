export const fetchRules = ()=>({
    type: "FETCH_RULES",
    payload: {
        request: {
          url: `/rules/`
        }
      }
})