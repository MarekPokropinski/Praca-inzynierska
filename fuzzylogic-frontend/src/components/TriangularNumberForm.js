import React from "react";
import { InputLabel, TextField } from "@material-ui/core";

// function safeParseInt(value) {
//   const parsed = parseInt(value)
//   if(isNaN(parsed)) {
//     return 0;
//   }
//   return parsed;
// }



export default function TriangularNumberForm(props) {
  const { onChange, number } = props;
  let ret = []
    for (let key in number) {
      if(key!=='type') {
        ret.push(
          <TextField
          value={number[key]}
          label={key}
          onChange={event => onChange({...number,[key]:event.target.value})}
          key={key}
        />
        )
      }
    }
    return ret;
}
