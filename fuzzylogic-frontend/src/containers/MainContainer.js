import React from "react";
import {
  Tabs,
  Tab,
  Switch,
  FormGroup,
  FormControlLabel
} from "@material-ui/core";
import { withStyles } from "@material-ui/core/styles";
import { Router, Route, Redirect } from "react-router-dom";
import { createBrowserHistory } from "history";
import VariablesContainer from "./VariablesContainer";
import VariableContainer from "./VariableDetailsContainer";

const styles = theme => ({
  root: {
    display: "flex",
    background: theme.palette.background.paper
  },
  tabs_container: {
    background: theme.palette.background.default,
    borderRight: "1px solid " + theme.palette.secondary.dark,
    height: "100vh",
    maxWidth: "200px"
  },
  tabs: {
    height: "calc(100vh - 50px)"
  },
  tabpanel: {
    height: "100vh",
    overflowY: "scroll",
    width: "100%"
  },
  tabTitle: {
    borderBottom: "2px solid white",
    fontSize: "1.5rem",
    color: theme.palette.text.primary
  },
  tab: {
    color: theme.palette.text.primary
  },
  switch: {
    margin: "0 auto 0 auto",
    color: theme.palette.text.primary
  }
});

function a11yProps(index) {
  return {
    id: `vertical-tab-${index}`,
    "aria-controls": `vertical-tabpanel-${index}`
  };
}

class MainContainer extends React.Component {
  constructor() {
    super();
    this.state = {
      selectedTab: 1
    };
    this.history = createBrowserHistory();
    this.history.listen(history => console.log(history));
  }

  render() {
    const { selectedTab } = this.state;
    const { classes, palette, setPalette } = this.props;
    return (
      <div className={classes.root}>
        <div className={classes.tabs_container}>
          <Tabs
            className={classes.tabs}
            orientation="vertical"
            value={selectedTab}
            onChange={(_event, newValue) => {
              const routes = ["/variables/", "/rules/"];
              const route = routes[newValue - 1];
              this.history.push({pathname:route})
              // this.history.replace(route);
              // console.log({...this.history.location})
            }}
          >
            <Tab
              className={classes.tabTitle}
              label="Fuzzy logic systems"
              disabled
              wrapped
              {...a11yProps(3)}
            />
            <Tab className={classes.tab} label="Variables" {...a11yProps(0)} />
            <Tab className={classes.tab} label="Rules" {...a11yProps(1)} />
          </Tabs>
          <FormGroup row>
            <FormControlLabel
              className={classes.switch}
              label="darkmode"
              control={
                <Switch
                  checked={palette === "dark"}
                  onChange={() =>
                    setPalette(palette === "dark" ? "light" : "dark")
                  }
                />
              }
            />
          </FormGroup>
        </div>
        <div className={classes.tabpanel}>
          <Router history={this.history}>
            <Route exact path="/variables/:id" component={VariableContainer} />
            <Route exact path="/variables/" component={VariablesContainer} />
            <Redirect from="/" to="/variables/" />
          </Router>
        </div>
      </div>
    );
  }
}
export default withStyles(styles)(MainContainer);
