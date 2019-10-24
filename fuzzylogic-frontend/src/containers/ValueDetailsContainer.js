import React from "react";
import {
  Breadcrumbs,
  Typography,
  Button,
  Select,
  FormControl,
  InputLabel,
  MenuItem
} from "@material-ui/core";
import { withStyles } from "@material-ui/core/styles";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import * as actionCreators from "../actions/valueActions";
import { fetchPlot } from "../actions/variableActions";
import Plot from "../components/VariableValuesPlot";
import TriangularNumberForm from "../components/TriangularNumberForm";

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
    this.refresh = this.refresh.bind(this);
    this.handleApply = this.handleApply.bind(this);
  }

  refresh() {
    this.props.fetchValueDetails(this.props.match.params.valueId);
  }

  componentDidMount() {
    this.refresh();
  }

  handleApply() {
    const { newValue, UpdateNumber, fetchPlot, variable } = this.props;


    const { type, ...parameters } = newValue.number;
    const parametersArray = Object.values(parameters);
    UpdateNumber(newValue.id, type, parametersArray).then(() =>
      fetchPlot(variable.id)
    );
  }
  validateParameters(number) {
    let parameters;
    switch (number.type) {
      case "triangular":
        parameters = ["a", "b", "c"];
        break;
      case "trapezoidal":
        parameters = ["a", "b", "c", "d"];
        break;
      default:
        parameters = [];
    }
    for (let key in number) {
      if (!parameters.includes(key) && key !== "type") {
        delete number[key];
      }
    }
    for (let param of parameters) {
      if (!Object.keys(number).includes(param)) {
        number[param] = 0;
      }
    }
  }

  render() {
    const {
      classes,
      variable,
      value,
      plot,
      newValue,
      setType,
      setParams
    } = this.props;
    if (!value.id) {
      return null;
    }

    if (!newValue.number) {
      setType("none");
      return null;
    }


    let number = newValue.number;
    this.validateParameters(number);

    return (
      <div className={classes.root}>
        <Breadcrumbs>
          <Link className={classes.link} to="/variables/">
            Variables
          </Link>
          <Link className={classes.link} to={`/variables/${variable.id}/`}>
            {variable.name}
          </Link>
          <Typography color="textPrimary">{value.name}</Typography>
        </Breadcrumbs>

        <Plot name={variable.name} values={plot} />

        <FormControl>
          <InputLabel>type</InputLabel>
          <Select
            value={newValue.number.type}
            onChange={event => setType(event.target.value)}
          >
            {/* <MenuItem value="none">none</MenuItem> */}
            <MenuItem value="triangular">triangular</MenuItem>
            <MenuItem value="trapezoidal">trapezoidal</MenuItem>
          </Select>
          <TriangularNumberForm number={number} onChange={setParams} />
          <Button
            variant="contained"
            color="primary"
            onClick={this.handleApply}
            disabled={newValue === value || newValue.number.type === "none"}
          >
            Apply
          </Button>
          {/* <Button variant="contained" color="secondary">
            Cancel
          </Button> */}
        </FormControl>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    variable: state.variable.data,
    value: state.value.data,
    newValue: state.value.newValue,
    plot: state.variable.plot
  };
};

const mapDispatchToProps = dispatch =>
  bindActionCreators({ ...actionCreators, fetchPlot }, dispatch);

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withStyles(styles)(ValueDetailsContainer));
