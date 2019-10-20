import React from "react";
import Plot from "react-plotly.js";
import { makeStyles } from "@material-ui/core";

const useStyles = makeStyles({
  plot: {
    width: "100%"
  }
});

export default function VariableValuesPlot(props) {
  const classes = useStyles();
  const { values, name } = props;
  const data = values.map(value => ({
    x: value.x,
    y: value.y,
    type: "scatter",
    mode: "lines+points",
    name: value.name,
    line : {shape: value.shape}
  }));
  return (
    <Plot
      className={classes.plot}
      data={data}
      useResizeHandler
      layout={{ title: name, autosize: true, showlegend: true }}
    />
  );
}
