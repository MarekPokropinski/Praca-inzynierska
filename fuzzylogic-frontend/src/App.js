import React, {useState} from 'react';
import './App.css';
import MainContainer from './containers/MainContainer'
import { MuiThemeProvider, createMuiTheme } from '@material-ui/core/styles';




function App() {
  const [palette, setPalette] = useState('dark')
  const theme = createMuiTheme({
    palette: {
      type: palette,
    },
  });
  return (
    <div >
      <MuiThemeProvider theme={theme}>
        <MainContainer palette={palette} setPalette={setPalette}/>
      </MuiThemeProvider>
    </div>
  );
}

export default App;
