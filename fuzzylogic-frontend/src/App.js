import React, { useState } from "react";
import MainContainer from "./containers/MainContainer";
import { MuiThemeProvider, createMuiTheme } from "@material-ui/core/styles";

function App() {
  const [palette, setPalette] = useState(
    localStorage.getItem("theme") || "light"
  );
  const theme = createMuiTheme({
    palette: {
      type: palette
    }
  });
  return (
    <div>
      <MuiThemeProvider theme={theme}>
        <MainContainer
          palette={palette}
          setPalette={palette => {
            setPalette(palette);
            localStorage.setItem("theme", palette);
          }}
        />
      </MuiThemeProvider>
    </div>
  );
}

export default App;
