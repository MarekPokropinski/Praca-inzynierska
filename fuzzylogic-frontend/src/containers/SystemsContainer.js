import React from "react";
import {
  List,
  ListItem,
  ListItemText,
  Fab,
  ListItemSecondaryAction,
  Button
} from "@material-ui/core";
import { withStyles } from "@material-ui/core/styles";
import AddIcon from "@material-ui/icons/Add";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import * as actionCreators from "../actions/systemsActions";
import CreateSystemDialog from "../components/CreateSystemDialog";

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

class RulesContainer extends React.Component {
  componentDidMount() {
    this.props.fetchSystems();
  }

  handleClickDelete(id) {
    const { fetchSystems, deleteSystem } = this.props;
    deleteSystem(id).then(() => fetchSystems());
  }

  handleCreateSystem(name) {
    const { createSystem, fetchSystems } = this.props;
    createSystem(name).then(() => fetchSystems());
  }

  handleGenerateSystem(
    name,
    variablesNames,
    data,
    fuzzyNumbersPerVariable,
    output
  ) {
    const { generateSystem, fetchSystems } = this.props;
    generateSystem({
      name,
      variablesNames,
      data,
      fuzzyNumbersPerVariable,
      output
    }).then(() => fetchSystems());
  }

  render() {
    const {
      classes,
      systems,
      displayDialog,
      displayCreateSystemDialog
    } = this.props;
    if (!systems) {
      return null;
    }
    return (
      <div className={classes.root}>
        <List className={classes.list} component="nav">
          {systems.map(system => (
            <ListItem
              className={classes.listItem}
              button
              key={system.id}
              onClick={() => this.props.selectSystem(system.id)}
            >
              <ListItemText
                className={classes.text}
                primary={system.name}
                secondary={system.id}
              />
              <ListItemSecondaryAction>
                <Button onClick={() => this.handleClickDelete(system.id)}>
                  delete
                </Button>
              </ListItemSecondaryAction>
            </ListItem>
          ))}
        </List>
        <Fab
          className={classes.fab}
          color="primary"
          onClick={() => displayCreateSystemDialog(true)}
        >
          <AddIcon />
        </Fab>
        <CreateSystemDialog
          open={displayDialog}
          onClose={() => displayCreateSystemDialog(false)}
          onSubmit={name => {
            this.handleCreateSystem(name);
            displayCreateSystemDialog(false);
          }}
          onLoadData={(name, labels, data, output, f) =>
            this.handleGenerateSystem(name, labels, data, output, f)
          }
        />
      </div>
    );
  }
}

const mapStateToProps = state => {
  return state.systems;
};

const mapDispatchToProps = dispatch =>
  bindActionCreators(actionCreators, dispatch);

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withStyles(styles)(RulesContainer));
