import React, { useState } from "react";
import {
  Dialog,
  DialogTitle,
  TextField,
  DialogContent,
  DialogActions,
  Button,
  Checkbox,
  FormControlLabel
} from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles({
  dialog: {
    padding: "5px"
  }
});

export default function(props) {
  const { onSubmit, open, onClose, title, text, checkboxText } = props;
  const [state, setState] = useState({ name: "", checked: false });
  const classes = useStyles();

  return (
    <Dialog className={classes.dialog} onClose={onClose} open={open}>
      <DialogTitle>{title}</DialogTitle>
      <DialogContent>
        <TextField
          label={text}
          value={state.name}
          onChange={event => setState({ ...state, name: event.target.value })}
        />
        {checkboxText !== undefined ? (
          <FormControlLabel
            control={
              <Checkbox
                checked={state.checked}
                onChange={event =>
                  setState({ ...state, checked: event.target.checked })
                }
              />
            }
            label={checkboxText}
          />
        ) : null}
      </DialogContent>
      <DialogActions>
        <Button
          variant="outlined"
          onClick={() => onSubmit(state.name, state.checked)}
          color="primary"
        >
          Save
        </Button>
        <Button variant="outlined" onClick={onClose} color="secondary">
          Cancel
        </Button>
      </DialogActions>
    </Dialog>
  );
}
