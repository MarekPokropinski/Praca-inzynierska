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
  TableRow,
  FormControl,
  InputLabel,
  Select,
  MenuItem
} from "@material-ui/core";
import { withStyles } from "@material-ui/core/styles";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import { fetchRules } from "../actions/rulesActions";
import { fetchVariables } from "../actions/variablesActions";
import { process, queueVariableChange } from "../actions/engineActions";
import { updateSystem } from "../actions/systemsActions";
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
  constructor() {
    super();
    this.handleChangeConjunction = this.handleChangeConjunction.bind(this);
    this.handleChangeAggregation = this.handleChangeAggregation.bind(this);
    this.handleChangeDefuzzifier = this.handleChangeDefuzzifier.bind(this);
    this.handleChangeImplication = this.handleChangeImplication.bind(this);
    this.refreshProcess = this.refreshProcess.bind(this);
  }
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

  refreshProcess() {
    const { system, process, data } = this.props;
    process(
      data.inputs.map(variable => ({
        name: variable.name,
        value: variable.value
      })),
      system.id
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

  handleChangeConjunction(event) {
    const { system, updateSystem } = this.props;
    updateSystem(system.id, { conjunction: event.target.value }).then(
      this.refreshProcess
    );
  }

  handleChangeImplication(event) {
    const { system, updateSystem } = this.props;
    updateSystem(system.id, { implication: event.target.value }).then(
      this.refreshProcess
    );
  }

  handleChangeAggregation(event) {
    const { system, updateSystem } = this.props;
    updateSystem(system.id, { aggregation: event.target.value }).then(
      this.refreshProcess
    );
  }

  handleChangeDefuzzifier(event) {
    const { system, updateSystem } = this.props;
    updateSystem(system.id, { defuzzifier: event.target.value }).then(
      this.refreshProcess
    );
  }

  render() {
    const { classes, data, busy, system } = this.props;
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
          <Grid item xs={3}>
            <FormControl>
              <InputLabel>Conjunction</InputLabel>
              <Select
                value={system.conjunction}
                onChange={this.handleChangeConjunction}
              >
                <MenuItem value="AlgebraicProduct">Algebraic product</MenuItem>
                <MenuItem value="BoundedDifference">
                  Bounded difference
                </MenuItem>
                <MenuItem value="DrasticProduct">Drastic product</MenuItem>
                <MenuItem value="EinsteinProduct">Einstein product</MenuItem>
                <MenuItem value="HamacherProduct">Hamacher product</MenuItem>
                <MenuItem value="Minimum">Minimum</MenuItem>
                <MenuItem value="NilpotentMinimum">Nilpotent minimum</MenuItem>
              </Select>
            </FormControl>
          </Grid>
          <Grid item xs={3}>
            <FormControl>
              <InputLabel>Implication</InputLabel>
              <Select
                value={system.implication}
                onChange={this.handleChangeImplication}
              >
                <MenuItem value="AlgebraicProduct">Algebraic product</MenuItem>
                <MenuItem value="BoundedDifference">
                  Bounded difference
                </MenuItem>
                <MenuItem value="DrasticProduct">Drastic product</MenuItem>
                <MenuItem value="EinsteinProduct">Einstein product</MenuItem>
                <MenuItem value="HamacherProduct">Hamacher product</MenuItem>
                <MenuItem value="Minimum">Minimum</MenuItem>
                <MenuItem value="NilpotentMinimum">Nilpotent minimum</MenuItem>
              </Select>
            </FormControl>
          </Grid>
          <Grid item xs={3}>
            <FormControl>
              <InputLabel>Aggregation</InputLabel>
              <Select
                value={system.aggregation}
                onChange={this.handleChangeAggregation}
              >
                <MenuItem value="AlgebraicSum">Algebraic sum</MenuItem>
                <MenuItem value="BoundedSum">Bounded sum</MenuItem>
                <MenuItem value="DrasticSum">Drastic sum</MenuItem>
                <MenuItem value="EinsteinSum">Einstein sum</MenuItem>
                <MenuItem value="HamacherSum">Hamacher sum</MenuItem>
                <MenuItem value="Maximum">Maximum</MenuItem>
                <MenuItem value="NilpotentMaximum">Nilpotent maximum</MenuItem>
              </Select>
            </FormControl>
          </Grid>
          <Grid item xs={3}>
            <FormControl>
              <InputLabel>Defuzzifier</InputLabel>
              <Select
                value={system.defuzzifier}
                onChange={this.handleChangeDefuzzifier}
              >
                <MenuItem value="Bisector">Bisector</MenuItem>
                <MenuItem value="Centroid">Centroid</MenuItem>
                <MenuItem value="LargestOfMaximum">Largest of maximum</MenuItem>
                <MenuItem value="MeanOfMaximum">Mean of maximum</MenuItem>
                <MenuItem value="SmallestOfMaximum">
                  Smallest of maximum
                </MenuItem>
              </Select>
            </FormControl>
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
    { fetchRules, fetchVariables, process, queueVariableChange, updateSystem },
    dispatch
  );

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withStyles(styles)(ProcessContainer));
