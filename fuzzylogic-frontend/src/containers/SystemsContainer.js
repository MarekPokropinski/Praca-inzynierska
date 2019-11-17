import React from "react";
import { List, ListItem, ListItemText, Fab } from "@material-ui/core";
import { withStyles } from "@material-ui/core/styles";
import AddIcon from "@material-ui/icons/Add";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import * as actionCreators from "../actions/systemsActions";

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

  render() {
    const { classes, systems } = this.props;
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
            </ListItem>
          ))}
        </List>
        <Fab className={classes.fab} color="primary">
          <AddIcon />
        </Fab>
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
