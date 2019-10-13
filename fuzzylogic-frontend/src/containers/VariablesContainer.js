import React from "react";
import { List, ListItem, ListItemText, Fab } from "@material-ui/core";
import { withStyles } from "@material-ui/core/styles";
import AddIcon from "@material-ui/icons/Add";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import CreateObjectDialog from "../components/CreateObjectDialog";
import * as actionCreators from "../actions/variablesActions";

const styles = theme => ({
  root: { width: "100%" },
  list: {
    width: "100%",
    padding : "5px"
  },
  listItem: {
    border: "2px solid " + theme.palette.primary.light,
    borderRadius: "5px",
    marginBottom : "5px"
  },
  text: {
    color: theme.palette.text.primary
  },
  fab: {
    position: "absolute",
    bottom: theme.spacing(2),
    right: theme.spacing(3)
  }
});

class MainContainer extends React.Component {
  constructor(){
    super()
    this.handleAddVariable =this.handleAddVariable.bind(this)
  }

  componentDidMount() {
    this.props.fetchVariables();
  }

  handleAddVariable(name){
    this.props.createVariable(name).then(()=>{
      this.props.displayDialog(false)
    })
  }

  render() {
    const { classes, variables } = this.props;
    if (!variables) {
      return null;
    }
    return (
      <div className={classes.root}>
        <List className={classes.list} component="nav">
          {variables.map((variable, key) => (
            <ListItem
              className={classes.listItem}
              button
              key={key}
              onClick={() =>
                this.props.history.push(`${variable.id}/`)
              }
            >
              <ListItemText
                className={classes.text}
                primary={variable.name}
                secondary={
                  variable.values.length === 0
                    ? ""
                    : "Values: " +
                      variable.values.reduce(
                        (prev, current) => prev + ", " + current
                      )
                }
              />
            </ListItem>
          ))}
        </List>
        <Fab
          className={classes.fab}
          color="primary"
          onClick={() => this.props.displayDialog(true)}
        >
          <AddIcon />
        </Fab>
        <CreateObjectDialog
          open={this.props.isDialogOpen}
          onClose={() => this.props.displayDialog(false)}
          onSubmit={this.handleAddVariable}
          title="Create linguistic variable"
          text="variable name"
        />
      </div>
    );
  }
}

const mapStateToProps = state => {
  return state.variables;
};

const mapDispatchToProps = dispatch =>
  bindActionCreators(actionCreators, dispatch);

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withStyles(styles)(MainContainer));
