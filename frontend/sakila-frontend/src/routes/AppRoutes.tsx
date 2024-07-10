import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import LandingPage from "../pages/LandingPage";
import HomePage from "../pages/HomePage";
import FilmPage from "../pages/FilmPage";
import InventoryPage from "../pages/InventoryPage";
import NavigationBar from "../components/All/Navbar";

const AppRoutes: React.FC = () => (
    <Router>
        <Routes>
            <Route path="/" element={<LandingPage/>}></Route>
            <Route
                path="/*"
                element = {
                    <>
                    <NavigationBar/>
                    <Routes>
            <Route path="/home" element={<HomePage/>}></Route>
            <Route path="/film" element={<FilmPage/>}></Route>
            <Route path="/inventory" element={<InventoryPage/>}></Route>
        </Routes>
        </>
            }
            />
            </Routes>
    </Router>
)

export default AppRoutes;   