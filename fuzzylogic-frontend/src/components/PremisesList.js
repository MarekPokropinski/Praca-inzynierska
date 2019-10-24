import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import {
  Typography,
  Button,
  FormControl,
  Select,
  MenuItem
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
  }
}));

export default function PremisesList(props) {
  const classes = useStyles();
  const { list, variables, onAdd } = props;
  const menuItemsVariables = variables.map(variable => (
    <MenuItem key={variable.name} value={variable.name}>{variable.name}</MenuItem>
  ));
  return (
    <>
      <Typography className={classes.typhography} color="textPrimary">
        {props.title}
      </Typography>
      <FormControl>
        {list.map((variable,key) => (
          <>
            <Select key={key} value={variable.name}>{menuItemsVariables}</Select>
          </>
        ))}
      </FormControl>

      <div className={classes.buttonContainer}>
        <Button onClick={onAdd} className={classes.button} variant="contained">
          add
        </Button>
      </div>
    </>
  );
}
