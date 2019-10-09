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
import { Link } from "react-router-dom";
import AddIcon from '@material-ui/icons/Add';
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import * as actionCreators from "../actions/variableActions";

const styles = theme => ({
  root: {
    padding: 10
  },
  link: {
    textDecoration: "none",
    color: theme.palette.text.primary
  },
  fab: {
    position: 'absolute',
    bottom: theme.spacing(2),
    right: theme.spacing(3),
  }, 
  button:{
    margin:theme.spacing(1)
  }
});



class MainContainer extends React.Component {
  componentDidMount() {
    this.props.fetchVariableDetails(this.props.match.params.id)
  }


  render() {
    const { classes, variable } = this.props;
    if(!variable){
      return null
    }
    return (
      <div className={classes.root}>
        <Breadcrumbs>
          <Link className={classes.link} to="/variables/">Variables</Link>
          <Typography color="textPrimary">{variable.name}</Typography>
        </Breadcrumbs>
        {/* <Fab className={classes.fab} color='primary'>
          <AddIcon/>
        </Fab> */}
        <Button className={classes.button} variant="contained">Add value</Button>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    variable: state.variable
  };
};

const mapDispatchToProps = dispatch =>
  bindActionCreators(actionCreators, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(withStyles(styles)(MainContainer));
