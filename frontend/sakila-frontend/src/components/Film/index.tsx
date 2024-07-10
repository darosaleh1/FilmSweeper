import React, { useEffect, useState } from 'react';
import { fetchFilms } from '../../services/filmService';
import { _Film } from '../../types/FilmTypes';
import FilmList from './FIlmList'; 
import Pagination from './Pagination';


const Film: React.FC = () => {
  const [films, setFilms] = useState<_Film[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [totalPages, setTotalPages] = useState<number>(1);

  useEffect(() => {

    const fetchData = async (page: number) => {
      setLoading(true);
      setError(null);

      try {
        const data = await fetchFilms(page - 1);
        console.log('Fetched data:', data);
        setFilms(data.content);
        setCurrentPage(data.number + 1);
        setTotalPages(data.totalPages);
      } catch (error) {
        console.error('Error fetching films:', error);
        setError(error instanceof Error ? error.message : 'An unexpected error occurred');
      } finally {
        setLoading(false);
      }
    };

    fetchData(currentPage);
  }, [currentPage]);

  const handleNextPage = () => {
    if (currentPage < totalPages) {
      setCurrentPage((prevPage) => prevPage + 1);
    }
  };

  const handlePreviousPage = () => {
    if (currentPage > 1) {
      setCurrentPage((prevPage) => prevPage - 1);
    }
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div>
      <h1>Film List</h1>
      <FilmList films={films} />
      <Pagination
        currentPage={currentPage}
        totalPages={totalPages}
        onPrevious={handlePreviousPage}
        onNext={handleNextPage}
      />
    </div>
  );
  
};

export {Film};
