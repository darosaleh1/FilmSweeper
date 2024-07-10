import React from 'react';
import { useNavigate } from 'react-router-dom';

interface ButtonProps {
  text: string;
  onClick?: () => void;
  type?: 'button' | 'submit' | 'reset';
  disabled?: boolean;
  to?: string;
}

const Button: React.FC<ButtonProps> = ({ text, onClick, type = 'button', disabled = false, to }) => {
  const navigate = useNavigate();

  const handleClick = () => {
    if(onClick) {
      onClick();
    }
    if(to){
      navigate(to);
    }
  };

  return (
    <button type={type} onClick={handleClick} disabled={disabled}>
      {text}
    </button>
  );
};

export default Button;
