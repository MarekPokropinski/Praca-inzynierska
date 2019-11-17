import React, { useState } from "react";
import { Typography, Slider } from "@material-ui/core";
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

export default function EngineInputVariable(props) {
  const {
    name,
    plots,
    fuzzyValueText,
    memberships,
    value,
    range,
    onChange
  } = props;

  const [sliderValue, setSliderValue] = useState(value);

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
    x: memberships.map(_ => value),
    y: memberships,
    type: "scatter",
    mode: "points+markers",
    line: { shape: plots.shape }
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
          <Slider
            // defaultValue={(range.first + range.second) / 2}
            min={range.first}
            max={range.second}
            value={sliderValue}
            onChange={(_event, newValue) => {
              onChange(newValue);
              setSliderValue(newValue);
            }}
          />
        </div>
        <Typography color="textPrimary">μ(x)={fuzzyValueText}</Typography>
      </div>
    </div>
  );
}
