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

class ValueDetailsContainer extends React.Component {
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

  render() {
    const { classes } = this.props;

    return (
      <div className={classes.root}>
        <Breadcrumbs>
          <Link className={classes.link} to="/variables/">
            Variables
          </Link>
          <Typography color="textPrimary">{variable.name}</Typography>
        </Breadcrumbs>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    variable: state.variable.data
  };
};

const mapDispatchToProps = dispatch =>
  bindActionCreators(actionCreators, dispatch);

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withStyles(styles)(ValueDetailsContainer));
