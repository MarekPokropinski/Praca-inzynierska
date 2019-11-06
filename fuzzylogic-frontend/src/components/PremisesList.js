import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import {
  Typography,
  Button,
  FormControl,
  Select,
  MenuItem,
  Grid,
  InputLabel
} from "@material-ui/core";

const useStyles = makeStyles(theme => ({
  typhography: {
    // color: theme.palette.text.primary
    backgroundColor: theme.palette.background.default,
    textAlign: "center"
  },
  buttonContainer: {
    margin: "10px auto",
    width: "70px"
  },
  button: {
    width: "100%"
  },
  span: {
    color: theme.palette.text.primary,
    padding: "1em 0",
    width: "100%",
    textAlign: "center",
    height: "100%"
  },
  select: {
    width: "100%"
  },
  formControl: {
    width: "100%"
  }
}));

function Premise(props) {
  const {
    selectedVariableName,
    selectedValueName,
    variables,
    onChange
  } = props;
  const classes = useStyles();
  const [selectedVariable] = variables.filter(
    variable => variable.name === selectedVariableName
  );

  const variableValues = selectedVariable ? selectedVariable.values : [];

  const menuItemsVariables = variables.map(variable => (
    <MenuItem key={variable.name} value={variable.name}>
      {variable.name}
    </MenuItem>
  ));
  console.log(variableValues);
  const menuItemValues = variableValues.map(value => (
    <MenuItem key={value.name} value={value.name}>
      {value.name}
    </MenuItem>
  ));

  return (
    <>
      <Grid container>
        <Grid item xs={5}>
          <FormControl className={classes.formControl}>
            <InputLabel>Variable</InputLabel>
            <Select
              onChange={event =>
                onChange(event.target.value, selectedValueName)
              }
              className={classes.select}
              value={selectedVariableName}
            >
              {menuItemsVariables}
            </Select>
          </FormControl>
        </Grid>
        <Grid item xs={2}>
          <div className={classes.span}>=</div>
        </Grid>
        <Grid item xs={5}>
          <FormControl className={classes.formControl}>
            <InputLabel>Value</InputLabel>
            <Select
              onChange={event =>
                onChange(selectedVariableName, event.target.value)
              }
              className={classes.select}
              value={selectedValueName}
            >
              {menuItemValues}
            </Select>
          </FormControl>
        </Grid>
      </Grid>
    </>
  );
}

export default function PremisesList(props) {
  const classes = useStyles();
  const { list, variables, onAdd, onChange } = props;

  return (
    <>
      <Typography className={classes.typhography} color="textPrimary">
        {props.title}
      </Typography>

      {list.map((premise, key) => (
        <Premise
          key={key}
          selectedVariableName={premise.variableName}
          selectedValueName={premise.valueName}
          variables={variables}
          onChange={(variableName, valueName) =>
            onChange(key, variableName, valueName)
          }
        />
      ))}

      <div className={classes.buttonContainer}>
        <Button onClick={onAdd} className={classes.button} variant="contained">
          add
        </Button>
      </div>
    </>
  );
}
