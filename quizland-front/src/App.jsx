import NavBar from "./components/NavBar";
import Game from "./pages/Game"
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'

function App() {

  return (
    <Router>
      <NavBar/>
      <Routes>
        <Route exact path="/game" element={<Game/>} />
      </Routes>
    </Router>
  );
}

export default App
