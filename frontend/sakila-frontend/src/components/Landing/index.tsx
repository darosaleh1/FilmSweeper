import React from 'react';
import Button from './EnterButton';
import './Landing.css'; 

const Landing: React.FC = () => {
  return (
    <div className="landing-container">
      <h1>Welcome to the inventory management system</h1>
      <Button text="Get Started" to="/home" />
    </div>
  );
};

export { Landing };
