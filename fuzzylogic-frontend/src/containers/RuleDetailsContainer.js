import React from "react";
import {
  Breadcrumbs,
  Typography,
  Button,
  Select,
  FormControl,
  InputLabel,
  MenuItem,
  Grid
} from "@material-ui/core";
import {Link} from "react-router-dom";
import { withStyles } from "@material-ui/core/styles";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import * as actionCreators from "../actions/valueActions";
import PremisesList from '../components/PremisesList';

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
  typhography: {
    // color: theme.palette.text.primary
    backgroundColor: theme.palette.background.default,
    textAlign: 'center'
  }
});

class RuleDetailsContainer extends React.Component {
  constructor() {
    super();
  }

  componentDidMount() {
    // this.refresh();
  }


  render() {
    const {classes} = this.props

    return (
      <div className={classes.root}>
        <Breadcrumbs>
          <Link className={classes.link} to="/rules/">
            Rules
          </Link>
          <Typography color="textPrimary">New Rule</Typography>
        </Breadcrumbs>
        <Grid container spacing={2}>
          <Grid item xs={6}>
            <PremisesList title="Premises" list={[{name:""}]} variables={[]}/>
          </Grid>
          {/* <Grid item xs={6}>
            <PremisesList title="Conclusions"/>
          </Grid> */}
        </Grid>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
  };
};

const mapDispatchToProps = dispatch =>
  bindActionCreators(actionCreators, dispatch);

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withStyles(styles)(RuleDetailsContainer));
