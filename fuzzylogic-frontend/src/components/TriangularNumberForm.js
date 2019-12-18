import React from "react";
import { TextField } from "@material-ui/core";

export default function TriangularNumberForm(props) {
  const { onChange, number } = props;
  let ret = [];
  for (let key in number) {
    if (key !== "type") {
      ret.push(
        <TextField
          value={number[key]}
          label={key}
          onChange={event => onChange({ ...number, [key]: event.target.value })}
          key={key}
          inputProps={{
            step: 1,
            type: "number"
          }}
        />
      );
    }
  }
  return ret;
}
