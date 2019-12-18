import React from "react";
import {
  List,
  ListItem,
  ListItemText,
  Fab,
  Button,
  ListItemSecondaryAction
} from "@material-ui/core";
import { withStyles } from "@material-ui/core/styles";
import AddIcon from "@material-ui/icons/Add";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import * as actionCreators from "../actions/rulesActions";

const styles = theme => ({
  root: { width: "100%" },
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
  },
  fab: {
    position: "absolute",
    bottom: theme.spacing(2),
    right: theme.spacing(3)
  }
});

class RulesContainer extends React.Component {
  componentDidMount() {
    const { system, fetchRules } = this.props;
    if (!system) {
      return;
    }
    fetchRules(system.id);
  }

  handleClickDelete(id) {
    const { deleteRule, system, fetchRules } = this.props;
    deleteRule(id).then(() => fetchRules(system.id));
  }

  render() {
    const { classes, rules } = this.props;
    if (!rules) {
      return null;
    }
    return (
      <div className={classes.root}>
        <List className={classes.list} component="nav">
          {rules.map(rule => (
            <ListItem
              className={classes.listItem}
              button
              key={rule.id}
              onClick={() => this.props.history.push(`${rule.id}/`)}
            >
              <ListItemText
                className={classes.text}
                primary={rule.description}
                secondary={rule.id}
              />
              <ListItemSecondaryAction>
                <Button onClick={() => this.handleClickDelete(rule.id)}>
                  delete
                </Button>
              </ListItemSecondaryAction>
            </ListItem>
          ))}
        </List>
        <Fab
          className={classes.fab}
          color="primary"
          onClick={() => this.props.history.push("new-rule")}
        >
          <AddIcon />
        </Fab>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return { ...state.rules, system: state.systems.system };
};

const mapDispatchToProps = dispatch =>
  bindActionCreators(actionCreators, dispatch);

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withStyles(styles)(RulesContainer));
