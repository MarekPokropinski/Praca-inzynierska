import React from "react";
import { Typography, Slider, Input } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import Plot from "react-plotly.js";

const useStyles = makeStyles({
  root: {
    width: "100%",
    padding: "10px 10px"
  },
  container: {
    border: "2px solid white",
    textAlign: "center"
  },
  plot: {
    width: "100%",
    height: "300px"
  },
  slider: {
    padding: "0 20px"
  }
});

export default function EngineOutputVariable(props) {
  const { name, plots, fuzzyValueText, value, range, fuzzyOutputPlot } = props;

  const classes = useStyles();
  const data = plots.map(plot => ({
    x: plot.x,
    y: plot.y,
    type: "scatter",
    mode: "lines+points",
    name: plot.name,
    line: { shape: plots.shape }
  }));
  data.push({
    x: fuzzyOutputPlot.x,
    y: fuzzyOutputPlot.y,
    type: "scatter",
    fill: "tozeroy",
    mode: "",
    name: fuzzyOutputPlot.name,
    line: { shape: plots.shape },
    marker: {
      color: "rgb(0,255,0)"
    }
  });

  return (
    <div className={classes.root}>
      <div className={classes.container}>
        <Typography color="textPrimary">{name}</Typography>
        <Plot
          className={classes.plot}
          data={data}
          useResizeHandler
          layout={{
            autosize: true,
            showlegend: false,
            yaxis: {
              automargin: true
            },
            xaxis: {
              automargin: true
            },
            margin: {
              t: 5,
              l: 5,
              r: 5,
              b: 5
            }
          }}
        />
        <div className={classes.slider}>
          <Slider min={range.first} max={range.second} value={value} />
        </div>
        <Input
          value={value}
          disabled
          inputProps={{
            step: 1,
            min: range.first,
            max: range.second,
            type: "number"
          }}
        />
        <Typography color="textPrimary">μ(x)={fuzzyValueText}</Typography>
      </div>
    </div>
  );
}
