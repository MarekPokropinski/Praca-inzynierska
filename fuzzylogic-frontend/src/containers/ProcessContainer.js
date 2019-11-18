import React from "react";
import {
  List,
  ListItem,
  ListItemText,
  Typography,
  Grid,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow
} from "@material-ui/core";
import { withStyles } from "@material-ui/core/styles";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import { fetchRules } from "../actions/rulesActions";
import { fetchVariables } from "../actions/variablesActions";
import { process, queueVariableChange } from "../actions/engineActions";
import EngineInputVariable from "../components/EngineInputVariable";
import EngineOutputVariable from "../components/EngineOutputVariable";

// import * as actionCreators from "../actions/rulesActions";

const styles = theme => ({
  root: { width: "100%" },
  grid: {
    textAlign: "center",
    paddingBottom: "80px"
  },
  table: {
    minWidth: 650
  }
});

class ProcessContainer extends React.Component {
  componentDidMount() {
    const { system, fetchVariables, fetchRules, process } = this.props;
    if (!system) {
      return;
    }
    fetchRules(system.id);
    fetchVariables(system.id).then(response =>
      process(
        response.payload.data
          .filter(variable => variable.input)
          .map(variable => ({
            name: variable.name,
            value: (variable.range.first + variable.range.second) / 2
          })),
        system.id
      )
    );
  }

  async handleProcess() {
    const { system, process, data, changes } = this.props;
    if (changes.size > 0) {
      await process(
        data.inputs.map(variable => ({
          name: variable.name,
          value: changes.has(variable.name)
            ? changes.get(variable.name)
            : variable.value
        })),
        system.id
      );
    }
  }

  handleChange(name, newValue) {
    const { queueVariableChange } = this.props;
    queueVariableChange(name, newValue);
  }

  render() {
    const { classes, data, busy } = this.props;
    if (!data) {
      return null;
    }
    if (!busy) {
      this.handleProcess();
    }
    return (
      <div className={classes.root}>
        <Grid className={classes.grid} container>
          <Grid item xs={6}>
            <Typography color="textPrimary">Input variables</Typography>
            {data.inputs.map(input => (
              <EngineInputVariable
                key={input.name}
                {...input}
                onChange={newValue => this.handleChange(input.name, newValue)}
              />
            ))}
          </Grid>
          <Grid item xs={6}>
            <Typography color="textPrimary">Output variables</Typography>
            {data.outputs.map(output => (
              <EngineOutputVariable key={output.name} {...output} />
            ))}
          </Grid>
          <Grid item xs={12}>
            <Typography color="textPrimary">Rules</Typography>
          </Grid>
          <Table className={classes.table} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Rule</TableCell>
                <TableCell align="right">Activation</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {data.rules.map(rule => (
                <TableRow
                  style={{
                    backgroundColor: `rgba(0,255,0,${rule.activationDegree})`
                  }}
                  key={rule.ruleText}
                >
                  <TableCell component="th" scope="row">
                    {rule.ruleText}
                  </TableCell>
                  <TableCell align="right">
                    {rule.activationDegree.toFixed(2)}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </Grid>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    rules: state.rules.rules,
    system: state.systems.system,
    variables: state.variables.variables,
    ...state.engine
  };
};

const mapDispatchToProps = dispatch =>
  bindActionCreators(
    { fetchRules, fetchVariables, process, queueVariableChange },
    dispatch
  );

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withStyles(styles)(ProcessContainer));