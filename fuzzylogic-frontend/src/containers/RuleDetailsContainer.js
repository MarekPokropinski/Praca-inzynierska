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
import { Link } from "react-router-dom";
import { withStyles } from "@material-ui/core/styles";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import * as actionCreators from "../actions/ruleDetailsActions";
import { fetchVariables } from "../actions/variablesActions";
import PremisesList from "../components/PremisesList";

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
    textAlign: "center"
  },
  ifThenText: {
    backgroundColor: theme.palette.background.default,
    display: "flex",
    textAlign: "center",
    alignItems: "center",
    justifyContent: "center",
    height: "100%",

    borderWidth: "3px",
    borderStyle: "solid",
    borderColor: theme.palette.background.level2,
    padding: "2px"
  },
  saveButton: {
    padding: "20px 0",
    display: "flex",
    justifyContent: "center"
  }
});

class RuleDetailsContainer extends React.Component {
  componentDidMount() {
    this.refresh();
  }

  refresh() {
    const {
      fetchVariables,
      fetchRuleDetails,
      clearRuleDetails,
      system
    } = this.props;
    this.ruleId = this.props.match.params.rule;
    this.ruleId = this.ruleId === "new-rule" ? null : this.ruleId;
    if (!system) {
      return;
    }
    fetchVariables(system.id);
    if (this.ruleId !== null) {
      fetchRuleDetails(this.props.match.params.rule);
    } else {
      clearRuleDetails();
    }
  }

  handleClickSave() {
    const { variables, rule, createRule, updateRule, system } = this.props;

    const premiseToId = premise =>
      variables
        .find(variable => variable.name === premise.variableName)
        .values.find(value => value.name === premise.valueName).id;

    const premisesIds = rule.premises.map(premiseToId);
    const conclusionIds = rule.conclusions.map(premiseToId);

    if (this.ruleId === null) {
      createRule(system.id, premisesIds, conclusionIds);
    } else {
      updateRule(this.ruleId, premisesIds, conclusionIds);
    }
  }

  render() {
    const {
      classes,
      variables,
      rule,
      modifyPremise,
      addPremise,
      modifyConclusion,
      addConclusion
    } = this.props;

    if (!variables || !rule) {
      return null;
    }

    return (
      <div className={classes.root}>
        <Breadcrumbs>
          <Link className={classes.link} to="/rules/">
            Rules
          </Link>
          <Typography color="textPrimary">
            {this.ruleId === null ? "New Rule" : this.ruleId}
          </Typography>
        </Breadcrumbs>
        <Grid container spacing={2}>
          <Grid item xs={2} sm={1}>
            <Typography className={classes.ifThenText} color="textPrimary">
              IF
            </Typography>
          </Grid>
          <Grid item xs={10} sm={5}>
            <PremisesList
              title="Antecedent"
              list={rule.premises}
              variables={variables.filter(variable => variable.input)}
              onChange={modifyPremise}
              onAdd={addPremise}
            />
          </Grid>
          <Grid item xs={2} sm={1}>
            <Typography className={classes.ifThenText} color="textPrimary">
              THEN
            </Typography>
          </Grid>
          <Grid item xs={10} sm={5}>
            <PremisesList
              title="Consequent"
              list={rule.conclusions}
              variables={variables.filter(variable => !variable.input)}
              onChange={modifyConclusion}
              onAdd={addConclusion}
            />
          </Grid>
        </Grid>
        <div className={classes.saveButton}>
          <Button
            color="primary"
            variant="contained"
            onClick={() => this.handleClickSave()}
          >
            save
          </Button>
        </div>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    variables: state.variables.variables,
    rule: state.ruleDetails.rule,
    system: state.systems.system
  };
};

const mapDispatchToProps = dispatch =>
  bindActionCreators({ ...actionCreators, fetchVariables }, dispatch);

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withStyles(styles)(RuleDetailsContainer));
