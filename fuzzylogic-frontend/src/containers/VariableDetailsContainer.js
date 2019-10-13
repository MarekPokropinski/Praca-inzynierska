import React from "react";
import {
  List,
  ListItem,
  ListItemText,
  Breadcrumbs,
  Typography,
  Fab,
  Button
} from "@material-ui/core";
import { withStyles } from "@material-ui/core/styles";
import { Link, Route } from "react-router-dom";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import * as actionCreators from "../actions/variableActions";
import CreateObjectDialog from "../components/CreateObjectDialog";
import VariablesContainer from "./VariablesContainer";

const styles = theme => ({
  root: {
    padding: 10
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
  }
});

class MainContainer extends React.Component {
  constructor() {
    super();
    this.handleAddValue = this.handleAddValue.bind(this);
    this.refresh = this.refresh.bind(this);
  }

  refresh() {
    this.props.fetchVariableDetails(this.props.match.params.id);
  }

  componentDidMount() {
    this.refresh();
  }

  async handleAddValue(name) {
    const { createValue, displayAddValueDialog, variable } = this.props;
    await createValue(variable.id, name);
    displayAddValueDialog(false);
  }

  render() {
    const {
      classes,
      variable,
      displayCreateValueDialog,
      displayAddValueDialog
    } = this.props;
    if (!variable || !variable.values) {
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
              onClick={() => this.props.history.push(`${variable.id}/`)}
            >
              <ListItemText className={classes.text} primary={value.name} />
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
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    variable: state.variable.data,
    displayCreateValueDialog: state.variable.displayCreateValueDialog
  };
};

const mapDispatchToProps = dispatch =>
  bindActionCreators(actionCreators, dispatch);

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withStyles(styles)(MainContainer));
