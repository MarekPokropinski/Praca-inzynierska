import React, { useState } from "react";
import {
  Dialog,
  DialogTitle,
  TextField,
  DialogContent,
  DialogActions,
  Button,
  Checkbox,
  FormControlLabel,
  Select,
  FormControl,
  InputLabel,
  MenuItem,
  Grid
} from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import CSVReader from "react-csv-reader";

const useStyles = makeStyles({
  dialog: {
    padding: "5px"
  },
  csvReaderInput: {
    // width: "0.1px",
    // height: "0.1px",
    // opacity: 0,
    // overflow: "hidden",
    // position: "absolute",
    // zIndex: -1
    padding: "0 5px",
    width: "calc(100% - )"
  },
  csvReader: {
    margin: "0 auto",
    width: "100%",
    padding: "5px 0 10px 30px"
  },
  selectWrapper: {
    padding: "10px"
  },
  select: {
    minWidth: "250px"
  }
});

function onFileLoaded(data, callback) {
  const labels = data[0];
  const variablesCount = data[0].length;
  for (let i = 0; i < data.length; i++) {
    if (data[i].length !== variablesCount) {
      if (i !== data.length - 1 || data[i].length !== 1) {
        alert("Error while parsing csv file");
        console.log(i, data.length - 1);
        callback();
        return;
      }
      if (i === data.length - 1 && data[i].length === 1) {
        callback(labels, data.slice(1, data.length - 1));
        return;
      }
    }
  }
  callback(labels, data.slice(1));
}

const initialState = {
  name: "",
  page: 0,
  data: { labels: [] },
  output: "",
  fuzzyNumbersPerVariable: 3
};

export default function(props) {
  const { onSubmit, open, onClose, onLoadData } = props;
  const [name, setName] = useState(initialState.name);
  const [page, setPage] = useState(initialState.page);
  const [data, setData] = useState({ ...initialState.data });
  const [output, setOutput] = useState(initialState.output);
  const [fuzzyNumbers, setFuzzyNumbers] = useState(
    initialState.fuzzyNumbersPerVariable
  );
  const classes = useStyles();

  const page1 = (
    <>
      <DialogContent>
        <TextField
          label={"System name"}
          value={name}
          onChange={event => setName(event.target.value)}
        />
      </DialogContent>
      <DialogActions>
        <Button
          variant="outlined"
          onClick={() => onSubmit(name)}
          color="primary"
        >
          Create
        </Button>
        <Button variant="outlined" onClick={() => setPage(1)} color="primary">
          Create from csv
        </Button>
        <Button variant="outlined" onClick={onClose} color="secondary">
          Cancel
        </Button>
      </DialogActions>
    </>
  );

  const page2 = (
    <>
      <CSVReader
        label="load csv"
        cssClass={classes.csvReader}
        cssInputClass={classes.csvReaderInput}
        onFileLoaded={data =>
          onFileLoaded(data, (labels, data) => {
            if (labels && data) {
              setData({ labels, data });
              setPage(2);
            }
          })
        }
      />
    </>
  );

  const page3 = (
    <div className={classes.selectWrapper}>
      <Grid container>
        <Grid item xs={12}>
          <FormControl>
            <InputLabel>Output variable</InputLabel>
            <Select
              className={classes.select}
              onChange={event => setOutput(event.target.value)}
              value={output}
            >
              {data.labels.map(label => (
                <MenuItem key={label} value={label}>
                  {label}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </Grid>
        <Grid item xs={12}>
          <TextField
            label="Fuzzy numbers per variable"
            value={fuzzyNumbers}
            onChange={event => setFuzzyNumbers(event.target.value)}
            inputProps={{
              step: 1,
              type: "number"
            }}
          />
        </Grid>
        <Grid item>
          <Button
            disabled={data.labels.find(val => val === output) === undefined}
            onClick={() =>
              onLoadData(
                name,
                data.labels,
                data.data,
                fuzzyNumbers,
                data.labels.findIndex(val => val === output)
              )
            }
          >
            Confirm
          </Button>
        </Grid>
      </Grid>
    </div>
  );

  const pages = [page1, page2, page3];

  return (
    <Dialog
      className={classes.dialog}
      onClose={() => {
        onClose();
        setName(initialState.name);
        setPage(initialState.page);
        setData({ ...initialState.data });
        setOutput(initialState.output);
      }}
      open={open}
    >
      <DialogTitle>{"Create new fuzzy logic system"}</DialogTitle>
      {pages[page]}
    </Dialog>
  );
}
