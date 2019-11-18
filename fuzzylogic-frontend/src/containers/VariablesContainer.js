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
    padding: "5px"
  },
  listItem: {
    border: "2px solid " + theme.palette.primary.light,
    borderRadius: "5px",
    marginBottom: "5px"
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

class VariablesContainer extends React.Component {
  constructor() {
    super();
    this.handleAddVariable = this.handleAddVariable.bind(this);
  }

  componentDidMount() {
    const { system } = this.props;
    if (!system) {
      return;
    }
    this.props.fetchVariables(system.id);
  }

  handleAddVariable(name, isInput) {
    const { system } = this.props;
    this.props.createVariable(system.id, name, isInput).then(() => {
      this.props.displayDialog(false);
    });
  }

  render() {
    const { classes, variables, system } = this.props;
    if (!variables || !system) {
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
              onClick={() => this.props.history.push(`${variable.id}/`)}
            >
              <ListItemText
                className={classes.text}
                primary={
                  (variable.input ? "Input: " : "Output: ") + variable.name
                }
                secondary={
                  variable.values.length === 0
                    ? ""
                    : "Values: " +
                      variable.values
                        .map(value => value.name)
                        .reduce((prev, current) => prev + ", " + current)
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
          checkboxText="is input"
        />
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    ...state.variables,
    system: state.systems.system
  };
};

const mapDispatchToProps = dispatch =>
  bindActionCreators(actionCreators, dispatch);

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withStyles(styles)(VariablesContainer));
