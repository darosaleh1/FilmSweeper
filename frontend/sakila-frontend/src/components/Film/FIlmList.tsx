import React from 'react';
import { _Film } from '../../types/FilmTypes';

interface FilmListProps {
  films: _Film[];
}

const FilmList: React.FC<FilmListProps> = ({ films }) => {
  if (!films || films.length === 0) {
    return <div>No films available.</div>;
  }

  return (
    <ul>
      {films.map((film) => (
        <li key={film.id}>{film.title}</li>
      ))}
    </ul>
  );
};

export default FilmList;
