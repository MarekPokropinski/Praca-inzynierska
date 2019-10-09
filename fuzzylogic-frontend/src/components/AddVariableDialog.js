import React, { useState } from "react";
import {
  Dialog,
  DialogTitle,
  TextField,
  DialogContent,
  DialogActions,
  Button
} from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles({
  dialog: {
    padding: "5px"
  }
});

export default function(props) {
  const { onSubmit, open, onClose } = props;
  const [name, setName] = useState("");
  const classes = useStyles();

  return (
    <Dialog className={classes.dialog} onClose={onClose} open={open}>
      <DialogTitle>Add new variable</DialogTitle>
      <DialogContent>
        <TextField
          label="variable name"
          value={name}
          onChange={event => setName(event.target.value)}
        />
      </DialogContent>
      <DialogActions>
        <Button variant="outlined" onClick={()=>onSubmit(name)} color="primary">
          Create
        </Button>
        <Button variant="outlined" onClick={onClose} color="secondary">
          Cancel
        </Button>
      </DialogActions>
    </Dialog>
  );
}
