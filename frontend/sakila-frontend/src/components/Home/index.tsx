import React from 'react';
import FilmButton from './FilmButton';
import './Home.css';

const Home: React.FC = () => {
  return (
    <div className="home-container">
      <h1>Welcome to My App</h1>
      <div>
      <FilmButton text="Film" to="/film" />
      <FilmButton text="Inventory" to="/inventory" />
      </div>
    </div>
  );
};

export { Home };
