import React from "react";
import { List, ListItem, ListItemText, Fab } from "@material-ui/core";
import { withStyles } from "@material-ui/core/styles";
import AddIcon from '@material-ui/icons/Add';

const styles = theme=> ({
  root:{width:"100%"},
  list: {
    width:"100%"
  }, 
  text: {
    color: theme.palette.text.primary
  },
  fab: {
    position: 'absolute',
    bottom: theme.spacing(2),
    right: theme.spacing(3),
  }
});

let variablesMock = [{ id: 1, name: "Opady", values: ["małe, duże"] }];

for(let i=0;i<3;i++){
    variablesMock.push(variablesMock[0])
}

class MainContainer extends React.Component {
  constructor() {
    super();
    this.state = {};
  }

  render() {
    const {classes} = this.props
    return (
      <div className={classes.root}>
        <List className={classes.list} component="nav">
          {variablesMock.map((variable, key) => (
            <ListItem button key = {key} onClick={()=>this.props.history.push(`${this.props.location.pathname}/${variable.id}`)}>
              <ListItemText
                className={classes.text}
                primary={variable.name}
                secondary={"Values: "+variable.values
                  .reduce(
                    (prev, current) => prev + ", " + current
                  )}
              />
            </ListItem>
          ))}
        </List>
        <Fab className={classes.fab} color='primary'>
          <AddIcon/>
        </Fab>
      </div>
    );
  }
}
export default withStyles(styles)(MainContainer);
