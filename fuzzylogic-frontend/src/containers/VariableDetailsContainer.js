import React from "react";
import {
  List,
  ListItem,
  ListItemText,
  Breadcrumbs,
  Typography,
  Fab,
  Button,
  ListItemSecondaryAction
} from "@material-ui/core";
import { withStyles } from "@material-ui/core/styles";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import * as actionCreators from "../actions/variableActions";
import CreateObjectDialog from "../components/CreateObjectDialog";
import Plot from "../components/VariableValuesPlot";

const styles = theme => ({
  root: {
    // padding: 10
  },
  link: {
    textDecoration: "none",
    color: theme.palette.text.primary
  },
  fab: {
    position: "absolute",
    bottom: theme.spacing(2),
    right: theme.spacing(3)
  },
  button: {
    margin: theme.spacing(1)
  },
  list: {
    width: "100%"
    // padding: "5px"
  },
  listItem: {
    border: "2px solid " + theme.palette.primary.light,
    borderRadius: "5px",
    marginBottom: "5px"
  },
  text: {
    color: theme.palette.text.primary
  }
});

class VariableDetailsContainer extends React.Component {
  constructor() {
    super();
    this.handleAddValue = this.handleAddValue.bind(this);
    this.refresh = this.refresh.bind(this);
    this.handleEditVariable = this.handleEditVariable.bind(this);
  }

  refresh() {
    const { fetchVariableDetails, fetchPlot } = this.props;
    const id = this.props.match.params.id;
    fetchVariableDetails(id);
    fetchPlot(id);
  }

  componentDidMount() {
    this.refresh();
  }

  async handleAddValue(name) {
    const { createValue, displayAddValueDialog, variable } = this.props;
    await createValue(variable.id, name);
    displayAddValueDialog(false);
  }
  handleClickDelete(id) {
    const { deleteValue } = this.props;
    deleteValue(id).then(() => {
      this.refresh();
    });
  }

  async handleEditVariable(name, isInput) {
    const { editVariable, setDisplayEditVariableDialog, variable } = this.props;
    await editVariable(variable.id, name, isInput);
    setDisplayEditVariableDialog(false);
    this.refresh();
  }

  render() {
    const {
      classes,
      variable,
      plot,
      displayCreateValueDialog,
      displayAddValueDialog,
      displayEditVariableDialog,
      setDisplayEditVariableDialog
    } = this.props;
    if (!variable || !variable.values) {
      return null;
    }
    if (!plot) {
      return null;
    }
    if (variable.id !== parseInt(this.props.match.params.id, 10)) {
      this.refresh();
    }
    return (
      <div className={classes.root}>
        <Breadcrumbs>
          <Link className={classes.link} to="/variables/">
            Variables
          </Link>
          <Typography color="textPrimary">{variable.name}</Typography>
        </Breadcrumbs>

        <Plot name={variable.name} values={plot} />

        <Button
          className={classes.button}
          variant="contained"
          onClick={() => setDisplayEditVariableDialog(true)}
        >
          Edit variable
        </Button>

        <Button
          className={classes.button}
          variant="contained"
          onClick={() => displayAddValueDialog(true)}
        >
          Add value
        </Button>

        <List className={classes.list} component="nav">
          {variable.values.map((value, key) => (
            <ListItem
              className={classes.listItem}
              button
              key={key}
              onClick={() => this.props.history.push(`${value.id}/`)}
            >
              <ListItemText className={classes.text} primary={value.name} />
              <ListItemSecondaryAction>
                <Button onClick={() => this.handleClickDelete(value.id)}>
                  delete
                </Button>
              </ListItemSecondaryAction>
            </ListItem>
          ))}
        </List>

        <CreateObjectDialog
          open={displayCreateValueDialog}
          onClose={() => displayAddValueDialog(false)}
          onSubmit={this.handleAddValue}
          title="Create linguistic value"
          text="value name"
        />

        <CreateObjectDialog
          open={displayEditVariableDialog}
          onClose={() => setDisplayEditVariableDialog(false)}
          onSubmit={this.handleEditVariable}
          title="Edit linguistic variable"
          text="New variable name"
          checkboxText="Is input"
        />
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    variable: state.variable.data,
    displayCreateValueDialog: state.variable.displayCreateValueDialog,
    displayEditVariableDialog: state.variable.displayEditVariableDialog,
    plot: state.variable.plot
  };
};

const mapDispatchToProps = dispatch =>
  bindActionCreators(actionCreators, dispatch);

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withStyles(styles)(VariableDetailsContainer));
